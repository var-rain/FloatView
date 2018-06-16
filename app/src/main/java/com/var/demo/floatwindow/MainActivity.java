package com.var.demo.floatwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * 主界面
 *
 * @author var_rain.
 * @date 2018/6/16.
 */
public class MainActivity extends AppCompatActivity {

    /*显示提示框按钮*/
    private Button showTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.initViews();
        this.initActions();
    }

    /**
     * 初始化视图控件
     */
    @SuppressLint("InflateParams")
    private void initViews() {
        this.showTips = findViewById(R.id.act_main_but_show_tips);
    }

    /**
     * 初始化事件监听
     */
    private void initActions() {
        this.showTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.createAndStart();
            }
        });
    }

    /**
     * 创建View并启动动画
     */
    @SuppressLint("InflateParams")
    private void createAndStart() {
        /*创建提示消息View*/
        final View view = LayoutInflater.from(this).inflate(R.layout.view_top_msg, null);
        /*创建属性动画,从下到上*/
        ObjectAnimator bottomToTop = ObjectAnimator.ofFloat(view, "translationY", 0, -dp2px(80)).setDuration(500);
        /*创建属性动画,从上到下*/
        ObjectAnimator topToBottom = ObjectAnimator.ofFloat(view, "translationY", -dp2px(80), 0).setDuration(500);
        /*初始化动画组合器*/
        AnimatorSet animator = new AnimatorSet();
        /*组合动画*/
        animator.play(bottomToTop).after(topToBottom).after(2000);
        /*添加动画结束回调*/
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                /*删除View*/
                App.instance().hideView(view);
            }
        });
        /*添加View到当前显示的Activity*/
        App.instance().showView(view);
        /*启动动画*/
        animator.start();
    }

    /**
     * 从dp单位转换为px
     *
     * @param dp dp值
     * @return 返回转换后的px值
     */
    private int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
