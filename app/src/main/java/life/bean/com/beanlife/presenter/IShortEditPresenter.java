package life.bean.com.beanlife.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.AddRememberActivity;
import life.bean.com.beanlife.activity.BaseActivity;
import life.bean.com.beanlife.activity.CalendarActivity;
import life.bean.com.beanlife.entitybiz.IShortEditBiz;
import life.bean.com.beanlife.myInterface.OnGetDateListener;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.utils.RequestFlag;
import life.bean.com.beanlife.view.IShortEditView;

/**
 * 作者 : bean on 2017/4/28/0028.
 * 注释 :快捷记账
 */
public class IShortEditPresenter  {
    private IShortEditView shortEditView;
    private IShortEditBiz shortEditBiz;
    private Context context;
    private ArrayAdapter<String> adapter;

    public IShortEditPresenter(IShortEditView shortEditView) {
        this.shortEditView = shortEditView;
        shortEditBiz = new IShortEditBiz();
        context = BaseActivity.getContextInstance();
    }

    public  void showKeyBord() {

    }

    public void ToCalendarActivity(String value) {
        IntentUtils.startActivity(CalendarActivity.class,"titleName",value);
    }

    public void showBottomDialog(final int what) {
        shortEditBiz.setData(what, new OnGetDateListener() {
            @Override
            public void onSuccessGetData(List list) {
                showMyBottomDialog(what,list);
            }

            @Override
            public void onFailedGetData() {
                shortEditView.showFailedError();
            }
        });
    }
    /**
     * 底部弹出框
     *
     * @param list2
     */
    public void showMyBottomDialog(final int what, final List<String> list2) {
        final Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View view = View.inflate(context, R.layout.layout_bottom_dialog, null);
        dialog.setContentView(view);
        final TextView dialog_title_name = (TextView) view.findViewById(R.id.dialog_title_name);
        TextView tv_add = (TextView) view.findViewById(R.id.tv_add);
        ListView lv_dialog_list_view = (ListView) view.findViewById(R.id.lv_dialog_list_view);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list2);
        lv_dialog_list_view.setAdapter(adapter);
        if (what == RequestFlag.CHOOSE_MEMBER) {
            dialog_title_name.setText("选择成员");
        } else {
            dialog_title_name.setText("选择支付方式");
        }
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddRememberActivity.class);
                if (what == RequestFlag.ADD_MEMBER) {
                    intent.putExtra("hint", RequestFlag.ADD_MEMBER);
                } else {
                    intent.putExtra("hint",  RequestFlag.ADD_PAY_MODE);
                }
                shortEditView.startMyActivity(intent,RequestFlag.ADD_MEMBER);

//                context.startActivityForResult(intent, 1);
            }
        });
        lv_dialog_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title_name = list2.get(position);
                if (what == RequestFlag.CHOOSE_MEMBER) {
                    shortEditView.setAddMemberText(title_name);

                } else {
//
                    shortEditView.setPayModeText(title_name);
                }
                dialog.dismiss();
            }
        });
        dialog.show();//显示对话框

        Window window = dialog.getWindow();
//        window.getDecorView().setPadding(0,0,0,0);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

}
