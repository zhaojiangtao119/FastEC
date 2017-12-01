package com.labelwall.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-11-30. 分类内容的数据转换
 */

public class SectionDataConverter {

    final static List<SectionBean> convert(String jsonStr) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(jsonStr).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");
            //添加content title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            dataList.add(sectionTitleBean);

            //获取每一个栏目中的商品信息
            final JSONArray goodsData = data.getJSONArray("goods");
            final int goodSize = goodsData.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goodsData.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumn = contentItem.getString("goods_thumb");
                //包装goods
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setmGoodsId(goodsId);
                itemEntity.setmGoodsName(goodsName);
                itemEntity.setmGoodsThumb(goodsThumn);
                //添加content 内容
                dataList.add(new SectionBean(itemEntity));
            }
        }
        return dataList;
    }

}
