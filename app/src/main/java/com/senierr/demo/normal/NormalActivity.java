package com.senierr.demo.normal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.senierr.rvadapter.RVAdapter;
import com.senierr.rvadapter.ViewHolderWrapper;
import com.senierr.rvadapter.link.OneToManyLink;
import com.senierr.demo.BaseRecyclerViewActivity;
import com.senierr.demo.R;
import com.senierr.demo.bean.NormalBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalActivity extends BaseRecyclerViewActivity {

    private RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        initRecyclerView();
        initView();
        loadData();
    }

    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        rvAdapter = new RVAdapter();
        rvAdapter.assign(NormalBean.class)
                .to(new NormalWrapper1(),
                        new NormalWrapper2())
                .by(new OneToManyLink<NormalBean>() {
                    @Override
                    public Class<? extends ViewHolderWrapper<NormalBean>> onAssigned(@NonNull NormalBean item) {
                        if (item.getId() < 3) {
                            return NormalWrapper1.class;
                        }
                        return NormalWrapper2.class;
                    }
                });
        recyclerView.setAdapter(rvAdapter);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            NormalBean normalBean = new NormalBean();
            Random random = new Random();
            normalBean.setId(random.nextInt(4) + 1);
            normalBean.setContent("Item: " + i);
            list.add(normalBean);
        }
        rvAdapter.setItems(list);
        rvAdapter.notifyDataSetChanged();
    }
}