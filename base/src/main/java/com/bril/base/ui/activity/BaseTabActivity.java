package com.bril.base.ui.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.bril.base.App;
import com.bril.base.R;
import com.bril.base.di.component.AppComponent;
import com.bril.base.net.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 基类
 */

public abstract class BaseTabActivity extends TabActivity implements EasyPermissions.PermissionCallbacks, LifecycleProvider<ActivityEvent> {
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    public Context context;
    protected SweetAlertDialog pDialog;
    private boolean isThemChange = true;
    private BaseActivity.OpenType openType = BaseActivity.OpenType.LEFT;
    @Inject
    public ApiService apiService;

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        context = this;
//        int fontType = FontSizeManager.getInstance().getFontSize(context);
//        if (fontType == FontSizeManager.TYPE_NORMAL) {
//            onApplyThemeResource(getTheme(), R.style.Normal, true);
//        } else if (fontType == FontSizeManager.TYPE_LARGE) {
//            onApplyThemeResource(getTheme(), R.style.Large, true);
//        } else if (fontType == FontSizeManager.TYPE_SUPPER_LARGE) {
//            onApplyThemeResource(getTheme(), R.style.SuperLarge, true);
//        }
//        isThemChange = false;

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(App.getInstance().getAppComponent());
        initeView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        openAnimatior();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        openAnimatior();
    }

    private void openAnimatior() {
        if (openType.equals(BaseActivity.OpenType.LEFT)) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        } else if (openType.equals(BaseActivity.OpenType.BOTTOM)) {
            overridePendingTransition(R.anim.in_from_bottom, R.anim.out_from_top);
        } else if (openType.equals(BaseActivity.OpenType.ALPHA)) {
            overridePendingTransition(R.anim.from_alpha, R.anim.from_alpha);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (openType.equals(BaseActivity.OpenType.LEFT)) {
            overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
        } else if (openType.equals(BaseActivity.OpenType.BOTTOM)) {
            overridePendingTransition(R.anim.in_from_top, R.anim.out_from_bottom);
        } else if (openType.equals(BaseActivity.OpenType.ALPHA)) {
            overridePendingTransition(R.anim.from_alpha, R.anim.from_alpha);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                clickBack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickBack() {
        finish();
    }

    protected void showLoading(String msg) {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    protected void closeLoading() {
        if (pDialog != null) {
            pDialog.cancel();
        }
    }

    protected void showError(String msg) {
        if (pDialog != null) {
            pDialog.setTitleText(msg)
                    .setConfirmText("确认")
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }
    }

    protected void showError(String msg, SweetAlertDialog.OnSweetClickListener listener) {
        if (pDialog != null) {
            pDialog.setTitleText(msg)
                    .setConfirmText("确认")
                    .setConfirmClickListener(listener)
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }
    }

    protected void showSuccess(String msg, SweetAlertDialog.OnSweetClickListener listener) {
        if (pDialog != null) {
            pDialog.setTitleText(msg)
                    .setConfirmText("确认")
                    .setConfirmClickListener(listener)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }


    protected abstract int getLayoutId();

    protected abstract void initeView();

    public void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
