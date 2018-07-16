package com.bril.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.bril.base.R;
import com.bril.base.net.BaseCallModel;
import com.bril.base.net.transformer.NetObserverTransformer;
import com.bril.base.ui.adapter.BaseQuickAdapter;
import com.bril.base.ui.adapter.BaseViewHolder;
import com.bril.base.ui.events.ReFreshEvent;
import com.bril.base.ui.widget.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * 刷新封装
 */

public abstract class BaseRefreshFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    public RecyclerView rvList;
    public SwipeRefreshLayout refreshLayout;
    public int page = 1;
    public int rows = 20;
    public SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(4);
    public BaseQuickAdapter<T, BaseViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onViewCreated(view, savedInstanceState);
        rvList=getActivity().findViewById(R.id.rv_list);
        refreshLayout=getActivity().findViewById(R.id.swipeLayout);
        refreshLayout.setOnRefreshListener(this);
        rvList.addItemDecoration(spaceItemDecoration);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = getAdapter();
        adapter.bindToRecyclerView(rvList);
        rvList.setAdapter(adapter);
        adapter.setEmptyView(R.layout.layout_data_empty);
        adapter.setOnItemClickListener(this);
        if (isLoadMore()) {
            adapter.setOnLoadMoreListener(this);
        }
        onRefresh();
    }

    public void netDate(final boolean isFlash) {
        getCall().compose(new NetObserverTransformer<>())
                .compose(this.bindToLifecycle())
                .subscribe(date -> {
                    loadSuc(isFlash, date);
                    refreshLayout.setRefreshing(false);
                }, e -> refreshLayout.setRefreshing(false));
    }

    public void loadSuc(boolean isFlash, List<T> date) {
        if (isFlash) {
            page++;
            adapter.setNewData(date);
        } else {
            if (date.isEmpty()) {
                adapter.loadMoreEnd();
            } else {
                page++;
                adapter.addData(date);
                adapter.loadMoreComplete();
            }
        }
    }

    public abstract boolean isLoadMore();

    public abstract BaseQuickAdapter<T, BaseViewHolder> getAdapter();

    public abstract Observable<BaseCallModel<List<T>>> getCall();

    public abstract void onRefreshEventBus(ReFreshEvent event);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(ReFreshEvent event) {
        onRefreshEventBus(event);
    }


    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        page = 1;
        netDate(true);
    }

    @Override
    public void onLoadMoreRequested() {
        netDate(false);
    }
}
