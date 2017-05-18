package life.bean.com.beanlife.gesture;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import life.bean.com.beanlife.R;


/**
 * 手势密码容器类
 *
 */
public class GestureContentView extends ViewGroup {

	
	/**
	 * 每个点区域的宽度
	 */
	private int blockWidth;
	private int spacing ;

	/**
	 * 声明一个集合用来封装坐标集合
	 */
	private List<GesturePoint> list;
	private Context context;
	private GestureDrawline gestureDrawline;
	
	/**
	 * 包含9个ImageView的容器，初始化
	 * @param context
	 * @param isVerify 是否为校验手势密码
	 * @param passWord 用户传入密码
	 * @param callBack 手势绘制完毕的回调
	 */
	public GestureContentView(Context context, boolean isVerify,String passWord, GestureDrawline.GestureCallBack callBack) {
		super(context);
		this.blockWidth = dip2px(context,66f); //圆圈的直径dp值
		this.spacing = dip2px(context, 30f); //圆圈之间的距离dp值
		this.list = new ArrayList<GesturePoint>();
		this.context = context;
		// 添加9个图标
		addChild();
		// 初始化一个可以画线的view
		gestureDrawline = new GestureDrawline(context, list, isVerify,passWord, callBack);
	}
	
	public static int dip2px(Context context, float dpValue) {
		// TODO Auto-generated method stub
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	private void addChild(){
		for (int i = 0; i < 9; i++) {
			ImageView image = new ImageView(context);
			image.setBackgroundResource(R.mipmap.gesturewhite);
			this.addView(image,new LayoutParams(blockWidth, blockWidth));
			invalidate();
			// 第几行
			int row = i / 3;
			// 第几列
			int col = i % 3;
			// 定义点的每个属性
			int leftX = col * blockWidth + col * spacing;
			int topY = row * blockWidth + row * spacing;
			int rightX = leftX + blockWidth;
			int bottomY = topY + blockWidth;
			
			//圆圈之间线的位置
			GesturePoint p = new GesturePoint(leftX, rightX, topY, bottomY, image,i+1);
			this.list.add(p);
		}
	}
	
	public void setParentView(ViewGroup parent){
		// 得到屏幕的宽度
		LayoutParams layoutParams = 
				new LayoutParams(blockWidth * 3 + spacing * 2, blockWidth * 3 + spacing * 2);
		parent.addView(gestureDrawline,layoutParams);
		parent.addView(this,layoutParams);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < getChildCount(); i++) {
			//第几行
			int row = i/3;
			//第几列
			int col = i%3;
			View v = getChildAt(i);
			//用于绘制圆圈的位置，d表示X轴方向的偏移量，如果要上下移动整块手绘区域，则将top和bottom参数增加或减小一个合适的偏移量
			int leftX = col * blockWidth + col * spacing;
			int topY = row * blockWidth + row * spacing;
			int rightX = leftX + blockWidth;
			int bottomY = topY + blockWidth;
			
			v.layout(leftX, topY, rightX, bottomY)  ;
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	/**
	 * 保留路径delayTime时间长
	 * @param delayTime
	 */
	public void clearDrawlineState(long delayTime) {
		gestureDrawline.clearDrawlineState(delayTime);
	}

}
