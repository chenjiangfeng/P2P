package atguigu.p2p.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import atguigu.p2p.R;
import atguigu.p2p.base.BaseFragment;
import atguigu.p2p.fragment.HomeFragment;
import atguigu.p2p.fragment.InvestorFragment;
import atguigu.p2p.fragment.MoreFragment;
import atguigu.p2p.fragment.Property;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    @InjectView(R.id.base_title)
    TextView baseTitle;
    private List<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;
    private String[] st = {"首页", "投资", "资产人", "更多"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //初始化数据
        initData();
        //设置监听
        listener();
    }

    private void listener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_coummnity:
                        position = 1;
                        break;
                    case R.id.rb_user:
                        position = 2;
                        break;
                    case R.id.rb_type:
                        position = 3;
                        break;
                }
                Fragment currentFragment = fragments.get(position);
                baseTitle.setText(st[position]);
                switchFragment(currentFragment);

            }
        });
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment currentFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //切换的不是一个
        if (tempFragment != currentFragment) {

            if (!currentFragment.isAdded()) {//没有添加过

                if (tempFragment != null) {//缓存的不为空
                    ft.hide(tempFragment);
                }

                ft.add(R.id.fl_main, currentFragment);
            } else {//添加过

                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
        } else {
            ft.show(currentFragment);
        }
        ft.commit();
        tempFragment = currentFragment;
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new Property());
        fragments.add(new InvestorFragment());
        fragments.add(new MoreFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}
