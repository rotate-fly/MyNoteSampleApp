package com.huangzisheng.www.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huangzisheng.com.notesample.R;
import com.huangzisheng.www.bean.MztBean;

import java.util.List;


public class JianDanMZTAdapter extends SimpleAdapter<MztBean> {


    private MztBeanLisneter lisneter;

    private OnclickImageLisneter imageLisneter;


    public void setImageLisneter(OnclickImageLisneter imageLisneter) {
        this.imageLisneter = imageLisneter;
    }

    public JianDanMZTAdapter(Context context, List<MztBean> datas, MztBeanLisneter lisneter) {
        super(context, R.layout.adapter_meizitu, datas);
        this.lisneter = lisneter;

    }

    public JianDanMZTAdapter(Context context, List<MztBean> datas) {
        super(context, R.layout.adapter_meizitu, datas);
    }


    @Override
    protected void convert(BaseViewHolder viewHoder, final MztBean item) {
        ImageView imageView = viewHoder.getImageView(R.id.image);
        imageView.setDrawingCacheEnabled(true);

        imageView.setDrawingCacheEnabled(false);
        if (item.getImageUrl().endsWith("gif"))
            Glide.with(context).asGif().load(item.getImageUrl()).into(imageView);
        else
            Glide.with(context).load(item.getImageUrl()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                view.setDrawingCacheEnabled(true);
//                Bitmap obmp = Bitmap.createBitmap(view.getDrawingCache());
//                view.setDrawingCacheEnabled(false);
//                imageLisneter.onClickItemImage(item.getImageUrl());
            }
        });

    }


    public interface OnclickImageLisneter {
        void onClickItemImage(String imageUrl);
    }

    public interface MztBeanLisneter {
        void setDefault(MztBean MztBean);

        void editMztBean(MztBean MztBean);

        void delMztBean(MztBean MztBean);
    }


}
