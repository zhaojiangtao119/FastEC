package com.labelwall.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.labelwall.latte.delegates.LatteDelegate;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ec.R2;
import com.labelwall.latte.net.RestClient;
import com.labelwall.latte.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-11-30.  分类的内容
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> contentData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if(args != null){
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId){
        final Bundle args  = new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //初始化RecyclerView
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        initData();
    }

    //请求服务端获取content数据
    private void initData(){
        RestClient.builder()
                //TODO 请求content数据,需要contentId
                .url("分类内容的数据")

                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        contentData = SectionDataConverter.convert(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,R.layout.item_section_header,contentData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }

}
