package atguigu.p2p.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 陈江峰 on 2017/3/11.
 */

public class AppManager {

    private AppManager() {
    }


    private static AppManager appManager = new AppManager();

    public static AppManager getInstance() {
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    //增加Activity
    public void addStack(Activity activity) {
        if (activity != null) {
            stack.add(activity);
        }
    }

    //删除Activity 就是删除一类的Activity
    public void removeActivity(Activity activity) {
        //校验
        if (activity != null) {
            for (int i = stack.size() - 1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish();
                    stack.remove(currentActivity);
                }
            }
        }
    }

    //删除所有的Activity
    public   void removeAll() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Activity currentActivity = stack.get(i);
            currentActivity.finish();
            stack.remove(currentActivity);
        }
    }
    //删除栈顶的一个Activity
    public void removeCurrentActivity(){

        Activity activity = stack.get(stack.size()-1);
        activity.finish();
        stack.remove(activity);
    }
    //得到总的Activity的总数

    public int getStackSize(){
        return stack.size();
    }

}
