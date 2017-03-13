package atguigu.p2p.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by 陈江峰 on 2017/3/11.
 */

public class UiUtils {

    public static Context getContext() {
        return MyApplication.getmContext();
    }
    //得到视图
    public static View getView(int getid){
        return View.inflate(getContext(), getid,null);

    }

    public static int getColor(int color){
        return getContext().getResources().getColor(color);
    }

    public static String[] getStringArrary(int stringid){
        return getContext().getResources().getStringArray(stringid);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

}
