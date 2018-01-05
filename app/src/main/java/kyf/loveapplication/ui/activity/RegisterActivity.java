package kyf.loveapplication.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kyf.loveapplication.R;
import kyf.loveapplication.data.event.LoginStateChangeEvent;
import kyf.loveapplication.ui.view.LoveEditText;
import kyf.loveapplication.ui.view.LoveImageView;
import kyf.loveapplication.utils.ToastUtils;

/**
 * 注册
 * Created by a55 on 2017/11/18.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.iv_head)        LoveImageView ivHead;
    @BindView(R.id.iv_second_head) LoveImageView ivSecondHead;

    @BindView(R.id.et_name)        LoveEditText etName;
    @BindView(R.id.et_second_name) LoveEditText etSecondName;
    @BindView(R.id.et_province)    LoveEditText etProvice;
    @BindView(R.id.et_phone)       LoveEditText etPhone;
    @BindView(R.id.et_code)        LoveEditText etCode;
    @BindView(R.id.et_password)    LoveEditText etPassword;

    /**
     * 跳转到当前页
     *
     * @param context context
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        ((Activity) context).startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

    }

    private void initData() {

    }

    @OnClick({R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                if (TextUtils.isEmpty(etName.getEditText())) {
                    ToastUtils.showToast(mActivity, "请输入账号");
                }
                if (TextUtils.isEmpty(etPassword.getEditText())) {
                    ToastUtils.showToast(mActivity, "请输入密码");
                }
                registerEmc(etName.getEditText().toString(), etPassword.getEditText().toString());
                break;
        }
    }

    private void registerEmc(String userName, String password) {
        new Thread(() -> {
            try {
                //注册失败会抛出HyphenateException
                EMClient.getInstance().createAccount(userName, password);//同步方法
                EventBus.getDefault().post(new LoginStateChangeEvent());
                MainActivity.toThisAvtivity(mActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
