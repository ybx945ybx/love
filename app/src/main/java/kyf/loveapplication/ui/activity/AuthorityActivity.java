package kyf.loveapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kyf.loveapplication.R;

public class AuthorityActivity extends AppCompatActivity {


    @BindView(R.id.sb_not_see_me)  SwitchButton mSbWechat;    // 微信
    @BindView(R.id.sb_not_see_her) SwitchButton mSbWeibo;     // 微博
    @BindView(R.id.sb_black)       SwitchButton mSbQq;        // QQ

    @BindView(R.id.rlyt_complain) RelativeLayout rlytComplain;        // QQ
    @BindView(R.id.tv_delete)     TextView       tvDelete;        // QQ

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        ButterKnife.bind(this);

        initVars();
        initViews();
        initEvent();
        initData();
    }

    private void initVars() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }
    }

    private void initViews() {

    }

    private void initEvent() {

    }

    private void initData() {

    }

    @OnClick({R.id.sb_not_see_me, R.id.sb_not_see_her, R.id.sb_black, R.id.rlyt_complain, R.id.tv_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_not_see_me:
                break;
            case R.id.sb_not_see_her:
                break;
            case R.id.sb_black:
                break;
            case R.id.rlyt_complain:
                break;
            case R.id.tv_delete:
                break;
        }
    }

    public static void toThisAvtivity(Activity activity, String userId) {
        Intent intent = new Intent(activity, AuthorityActivity.class);
        intent.putExtra("userId", userId);
        activity.startActivity(intent);
    }
}
