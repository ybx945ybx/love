package kyf.loveapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kyf.loveapplication.R;
import kyf.loveapplication.data.event.LoginStateChangeEvent;
import kyf.loveapplication.ui.view.LoveEditText;
import kyf.loveapplication.utils.ToastUtils;

/**
 * Created by a55 on 2017/11/7.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_country)        TextView     tvCountry;
    @BindView(R.id.tv_select_country) TextView     tvSelectCountry;
    @BindView(R.id.et_account)        LoveEditText etAccount;
    @BindView(R.id.et_password)       LoveEditText etPassword;
    @BindView(R.id.tv_login)          TextView     tvLogin;
    @BindView(R.id.tv_to_code_login)  TextView     tvToCodeLogin;
    @BindView(R.id.tv_register)       TextView     tvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        intVars();
        initView();
        initEvent();
        initData();

    }

    private void intVars() {

    }

    private void initView() {
        etPassword.setRightImgSelect(false);
        etPassword.setTransformationMethod(etPassword.getRightImgSelect() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());

    }

    private void initEvent() {

        etPassword.setOnRightImgClickListener(view -> {
            etPassword.setRightImgSelect(!etPassword.getRightImgSelect());

            etPassword.setTransformationMethod(etPassword.getRightImgSelect() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            //切换后将EditText光标置于末尾
            CharSequence charSequence = etPassword.getEditText();
            if (charSequence instanceof Spannable) {
                Spannable spanText = (Spannable) charSequence;
                Selection.setSelection(spanText, charSequence.length());
            }
        });
    }

    private void initData() {

    }

    @OnClick({R.id.tv_register, R.id.tv_to_code_login, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
//                ToastUtils.showToast(mActivity, "登录");
                if (TextUtils.isEmpty(etAccount.getEditText())){
                    ToastUtils.showToast(mActivity, "请输入账号");
                }
                if (TextUtils.isEmpty(etPassword.getEditText())){
                    ToastUtils.showToast(mActivity, "请输入密码");
                }
                loginEmc(etAccount.getEditText().toString(), etPassword.getEditText().toString());
//                loginEmc("love", "pppppp");
                //                EMClient.getInstance().login(userName,password,new EMCallBack() {//回调

                break;
            case R.id.tv_to_code_login:
                LoginMobileActivity.launch(mActivity);
                break;
            case R.id.tv_register:
                RegisterActivity.launch(mActivity);
                break;
        }
    }

    private void loginEmc(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                dismissProgressDialog();
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Logger.d("登录聊天服务器成功！");

                MainActivity.toThisAvtivity(mActivity);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Logger.d(message);
                dismissProgressDialog();
            }
        });
    }

    @Subscribe
    public void onLoginStateEvent(LoginStateChangeEvent event){
        finish();
    }
}
