package kyf.loveapplication.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import kyf.loveapplication.R;
import kyf.loveapplication.data.model.ChateBean;
import kyf.loveapplication.ui.view.LoveImageView;

/**
 * Created by a55 on 2017/11/19.
 */

public class ChateListAdapter extends BaseQuickAdapter<ChateBean, BaseViewHolder> {
    private Activity mActivity;

    public ChateListAdapter(Activity activity, ArrayList<ChateBean> data) {
        super(R.layout.chate_item, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChateBean item) {
//        Logger.d(item.avator);
        ((LoveImageView)helper.getView(R.id.iv_avator)).setImageURI(item.avator);
        helper.setText(R.id.tv_user, item.user);
        helper.setText(R.id.tv_date, item.date);
        helper.setText(R.id.tv_message, item.message);
    }
}
