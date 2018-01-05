package kyf.loveapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kyf.loveapplication.R;
import kyf.loveapplication.adapter.ContactsAdapter;
import kyf.loveapplication.ui.activity.AddFreindActivity;
import kyf.loveapplication.ui.view.EasyRecyclerViewSidebar;
import kyf.loveapplication.ui.view.PinnedHeaderListView;

/**
 * 通讯录
 * Created by a55 on 2017/11/2.
 */

public class ContactsFragment extends BaseFragment {

    @BindView(R.id.pinnerListView)      PinnedHeaderListView    listView;           // ListView
    //    @BindView(R.id.msv)                 MultipleStatusView      msView;             // ListView
    //    @BindView(R.id.titleTxt)            TextView                titleTxt;           // Title
    @BindView(R.id.section_sidebar)     EasyRecyclerViewSidebar slidebar;           // 侧边栏
    @BindView(R.id.section_floating_tv) TextView                slideFloatingTxt;   // 悬浮提示框
    //    @BindView(R.id.carCountTxt)         TextView                carCountTxt;        // 购物车数量

    private LinearLayout llytAdd;
    private LinearLayout llytGroup;

    private ContactsAdapter adapter;  //适配器
    //    private ArrayList<AllBrandOrSellerBean> mData;    //数据
    private int             type;

    //    private ChateListAdapter mAdapter;
    private Unbinder mUnbinder;
    private String   avator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        initVars();
        initViews();
        initEvent();
        initData();
        return v;
    }

    private void initVars() {

    }

    private void initViews() {
        View headView = LayoutInflater.from(mActivity).inflate(R.layout.contacts_head, null);
        llytAdd = findViewById(headView, R.id.llyt_add_friend);
        llytGroup = findViewById(headView, R.id.llyt_group);
        listView.addHeaderView(headView);


    }

    private void initEvent() {
        slidebar.setFloatView(slideFloatingTxt);
        slidebar.setOnTouchSectionListener(new EasyRecyclerViewSidebar.OnTouchSectionListener() {
            @Override
            public void onTouchLetterSection(int sectionIndex, EasyRecyclerViewSidebar.EasySection letterSection) {
                slideFloatingTxt.setText(letterSection.letter);

                //计算正确的位置
                int itemId = (int) adapter.getItemId(sectionIndex, 0);
                listView.setSelection(itemId);
            }
        });

        llytAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFreindActivity.toThisAvtivity(mActivity);
            }
        });

        llytGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/12/17

            }
        });
    }

    private void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
