package com.huangzisheng.com.notesample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.huangzisheng.com.notesample.application.manage.AcitivityMapping;
import com.huangzisheng.com.notesample.application.manage.ActivityStruct;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayout;
    Map<Integer, List<String>> viewData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        try {
            viewData=  initViewData();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        if(viewData!=null){
            for(int i=0;i<viewData.size();i++){
                Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                btn.setTextSize(16);
                btn.setText(viewData.get(i).get(1));
                btn.setTag(viewData.get(i).get(2));
                btn.setOnClickListener(this);
                linearLayout.addView(btn);
            }
        }



    }

    private Map<Integer, List<String>> initViewData() throws IOException {

            //声明返回值
            Map<Integer, List<String>> map = null;
            InputStream is = null;
            try{
                File file = new File(Environment.getExternalStorageDirectory()+"/"+getApplicationContext().getPackageName()+"/"+
                        "show_data_data.xml");
                if(!file.exists()){
                    is =  getApplicationContext().getAssets().open("codeList.xml");
                }else{
                    is = new FileInputStream(file);
                }
                //首先利用Xml.newPullParser()获取解析对象
                XmlPullParser xmlPullParser= Xml.newPullParser();

                // 解析文件
                xmlPullParser.setInput(is, "UTF-8");

                //获取解析的事件类型
                int eventType=xmlPullParser.getEventType();
                //判断文件解析的是否完毕
               int i=0;
                while(eventType!=XmlPullParser.END_DOCUMENT){
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            map = new HashMap<Integer, List<String>>();
                            break;

                        case XmlPullParser.START_TAG:
                            String tagName=xmlPullParser.getName();
                            if("item".equals(tagName)) {
                                //创建person对象
                                List<String> listString = new ArrayList<String>();
                                String contract_code = xmlPullParser.getAttributeValue(null, "name");
                                String futu_exch_type = xmlPullParser.getAttributeValue(null, "text");
                                String futu_price_step = xmlPullParser.getAttributeValue(null, "id");
//                                String amount_per_hand = xmlPullParser.getAttributeValue(null, "amount_per_hand");
                                listString.add(0, contract_code);
                                listString.add(1, futu_exch_type);
                                listString.add(2, futu_price_step);
//                                listString.add(3, amount_per_hand);
                                Log.e("test","test---"+contract_code+"--"+futu_exch_type);
                                map.put(i,listString);
                                i++;
                            }
                            break;
                        case XmlPullParser.END_TAG:
//                        if("item".equals(xmlPullParser.getName()) && listString!=null&&listString.size()>0){
//                            //把person对象放到集合中去
//                            map.put(listString.get(0).toUpperCase(),listString);
//                            listString.clear();
//                        }
                            break;
                    }
                    eventType=xmlPullParser.next();
                }

            }catch(Exception e){
//            e.printStackTrace();
                return null;
            }finally {
                if(is!=null)
                    is.close();
            }
            return map;
        }

    @Override
    public void onClick(View view) {
     ActivityStruct activityStruct =  AcitivityMapping.getInstance().getAcitiActivityStruct((String) view.getTag());
        Intent intent = new Intent(this,activityStruct.getClass());
        startActivity(intent);
    }
}
