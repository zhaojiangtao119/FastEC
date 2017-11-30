package com.labelwall.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-11-29. 数据转换的约束
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {//设置需要转化的jsonStr数据
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("Data is null");
        }
        return mJsonData;
    }
}
