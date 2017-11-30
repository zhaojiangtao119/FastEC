package com.labelwall.latte.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.labelwall.latte.ui.recycler.DataConverter;
import com.labelwall.latte.ui.recycler.ItemType;
import com.labelwall.latte.ui.recycler.MultipleFields;
import com.labelwall.latte.ui.recycler.MultipleItemEntity;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017-11-29.
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //TODO index页面数据的 转化
        /*
        * json 的格式：
        *   data:[
        *          {goodsId:1,spanSize:4,banners:["","",""]},
        *          {goodsId:2,text:"",imageUrl:"",spanSize:4},...
        *           ]
        * */
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");
            final ArrayList<String> bannersImages = new ArrayList<>();

            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannersImages.add(banner);
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.BANNERS, bannersImages)
                    .build();
            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
