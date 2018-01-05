package kyf.loveapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kyf.loveapplication.R;
import kyf.loveapplication.ui.view.LoveImageView;

public class UserHomePageActivity extends AppCompatActivity {

    //    @BindView(R.id.tv_add_friend) TextView tvAddPhoneFriend;         // 底部RadioGroup
    //    @BindView(R.id.tv_add_record) TextView tvAddRecord;         // 底部RadioGroup
    //
    //    @BindView(R.id.et_search) EditText etSearch;         // 底部RadioGroup
    //    @BindView(R.id.tv_search) TextView tvSearch;         // 底部RadioGroup
    //
    //    @BindView(R.id.rlyt_search_result) RelativeLayout rlytSearchResult;         // 底部RadioGroup
    @BindView(R.id.iv_head)   LoveImageView ivHead;         // 底部RadioGroup
    @BindView(R.id.tv_name)   TextView      tvName;         // 底部RadioGroup
    @BindView(R.id.tv_sign)   TextView      tvSign;         // 底部RadioGroup
    @BindView(R.id.tv_source) TextView      tvSource;         // 底部RadioGroup

    @BindView(R.id.rycv_cirle) RecyclerView rycvCirle;         // 朋友圈

    //    @BindView(R.id.iv_gender)          ImageView      ivGender;         // 底部RadioGroup
    //    @BindView(R.id.et_content)         EditText       etContent;         // 底部RadioGroup
    //    @BindView(R.id.tv_send_message)    TextView       tvSend;         // 底部RadioGroup
    //    @BindView(R.id.tv_add)             TextView       tvAdd;         // 底部RadioGroup

    @BindView(R.id.tv_search_no_result) TextView tvSearchNoResult;         // 底部RadioGroup


    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        ButterKnife.bind(this);
        //        EventBus.getDefault().register(this);

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

    @OnClick({R.id.tv_right, R.id.tv_more, R.id.tv_send_message, R.id.tv_modif})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                break;
            case R.id.tv_more:
                break;
            case R.id.tv_send_message:
                break;
            case R.id.tv_modif:
                break;
        }
    }

    public static void toThisAvtivity(Activity activity, String userId) {
        Intent intent = new Intent(activity, UserHomePageActivity.class);
        intent.putExtra("userId", userId);
        activity.startActivity(intent);
    }
}
