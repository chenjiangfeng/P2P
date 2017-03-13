package atguigu.p2p.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import atguigu.p2p.R;
import atguigu.p2p.utils.AppManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity {

    @InjectView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @InjectView(R.id.welcome)
    RelativeLayout welcome;
    @InjectView(R.id.tv_vesion)
    TextView tvVesion;
    private int vesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        AppManager.getInstance().addStack(this);
        //设置版本号
        setVersion();


        showAnimation();
    }

    private void setVersion() {
        tvVesion.setText(getVesion());

    }

    private void showAnimation() {
        AlphaAnimation at = new AlphaAnimation(0, 1);
        at.setDuration(2000);
        at.setFillAfter(true);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        sa.setFillAfter(true);
//        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(3000);
//        ra.setFillAfter(true);
        AnimationSet as = new AnimationSet(false);
        as.addAnimation(at);
//       as.addAnimation(ra);
        as.addAnimation(sa);
        welcome.startAnimation(as);
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public String getVesion() {

        try {
            //得到包的管理器

            PackageManager packageManager = getPackageManager();

            //得到包的信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //versionCode每次发布都要加上1

            int versionCode = packageInfo.versionCode;
            //得到当前版本号
         String versionName = packageInfo.versionName;

            return versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AppManager.getInstance().removeCurrentActivity();
        AppManager.getInstance().remove(this);

    }
}
