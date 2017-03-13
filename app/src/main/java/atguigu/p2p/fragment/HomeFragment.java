package atguigu.p2p.fragment;

import android.view.View;
import android.widget.Toast;

import atguigu.p2p.R;
import atguigu.p2p.base.BaseFragment;
import atguigu.p2p.utils.AppNetConfig;
import atguigu.p2p.utils.LoadNet;
import atguigu.p2p.utils.LoadNetHttp;

/**
 * Created by 陈江峰 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {


    @Override
    public View initView() {
     View  view= View.inflate(mContext,R.layout.fragment_home,null);
//        View view = UiUtils.getView(R.layout.fragment_home);
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
                Toast.makeText(mContext,"成功"+content,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(String error) {
                Toast.makeText(mContext,"失败"+error,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
