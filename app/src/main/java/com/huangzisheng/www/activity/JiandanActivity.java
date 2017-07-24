package com.huangzisheng.www.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huangzisheng.www.R;
import com.huangzisheng.www.Tools.Constants;
import com.huangzisheng.www.Tools.GlideCacheUtil;


/**
 * Created by huangzisheng on 2017/7/18.
 */

public class JiandanActivity extends Activity implements View.OnClickListener {
    public JiandanActivity() {
        super();
    }

    ImageView image;
    Button toJiandanMZT,toJiandanWLT;
    TextView cacheSize;
    LinearLayout clearCacheLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiandan);
        initView();
        initEvent();
    }

    private void initEvent() {
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        toJiandanMZT = (Button) findViewById(R.id.toJiandanMZT);
        toJiandanWLT = (Button) findViewById(R.id.toJiandanWLT);
        cacheSize = (TextView) findViewById(R.id.cache_size);
        clearCacheLayout = (LinearLayout) findViewById(R.id.clear_cache);
        clearCacheLayout.setOnClickListener(this);
        toJiandanWLT.setOnClickListener(this);
        toJiandanMZT.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cacheSize.setText(GlideCacheUtil.getInstance().getCacheSize(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toJiandanMZT:
                Intent intent = new Intent(this, JiandanMZTActivity.class);
                intent.putExtra("url", Constants.MET_URL);
                startActivity(intent);
                break;
            case R.id.toJiandanWLT:
                Intent intent1 = new Intent(this, JiandanMZTActivity.class);
                intent1.putExtra("url", Constants.BORING_URL);
                startActivity(intent1);
                break;
            case R.id.clear_cache:
                GlideCacheUtil.getInstance().clearImageAllCache(this);
                cacheSize.setText("0.0M");
                break;
        }
    }
}
