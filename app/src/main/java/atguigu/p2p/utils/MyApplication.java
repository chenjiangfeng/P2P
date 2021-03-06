package atguigu.p2p.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.Log;

/**
 * Created by 陈江峰 on 2017/3/11.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private static Thread mainThread;

    private static int threadid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static int getThreadid() {
        return threadid;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static Handler getHandler() {
        return handler;
    }

     /*
    * 1、onCreate（） 在创建应用程序时创建

      2、onTerminate（） 在模拟环境下执行。当终止应用程序对象时调用，不保证一定被调用，
      当程序是被内核终止以便为其他应用程序释放资源，
      那么将不会提醒，并且不调用应用程序的对象的onTerminate方法而直接终止进程。

        3、onLowMemory（） 低内存时执行。
        好的应用程序一般会在这个方法里面释放一些不必要的资源来应付当后台程序已经终止，(释放一些资源就是那种后台程序)
        前台应用程序内存还不够时的情况。

        4、onConfigurationChanged（Configuration newConfig） 配置改变时触发这个方法。

        5、onTrimMemory（int level）程序在进行内存清理时执行​
    *
    *
    * */

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("TAG", "onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e("TAG","onTrimMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TAG","onConfigurationChanged");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e("TAG", "onTerminate");
    }
}
