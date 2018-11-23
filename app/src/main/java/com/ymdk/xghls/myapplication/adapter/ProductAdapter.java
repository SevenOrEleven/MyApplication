package com.ymdk.xghls.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.ymdk.xghls.myapplication.R;
import com.ymdk.xghls.myapplication.bean.AttrConfigsInfo;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ProductAdapter.class.getSimpleName();
    private List<AttrConfigsInfo> classifies;
    private Context context;
    private IsSelectedListener selectedListener;


    public ProductAdapter(Context context, List<AttrConfigsInfo> classifies) {
        this.context = context;
        this.classifies = classifies;
    }

    public void setSelectedListener(IsSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductHolder(View.inflate(context, R.layout.product_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ProductHolder productHolder = (ProductHolder) holder;
        AttrConfigsInfo classify = classifies.get(position);
        final FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        productHolder.title.setText(classify.getTitle());
        productHolder.des.addItemDecoration(new SpaceItemDecoration(dp2px(10)));
        productHolder.des.setLayoutManager(flowLayoutManager);
        FlowAdapter adapter = new FlowAdapter(classify.getSpecList());
        adapter.setSelecterChildListener(new IsSelecterChildListener() {
            @Override
            public void isChildSelected(int childPosition) {
                selectedListener.isSelected(position, childPosition);
            }
        });
        productHolder.des.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return classifies.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView des;

        public ProductHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            des = (RecyclerView) itemView.findViewById(R.id.des);
        }
    }

    class FlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<AttrConfigsInfo.SpecListBean> list;
        private AttrConfigsInfo.SpecListBean selectDes;
        private IsSelecterChildListener selecterChildListener;

        public void setSelecterChildListener(IsSelecterChildListener selecterChildListener) {
            this.selecterChildListener = selecterChildListener;
        }

        public FlowAdapter(List<AttrConfigsInfo.SpecListBean> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(context, R.layout.flow_item, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            TextView textView = ((MyHolder) holder).text;

            final AttrConfigsInfo.SpecListBean des = list.get(position);
            if (des.isSelect) {
                textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_select_back));
            } else {
                textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_back));
            }
            textView.setText(des.getAttrValue());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecterChildListener.isChildSelected(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            private TextView text;

            public MyHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.flow_text);
            }
        }

    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public interface IsSelectedListener {
        void isSelected(int parentPosition, int childPosition);
    }

    interface IsSelecterChildListener {
        void isChildSelected(int childPosition);
    }


}
