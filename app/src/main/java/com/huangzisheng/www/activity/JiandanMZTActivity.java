package com.huangzisheng.www.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import com.huangzisheng.www.R;
import com.huangzisheng.www.Tools.Constants;
import com.huangzisheng.www.adapter.JianDanMZTAdapter;
import com.huangzisheng.www.bean.MztBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzisheng on 2017/7/18.
 */

public class JiandanMZTActivity extends Activity implements View.OnClickListener {

    private ScrollView scrollView;
    private RecyclerView recyclerView;
    private Button previous, jump, next;
    private EditText pageNum;

    private String urlStr;//有上一个activity传过来的url

    private int totlePage = 0;
    private int curPage;
    private String url;

    private JianDanMZTAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiandan_meizitu);
        initView();
        initEvent();
    }

    private void initEvent() {

        urlStr = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(urlStr))
            urlStr = Constants.BORING_URL;
        threadToGetData(urlStr);

//        pageNum.setText(totlePage);
        previous.setOnClickListener(this);
        jump.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    private void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        previous = (Button) findViewById(R.id.previous);
        jump = (Button) findViewById(R.id.jump);
        next = (Button) findViewById(R.id.next);
        pageNum = (EditText) findViewById(R.id.page_num_edit);
    }

    private void threadToGetData(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("connectUrl",urlStr);
                    getDatas(urlStr);
                } catch (IOException e) {

                }
            }
        }).start();
    }

    private void getDatas(String urlStr) throws IOException {
        //http://jandan.net/ooxx/page-187#comments
        Log.d("mzt", "---" + urlStr +"-totlePage = "+totlePage +"---curPage=" + curPage);
        Document document = Jsoup.connect(urlStr).get();
        Elements elements = document.select("div.cp-pagenavi");
        Element element = elements.first();
        Element first = element.select("a[href]").first();
        if (totlePage == 0) {
            totlePage = Integer.parseInt(first.text().replace("[]",""));
            totlePage+=1;
            curPage = totlePage;
            url = first.attr("href");
        }

        final List<MztBean> mztBeanList = new ArrayList<>();
        if (mztBeanList.size() > 0) {
            mztBeanList.clear();
        }
        Elements imageDiv = document.select("div.text");
        for (Element e : imageDiv) {

            String id = e.select("a[href]").get(0).text();
            String url = e.select("a[href]").get(0).select("img").attr("src");//e.select("p").
            MztBean mztBean = new MztBean(url, id, "");
            mztBeanList.add(mztBean);
        }
        if (mztBeanList.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    showData(mztBeanList);
                }
            });

        }


    }

    private void showData(List<MztBean> mztBeanList) {

        if (mAdapter == null) {
            mAdapter = new JianDanMZTAdapter(this, mztBeanList);

            //设置adapter
            recyclerView.setAdapter(mAdapter);
            //设置布局管理器
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            //设置Item增加、移除动画
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //添加分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//            recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        } else {
            mAdapter.refreshData(mztBeanList);
            recyclerView.setAdapter(mAdapter);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous://http://jandan.net/ooxx/page-188#comments
                if (curPage == 0)
                    Snackbar.make(view, "到底了", Snackbar.LENGTH_SHORT).show();
                else {
                    url = url.replace("" + curPage, "" + (curPage - 1));
                    if(!url.contains("http"))
                        url="http:"+url;
                    threadToGetData(url);
                    curPage--;

                }
                break;
            case R.id.jump:
                url= url.replace("" + curPage, "" + pageNum.getText());
                if(!url.contains("http"))
                    url="http:"+url;
                threadToGetData(url);
                curPage = Integer.parseInt(pageNum.getText().toString());
                break;
            case R.id.next:
                if (curPage >= totlePage)
                    Snackbar.make(view, "到顶了", Snackbar.LENGTH_SHORT).show();
                else {
                    url = url.replace("" + curPage, "" + (curPage + 1));
                    if(!url.contains("http"))
                        url="http:"+url;
                    threadToGetData(url);
                    curPage++;
                }
                break;
        }
    }
}
