package com.labelwall.latte.ui.refresh;

/**
 * Created by Administrator on 2017-11-30.  分页对象
 */

public class PagingBean {

    //链式设置属性值得改造，将setter方法的返回值改变为当前类

    private int mPageIndex = 0;
    private int mTotleNums = 0;
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotleNums() {
        return mTotleNums;
    }

    public PagingBean setTotleNums(int mTotleNums) {
        this.mTotleNums = mTotleNums;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PagingBean addIndex(){
        mPageIndex++;
        return this;
    }
}
