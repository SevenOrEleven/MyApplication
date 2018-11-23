package com.ymdk.xghls.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ymdk.xghls.myapplication.R;
import com.ymdk.xghls.myapplication.adapter.ProductAdapter;
import com.ymdk.xghls.myapplication.bean.AttrConfigsInfo;
import com.ymdk.xghls.myapplication.bean.BaseEntity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ProductActivity extends BaseActivity implements ProductAdapter.IsSelectedListener {

    //private TextView suspension;
    protected RecyclerView productView;
    private ProductAdapter adapter;
    private List<AttrConfigsInfo> attList = new ArrayList<>();

    private List<String> resultIdList = new ArrayList<>();
    private List<String> resultList = new ArrayList<>();
    private String attrId = "";
    private String attrValue = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productView = (RecyclerView) findViewById(R.id.product_view);
        productView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(ProductActivity.this, attList);
        adapter.setSelectedListener(this);
        productView.setAdapter(adapter);
        String url = "https://www.tianxiadiaochang.com/xyweb//xyGoods/getGoodsAttrConfigs";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("goodsId", "1")
                .addParams("source", "android_ly")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ProductActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Gson gson = new Gson();
                        BaseEntity data = gson.fromJson(response, new TypeToken<BaseEntity>() {
                        }.getType());

                        String s = data.getData();
                        if (data.getStatus() == 1) {
                            List<AttrConfigsInfo> appSCOrderInfos = gson.fromJson(s, new TypeToken<List<AttrConfigsInfo>>() {
                            }.getType());

                            attList.addAll(appSCOrderInfos);
                            for (int i = 0; i < attList.size(); i++) {
                                attList.get(i).getSpecList().get(0).isSelect = true;
                                // TODO: 2018/11/9 换id
                                resultList.add(attList.get(i).getSpecList().get(0).getAttrValue());
                                resultIdList.add(attList.get(i).getSpecList().get(0).getAttrId());
                            }
                            for (int i = 0; i < resultList.size(); i++) {
                                attrValue = attrValue + resultList.get(i) + ",";
                            }
                            for (int j = 0; j < resultIdList.size(); j++) {
                                attrId = attrId + resultIdList.get(j) + ",";
                            }

                            Toast.makeText(ProductActivity.this, attrId, Toast.LENGTH_SHORT).show();
                            Toast.makeText(ProductActivity.this, attrValue, Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
    }


    @Override
    public void isSelected(int parentPosition, int childPosition) {
        for (int j = 0; j < attList.get(parentPosition).getSpecList().size(); j++) {
            attList.get(parentPosition).getSpecList().get(j).isSelect = false;
        }
        attList.get(parentPosition).getSpecList().get(childPosition).isSelect = true;
        attrValue = "";
        attrId = "";
        for (int i = 0; i < attList.size(); i++)
            for (int j = 0; j < attList.get(i).getSpecList().size(); j++)
                if (attList.get(i).getSpecList().get(j).isSelect) {
                    attrId = attrId + attList.get(i).getSpecList().get(j).getAttrId() + ",";
                    // TODO: 2018/11/9 换id
                    attrValue = attrValue + attList.get(i).getSpecList().get(j).getAttrValue() + ",";
                }

        adapter = new ProductAdapter(ProductActivity.this, attList);
        Toast.makeText(this, attrId, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, attrValue, Toast.LENGTH_SHORT).show();

        adapter.setSelectedListener(this);
        productView.setAdapter(adapter);
    }
}
