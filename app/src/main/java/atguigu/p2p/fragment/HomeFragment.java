package atguigu.p2p.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import atguigu.p2p.R;
import atguigu.p2p.base.BaseFragment;
import atguigu.p2p.bean.HomeBean;
import atguigu.p2p.utils.AppNetConfig;
import atguigu.p2p.utils.LoadNet;
import atguigu.p2p.utils.LoadNetHttp;
import atguigu.p2p.utils.MyProgress;
import atguigu.p2p.utils.ThreadPool;
import atguigu.p2p.utils.UiUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    private HomeBean homeBean;
    @InjectView(R.id.home_progress)
    MyProgress homeProgress;

    @Override
    public View initView() {

        View view = UiUtils.getView(R.layout.fragment_home);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromeNet();

    }

    private void getDataFromeNet() {

        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String content) {
//                Toast.makeText(mContext, "成功" + content, Toast.LENGTH_SHORT).show();

                //解析数据
               homeBean = JSON.parseObject(content,HomeBean.class);
//                Log.e("TAG", "HomeFragment success()111111111111"+homeBean.getImageArr().size());
                tvHomeYearrate.setText(Double.parseDouble(homeBean.getProInfo().getYearRate())/100+"%");
                tvHomeProduct.setText(homeBean.getProInfo().getName());
                initProgress(homeBean.getProInfo());
//                //设置banner的图片
                initBanner(homeBean);
            }

            @Override
            public void failure(String error) {
                Toast.makeText(mContext, "失败" + error, Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void initBanner(HomeBean homeBean) {
        List<String>url = new ArrayList<>();
        for(int i =0;i<homeBean.getImageArr().size();i++){
            url.add(AppNetConfig.BASE_URL+homeBean.getImageArr().get(i).getIMAURL());
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(url);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);

        }
    }
    private void initProgress(final HomeBean.ProInfoBean proInfo) {

        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                int progress = Integer.parseInt(proInfo.getProgress());
                for (int i = 0; i <= progress; i++) {
                    SystemClock.sleep(20);
                    homeProgress.setProgress(i);
                }
            }
        });
    }
}
