package com.labelwall.latte.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.labelwall.latte.ec.R;

import java.util.List;

/**
 * Created by Administrator on 2017-11-30.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    //title的转化
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header,item.header);
        helper.setVisible(R.id.more,item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    //content(goods的转换)
    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t返回SectionBean的类型
        final String thumb = item.t.getmGoodsThumb();
        final String name = item.t.getmGoodsName();
        final int id = item.t.getmGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv,name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        //图片加载
        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView);
    }
}
