package kyf.loveapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kyf.loveapplication.R;
import kyf.loveapplication.ui.fragment.BaseFragment;
import kyf.loveapplication.ui.fragment.ChatListFragment;
import kyf.loveapplication.ui.fragment.CommunityFragment;
import kyf.loveapplication.ui.fragment.ContactsFragment;
import kyf.loveapplication.ui.fragment.MeFragment;
import kyf.loveapplication.ui.fragment.ProductRelationFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;         // 底部RadioGroup

    private ArrayList<BaseFragment> mFragments;                // 保存Fragment的集合

    private int oldPosition;                                   // 上次checked位置
    private int newPosition;                                   // 最新checked位置
    private long clickTime = 0;                                // 第一次点击的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);

        initVars();
        initViews();
        initData();
    }

    private void initVars() {

    }

    private void initViews() {
        //初始化Fragment
        mFragments = new ArrayList<>(5);
        mFragments.add(new ChatListFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new CommunityFragment());
        mFragments.add(new ProductRelationFragment());
        mFragments.add(new MeFragment());
        //初始化标记
        oldPosition = 0;
        newPosition = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.contain_view, mFragments.get(0)).commit();
        for (int i = 0; i < mFragments.size(); i++) {
            mRadioGroup.getChildAt(i).setId(i);
        }
        mRadioGroup.check(0);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            oldPosition = newPosition;
            newPosition = checkedId;

            switchFragment();
        });
    }

    private void initData() {

    }


    public void switchFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mFragments.get(oldPosition));
        BaseFragment fragment = mFragments.get(newPosition);
        if (fragment.isAdded()) {
            ft.show(fragment);
            if (newPosition == 4) {
//                EventBus.getDefault().post(new UserInfoChangeEvent());

            }
        } else {
            ft.add(R.id.contain_view, fragment);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (System.currentTimeMillis() - clickTime > 2000) {
            Toast.makeText(this, "再点一次退出Love", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    public static void toThisAvtivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
