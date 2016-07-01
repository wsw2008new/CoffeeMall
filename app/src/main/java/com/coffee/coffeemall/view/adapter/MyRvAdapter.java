package com.coffee.coffeemall.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.model.bean.HeaderBean;
import com.coffee.coffeemall.model.bean.RecommendBean;
import com.facebook.drawee.view.DraweeView;

import java.util.List;


/**
 * Created by Administrator on 2016/6/30.
 */
public class MyRvAdapter extends RecyclerView.Adapter {

    private static int HEADER_ITEM=0;
    private static int DEFAULT_ITEM=1;
    private static int headerCount=0;

    private final List<HeaderBean> mHeaderBean;
    private final List<RecommendBean> mRecommendBean;
    private final LayoutInflater mInflater;
    private View mHeaderView;

    public MyRvAdapter(Context context, List<HeaderBean> headerBean, List<RecommendBean> recommendBean) {
        mInflater = LayoutInflater.from(context);
        mHeaderBean = headerBean;
        mRecommendBean = recommendBean;
    }

    public void setHeaderView(View headerView){
        mHeaderView = headerView;
        headerCount++;
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0&&mHeaderView!=null){
            return HEADER_ITEM;
        }else{
            return DEFAULT_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==HEADER_ITEM && mHeaderView!=null){
            return new HeaderViewHolder(mHeaderView);
        }else{
            View view = mInflater.inflate(R.layout.item_recyclerview, parent, false);
            return new GenericViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder){
            HeaderPagerAdapter pagerAdapter = new HeaderPagerAdapter(mInflater,mHeaderBean);

            ((HeaderViewHolder) holder).mViewPager.setAdapter(pagerAdapter);

        }else{
            GenericViewHolder genericHolder = (GenericViewHolder) holder;
            genericHolder.textView.setText(mRecommendBean.get(position-headerCount).getTitle());
            RecommendBean.Item cpOne = mRecommendBean.get(position - headerCount).getCpOne();
            RecommendBean.Item cpTwo = mRecommendBean.get(position - headerCount).getCpTwo();
            RecommendBean.Item cpThree = mRecommendBean.get(position - headerCount).getCpThree();
            genericHolder.ivR.setImageURI(Uri.parse(cpOne.getImgUrl()));
            genericHolder.ivLt.setImageURI(Uri.parse(cpThree.getImgUrl()));
            genericHolder.ivLb.setImageURI(Uri.parse(cpTwo.getImgUrl()));

            genericHolder.ivLb.setOnClickListener((v -> {
                if(mListener!=null){
                    mListener.OnItemClick(v,cpTwo);
                }
            }));
            genericHolder.ivLt.setOnClickListener(v -> {
                if(mListener!=null){
                    mListener.OnItemClick(v,cpThree);
                }
            });
            genericHolder.ivR.setOnClickListener(v -> {
                if(mListener!=null){
                    mListener.OnItemClick(v,cpTwo);
                }
            });

        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, RecommendBean.Item item);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }



    @Override
    public int getItemCount() {
        return mRecommendBean.size()+headerCount;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{
        ViewPager mViewPager;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.mViewPager = (ViewPager) itemView.findViewById(R.id.vp);

        }
    }

    class GenericViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        DraweeView ivLb;
        DraweeView ivLt;
        DraweeView ivR;
        public GenericViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.tv_rc);
            ivLb = (DraweeView) itemView.findViewById(R.id.iv_lb);
            ivLt = (DraweeView) itemView.findViewById(R.id.iv_lt);
            ivR = (DraweeView) itemView.findViewById(R.id.iv_r);

        }
    }
}
