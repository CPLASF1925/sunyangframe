package com.bril.base.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bril.base.App;
import com.bril.base.R;
import com.bril.base.di.component.AppComponent;
import com.bril.base.net.ApiService;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 基类
 */

public abstract class BaseActivity extends RxAppCompatActivity implements EasyPermissions.PermissionCallbacks {
    Toolbar toolbar;
    public TextView toolbarTitle;
    private TextView toolbarRight;
    private TextView toolbarLeft;
    public Context context;
    protected SweetAlertDialog pDialog;
    private OpenType openType = OpenType.LEFT;
    @Inject
    public ApiService apiService;
    private static final String TAG = "BaseActivity";

    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        Log.e(TAG, "设置主题：：" + resid);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
//        int fontType = FontSizeManager.getInstance().getFontSize(context);
//        if (fontType == FontSizeManager.TYPE_NORMAL) {
//            //onApplyThemeResource(getTheme(), R.style.Normal, true);
//            getTheme().applyStyle(R.style.Normal, true);
//        } else if (fontType == FontSizeManager.TYPE_LARGE) {
//            //onApplyThemeResource(getTheme(), R.style.Large, true);
//            getTheme().applyStyle(R.style.Large, true);
//        } else if (fontType == FontSizeManager.TYPE_SUPPER_LARGE) {
//            getTheme().applyStyle(R.style.SuperLarge, true);
//            //onApplyThemeResource(getTheme(), R.style.SuperLarge, true);
//        }
        setupActivityComponent(App.getInstance().getAppComponent());
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
            toolbarRight = (TextView) findViewById(R.id.tv_right);
            toolbarLeft = (TextView) findViewById(R.id.tv_left);
            if (toolbarTitle != null) {
                toolbarTitle.setText(getTitle());
            }
            if (toolbar != null) {
                toolbar.setTitle("");
                setSupportActionBar(toolbar);
            }
        }
        initeView();
    }

    /**
     * 打开及关闭方式，默认从左往右
     */
    public void setOpenAnimate(OpenType openType) {
        this.openType = openType;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        openAnimatior();
    }

    public void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        openAnimatior();
    }

    private void openAnimatior() {
        if (openType.equals(OpenType.LEFT)) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        } else if (openType.equals(OpenType.BOTTOM)) {
            overridePendingTransition(R.anim.in_from_bottom, R.anim.out_from_top);
        } else if (openType.equals(OpenType.ALPHA)) {
            overridePendingTransition(R.anim.from_alpha, R.anim.from_alpha);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (openType.equals(OpenType.LEFT)) {
            overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
        } else if (openType.equals(OpenType.BOTTOM)) {
            overridePendingTransition(R.anim.in_from_top, R.anim.out_from_bottom);
        } else if (openType.equals(OpenType.ALPHA)) {
            overridePendingTransition(R.anim.from_alpha, R.anim.from_alpha);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setRightButton(String name, int rightIconId, View.OnClickListener onClickListener) {
        toolbarRight.setText(name);
        toolbarRight.setVisibility(View.VISIBLE);
        toolbarRight.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(rightIconId), null, null, null);
        toolbarRight.setOnClickListener(onClickListener);
    }

    public void setRightButton(String name, View.OnClickListener onClickListener) {
        toolbarRight.setText(name);
        toolbarRight.setVisibility(View.VISIBLE);
        toolbarRight.setOnClickListener(onClickListener);
    }

    public void setLeftButton(String name, int rightIconId, View.OnClickListener onClickListener) {
        toolbarLeft.setText(name);
        toolbarLeft.setVisibility(View.VISIBLE);
        toolbarLeft.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(rightIconId), null, null, null);
        toolbarLeft.setOnClickListener(onClickListener);
    }

    public void setLeftButton(String name, View.OnClickListener onClickListener) {
        toolbarLeft.setText(name);
        toolbarLeft.setVisibility(View.VISIBLE);
        toolbarLeft.setOnClickListener(onClickListener);
    }

    public void setBackVisible() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.mx_btn_back));
        getSupportActionBar().setHomeButtonEnabled(true);
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

    public void showLoading(String msg) {
        closeLoading();
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void closeLoading() {
        if (pDialog != null) {
            pDialog.cancel();
        }
    }

    public void showError(String msg) {
        if (pDialog != null) {
            pDialog.setTitleText(msg)
                    .setConfirmText("确认")
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }
    }

    public void showError(String msg, SweetAlertDialog.OnSweetClickListener listener) {
        if (pDialog != null) {
            pDialog.setTitleText(msg)
                    .setConfirmText("确认")
                    .setConfirmClickListener(listener)
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }
    }

    public void showAlert(String msg, SweetAlertDialog.OnSweetClickListener listener) {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText(msg);
        pDialog.setTitleText(msg)
                .setCancelText("取消")
                .setConfirmText("确定")
                .setConfirmClickListener(listener).show();
    }

    public void showSuccess(String msg, SweetAlertDialog.OnSweetClickListener listener) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void initeView();

    enum OpenType {
        LEFT, BOTTOM, ALPHA
    }
}
