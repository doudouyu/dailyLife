package life.bean.com.beanlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import life.bean.com.beanlife.view.IBaseView;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener,IBaseView{
    public Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        View view = View.inflate(context, getLayoutId(), null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        dealCommon();
    }

    public abstract int getLayoutId() ;

    public abstract void initView(View view);


    public abstract void initData();

    public  void dealCommon(){}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.selected:
//                getFragmentManager().popBackStack();
//                break;
            default:
                onInnerClick(v);
                break;
        }
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void RefreshView(List list) {

    }

    @Override
    public void showFailedError() {

    }

    public void onInnerClick(View v){}
    public void showToast(String title) {
        Toast.makeText(context,title,Toast.LENGTH_SHORT).show();
    }
}
