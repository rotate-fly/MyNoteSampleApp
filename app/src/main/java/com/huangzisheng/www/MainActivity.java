package com.huangzisheng.www;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.huangzisheng.com.notesample.R;
import com.huangzisheng.www.activity.JiandanActivity;


public class MainActivity extends AppCompatActivity {

    WebView webview;
    ImageView imageView;
    Button btn,toJiandan;

    AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initEvent() {
//        setAnimatorView(imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimatorView(imageView);
            }
        });

        toJiandan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, JiandanActivity.class));
            }
        });

    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(false);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        imageView = (ImageView) findViewById(R.id.image);
        btn = (Button) findViewById(R.id.btn);
        toJiandan = (Button) findViewById(R.id.toJiandan);
    }


    public void clickImage(View v){

        Snackbar snackBar = Snackbar.make(v,"positionX= "+v.getX()+" positionY="+v.getY(), Snackbar.LENGTH_SHORT);
        snackBar.getView().setBackgroundColor(Color.parseColor("#ff4891"));
        snackBar.show();
//        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
//        wechatIntent.setPackage("com.tencent.mm");
////        wechatIntent.setPackage("com.tencent.mobileqq");
//        wechatIntent.setType("text/plain");
//        wechatIntent.putExtra(Intent.EXTRA_TEXT, "分享到微信的内容");
//        startActivity(wechatIntent);
/**调用系统分享----方式1*/
/*
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
*/
/**方式1和方式2效果一致，方式2确保会出现选择框，并添加了选择框的title*/
        /**调用系统分享----方式2*/
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));

    }




    /**属性动画*/
   public void  setAnimatorView(View v){
       AnimatorSet  animatorSet = new AnimatorSet();
       animatorSet.playTogether(
               ObjectAnimator.ofFloat(v, "rotationX", 0, 360),
               ObjectAnimator.ofFloat(v, "rotationY", 0, 180),
               ObjectAnimator.ofFloat(v, "rotation", 0, -90,0),
               ObjectAnimator.ofFloat(v, "translationX", 0, -90),
               ObjectAnimator.ofFloat(v, "translationY", 0, 360),
               ObjectAnimator.ofFloat(v, "scaleX", 1, 1.5f, 0, 1),
               ObjectAnimator.ofFloat(v, "scaleY", 1, 0.5f, 1),
               ObjectAnimator.ofFloat(v, "alpha", 1, 0.5f, 1)
       );
       animatorSet.setDuration(5 * 1000).start();
       animatorSet.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {

           }

           @Override
           public void onAnimationEnd(Animator animator) {

           }

           @Override
           public void onAnimationCancel(Animator animator) {

           }

           @Override
           public void onAnimationRepeat(Animator animator) {

           }
       });

   }

    public void setNormalAnimatorView(View v){

    }

}
