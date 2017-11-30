package com.labelwall.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.labelwall.latte.ui.recycler.DataConverter;
import com.labelwall.latte.ui.recycler.ItemType;
import com.labelwall.latte.ui.recycler.MultipleFields;
import com.labelwall.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-11-30. 分类数据的转换
 */

public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //获取服务端响应的数据，将数据装换为json数组
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        //将json数据装换为entity对象
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG,false)//设置选中的标记
                    .build();
            ENTITYS.add(entity);
            //设置第一个分类被选中
            ENTITYS.get(0).setField(MultipleFields.TAG,true);
        }
        return ENTITYS;
    }
}
