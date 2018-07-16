package com.bril.base.net.transformer;

import com.bril.base.App;
import com.bril.base.net.exception.AuthorizationException;
import com.bril.base.net.exception.HintUserException;
import com.bril.base.ui.events.ExitEvent;
import com.bril.base.utils.ToastUtils;
import com.google.gson.stream.MalformedJsonException;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 异步请求
 */

public class AsyObserverTransformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(e -> {
                    if (e instanceof AuthorizationException) {
                        EventBus.getDefault().post(new ExitEvent());
                    } else {
                        String hint = "访问网络失败，请稍后再试";
                        if (e instanceof HintUserException) {
                            hint = e.getMessage();
                        } else if (e instanceof ConnectException) {
                            hint = "服务网络断开，检查网络是否可用";
                        } else if (e instanceof SocketTimeoutException) {
                            hint = "网络访问超时,请稍后再试";
                        } else if (e instanceof MalformedJsonException) {
                            hint = "数据解析异常，请联系管理员！";
                        }
                        ToastUtils.shortShow(App.getInstance(), hint);
                        e.printStackTrace();
                    }
                    return null;
                });
    }
}