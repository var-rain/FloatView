package com.var.demo.floatwindow;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * 应用程序入口点
 *
 * @author var_rain.
 * @date 2018/6/16.
 */
public class App extends Application implements Application.ActivityLifecycleCallbacks {

    /*当前对象的静态实例*/
    private static App instance;
    /*当前显示的Activity*/
    private Activity activity;

    @Override
    public void onCreate() {
        super.onCreate();
        App.instance = this;
        this.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 获取Application对象
     *
     * @return 返回一个App对象实例
     * @see App
     */
    public static App instance() {
        return App.instance;
    }

    /**
     * 显示View
     *
     * @param view 需要显示到Activity的视图
     */
    public void showView(View view) {
        /*Activity不为空并且没有被释放掉*/
        if (this.activity != null && !this.activity.isFinishing()) {
            /*获取Activity顶层视图,并添加自定义View*/
            ((ViewGroup) this.activity.getWindow().getDecorView()).addView(view);
        }
    }

    /**
     * 隐藏View
     *
     * @param view 需要从Activity中移除的视图
     */
    public void hideView(View view) {
        /*Activity不为空并且没有被释放掉*/
        if (this.activity != null && !this.activity.isFinishing()) {
            /*获取Activity顶层视图*/
            ViewGroup root = ((ViewGroup) this.activity.getWindow().getDecorView());
            /*如果Activity中存在View对象则删除*/
            if (root.indexOfChild(view) != -1) {
                /*从顶层视图中删除*/
                root.removeView(view);
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        /*获取当前显示的Activity*/
        this.activity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
