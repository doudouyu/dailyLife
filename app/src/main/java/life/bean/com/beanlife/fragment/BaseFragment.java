package life.bean.com.beanlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
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

    }
}
