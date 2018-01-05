package kyf.loveapplication.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kyf.loveapplication.R;
import kyf.loveapplication.ui.view.LoveEditText;
import kyf.loveapplication.ui.view.LoveImageView;
import kyf.loveapplication.utils.ToastUtils;

/**
 * 短信验证码登录
 * Created by a55 on 2017/11/18.
 */

public class LoginMobileActivity extends BaseActivity {

    @BindView(R.id.iv_head)       LoveImageView ivHead;
    @BindView(R.id.et_phone)      LoveEditText  etPhone;
    @BindView(R.id.et_code)       LoveEditText  etCode;
    @BindView(R.id.tv_login)      TextView      tvLogin;

    private CountDownTimer mCountDownTimer;         // 发送验证码倒计时

    /**
     * 跳转到当前页
     *
     * @param context context
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginMobileActivity.class);
        ((Activity) context).startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mobile);
        ButterKnife.bind(this);

        intVars();
        initView();
        initEvent();
        initData();

    }

    private void intVars() {

    }

    private void initView() {

    }

    private void initEvent() {

        etPhone.setOnRightTxtClickListener(view -> {
            if (etCode.getRightTextEnable()) {
                getCode();
            }
        });
    }

    private void getCode() {

        if (TextUtils.isEmpty(etPhone.getEditText())) {
            ToastUtils.showToast(mActivity, "请先输入手机号码");
            return;
        }
        if (etPhone.getEditText().toString().length() < 11) {
            ToastUtils.showToast(mActivity, "请输入正确的手机号码");
            return;
        }

        startCountDown();
        ToastUtils.showToast(mActivity, String.format("短信验证码已发送至%s", etPhone.getEditText().toString()));


    }

    private void initData() {

    }

    /**
     * 发送验证码倒计时
     */
    private void startCountDown() {
        etPhone.setRightTextEnable(false);
        mCountDownTimer = new CountDownTimer(60 * 2000, 1000) { // 开始倒计时
            @Override
            public void onTick(long millisUntilFinished) {
                etPhone.setRightText("重新获取" + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                etPhone.setRightTextEnable(true);
                etPhone.setRightText("获取验证码");
            }
        }.start();
    }

    @OnClick({R.id.headerLeftImg, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headerLeftImg:
                onBackPressed();
                break;
            case R.id.tv_login:
                if (TextUtils.isEmpty(etPhone.getEditText())) {
                    ToastUtils.showToast(mActivity, "请先输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etCode.getEditText())) {
                    ToastUtils.showToast(mActivity, "请先输入验证码");
                    return;
                }
//                ToastUtils.showToast(mActivity, "登录");

                LoginDefeatActivity.launch(mActivity);
                break;
        }
    }
}
