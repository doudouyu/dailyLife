package life.bean.com.beanlife.gesture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.os.Handler;

import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import life.bean.com.beanlife.R;

/**
 * 手势密码路径绘制
 */
public class GestureDrawline extends View {
    private int mov_x;// 声明起点坐标
    private int mov_y;
    private Paint paint;// 声明画笔
    private Canvas canvas;// 画布
    private Bitmap bitmap;// 位图
    private List<GesturePoint> list;//
    private List<Pair<GesturePoint, GesturePoint>> lineList;// 记录画过的线
    private Map<String, GesturePoint> autoCheckPointMap;// 自动选中的情况点
    private boolean isDrawEnable = true; // 是否允许绘制
    /**
     * 屏幕的宽度和高度
     */
    private int[] screenDispaly;

    private GesturePoint currentPoint;
    private GestureCallBack callBack;
    private StringBuilder passWordSb;
    private boolean isVerify;
    private String passWord;

    public GestureDrawline(Context context, List<GesturePoint> list,
                           boolean isVerify, String passWord, GestureCallBack callBack) {
        super(context);
        //屏幕参数
        screenDispaly = AppUtil.getScreenDispaly(context);
        //画笔
        paint = new Paint(Paint.DITHER_FLAG);//
        //初始化画布--宽高
        bitmap = Bitmap.createBitmap(screenDispaly[0], screenDispaly[0], Bitmap.Config.ARGB_8888); //
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        //设置画笔参数
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(15);//
        paint.setColor(getResources().getColor(R.color.gestureline_green));// 设置默认连线颜色
        paint.setAntiAlias(true);
        //9个点的集合
        this.list = list;
        //画过的线集合
        this.lineList = new ArrayList<Pair<GesturePoint, GesturePoint>>();
        initAutoCheckPointMap();
        this.callBack = callBack;

        this.isVerify = isVerify;
        this.passWord = passWord;
        //密码集
        this.passWordSb = new StringBuilder();
    }

    private void initAutoCheckPointMap() {
        autoCheckPointMap = new HashMap<String, GesturePoint>();
        autoCheckPointMap.put("1,3", getGesturePointByNum(2));
        autoCheckPointMap.put("1,7", getGesturePointByNum(4));
        autoCheckPointMap.put("1,9", getGesturePointByNum(5));
        autoCheckPointMap.put("2,8", getGesturePointByNum(5));
        autoCheckPointMap.put("3,7", getGesturePointByNum(5));
        autoCheckPointMap.put("3,9", getGesturePointByNum(6));
        autoCheckPointMap.put("4,6", getGesturePointByNum(5));
        autoCheckPointMap.put("7,9", getGesturePointByNum(8));
    }

    /**
     * 得到对应的点位
     */
    private GesturePoint getGesturePointByNum(int num) {
        for (GesturePoint point : list) {
            if (point.getNum() == num) {
                return point;
            }
        }
        return null;
    }

    /**
     * 将位图绘制到画布上
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    // 触摸事件
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isDrawEnable == false) {
            return true;
        }
        // 设置默认连线颜色
        paint.setColor(getResources().getColor(R.color.gestureline_green));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mov_x = (int) event.getX();
                mov_y = (int) event.getY();
                /**获取按下的点位*/
                currentPoint = getPointAt(mov_x, mov_y);
                if (currentPoint != null) {
                    /**设置按下的点位的状态，保存密码*/
                    currentPoint.setPointState(Constants.POINT_STATE_SELECTED, 0, 0);
                    passWordSb.append(currentPoint.getNum());
                }
                invalidate();
                //回调方法
                callBack.onPointDown();
                break;
            case MotionEvent.ACTION_MOVE:
                //清楚痕迹
                clearScreenAndDrawList();
                //移动回调
                callBack.onGestureLineMove();
                //获取移动到的位置的点位
                GesturePoint pointAt = getPointAt((int) event.getX(),
                        (int) event.getY());
                if (currentPoint == null && pointAt == null) {
                    return true;
                } else {
                    //由间隔处滑到原点处
                    if (currentPoint == null && pointAt != null) {
                        // 如果为空，那么把手指移动到的点赋值给currentPoint
                        currentPoint = pointAt;
                        /**设置按下的点位的状态，保存密码*/
                        currentPoint.setPointState(Constants.POINT_STATE_SELECTED,
                                0, 0);
                        passWordSb.append(currentPoint.getNum());
                    }
                }
                /***/
                if (pointAt == null
                        || currentPoint.equals(pointAt)
                        || Constants.POINT_STATE_SELECTED == pointAt
                        .getPointState()) {

                    canvas.drawLine(currentPoint.getCenterX(),
                            currentPoint.getCenterY(), event.getX(), event.getY(),
                            paint);
                } else {


                    int x = pointAt.getCenterX() - currentPoint.getCenterX();
                    int y = pointAt.getCenterY() - currentPoint.getCenterY();
                    currentPoint
                            .setPointState(Constants.POINT_STATE_SELECTED, x, y);
                    if (pointAt.equals(list.get(list.size() - 1))) {
                        pointAt
                                .setPointState(Constants.POINT_STATE_SELECTED, 0, 0);
                    }

                    GesturePoint betweenPoint = getBetweenCheckPoint(currentPoint,
                            pointAt);
                    if (betweenPoint != null
                            && Constants.POINT_STATE_SELECTED != betweenPoint
                            .getPointState()) {

                        // 存在中间点并且没有被选中
                        Pair<GesturePoint, GesturePoint> pair1 = new Pair<GesturePoint, GesturePoint>(
                                currentPoint, betweenPoint);
                        lineList.add(pair1);
                        passWordSb.append(betweenPoint.getNum());
                        Pair<GesturePoint, GesturePoint> pair2 = new Pair<GesturePoint, GesturePoint>(
                                betweenPoint, pointAt);
                        lineList.add(pair2);
                        passWordSb.append(pointAt.getNum());
                        betweenPoint.setPointState(Constants.POINT_STATE_SELECTED, 0, 0);
                        currentPoint = pointAt;
                    } else {
                        Pair<GesturePoint, GesturePoint> pair = new Pair<GesturePoint, GesturePoint>(
                                currentPoint, pointAt);
                        lineList.add(pair);
                        passWordSb.append(pointAt.getNum());
                        currentPoint = pointAt;
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawEnable = false;
                if (isVerify) {
                    // 手势密码校验
                    String encodeString = MD5EncodeUtil.MD5Ecode(String.valueOf(passWordSb));
                    String passwordMd5Ecode = MD5EncodeUtil.MD5Ecode(passWord);
                    if (passwordMd5Ecode.equals(encodeString)) {

                        callBack.checkedSuccess();
                    } else {
                        Log.e("TAG", "encode String fali");
                        callBack.checkedFail();
                    }
                } else {
                    //重新绘制界面
                    clearScreenAndDrawList();
                    GesturePoint pointAtUp = getPointAt((int) event.getX(),
                            (int) event.getY());
                    if (pointAtUp != null
                            && currentPoint.equals(pointAtUp)
                            && Constants.POINT_STATE_SELECTED == pointAtUp
                            .getPointState() && passWordSb.length() == 1) {
                        currentPoint.setPointState(Constants.POINT_STATE_WRONG,
                                0, 0);
                    }
                    invalidate();
                    callBack.onGestureCodeInput(passWordSb.toString());
                }
                break;

            default:
                break;
        }
        return true;
    }

    /**
     * @param delayTime 延迟执行时间
     */
    public void clearDrawlineState(long delayTime) {
        if (delayTime > 0) {
            // 绘制红色提示路线
            isDrawEnable = false;
            drawErrorPathTip();
        }
        new Handler().postDelayed(new clearStateRunnable(), delayTime);
    }

    /**
     */
    final class clearStateRunnable implements Runnable {
        public void run() {
            // 重置passWordSb
            passWordSb = new StringBuilder();
            // 清空保存点的集合
            lineList.clear();
            // 重新绘制界面
            clearScreenAndDrawList();
            for (GesturePoint p : list) {
                p.setPointState(Constants.POINT_STATE_NORMAL, 0, 0);
            }
            invalidate();
            isDrawEnable = true;
        }
    }

    /**
     * @param x
     * @param y
     */
    private GesturePoint getPointAt(int x, int y) {

        for (GesturePoint point : list) {
            // 先判断x
            int leftX = point.getLeftX();
            int rightX = point.getRightX();
            if (!(x >= leftX && x < rightX)) {
                continue;
            }

            int topY = point.getTopY();
            int bottomY = point.getBottomY();
            if (!(y >= topY && y < bottomY)) {
                continue;
            }

            // 如果执行到这，那么说明当前点击的点的位置在遍历到点的位置这个地方
            return point;
        }

        return null;
    }

    private GesturePoint getBetweenCheckPoint(GesturePoint pointStart,
                                              GesturePoint pointEnd) {
        int startNum = pointStart.getNum();
        int endNum = pointEnd.getNum();
        String key = null;
        if (startNum < endNum) {
            key = startNum + "," + endNum;
        } else {
            key = endNum + "," + startNum;
        }
        return autoCheckPointMap.get(key);
    }

    /**
     * 清掉屏幕上所有的线，然后画出集合里面的线
     */
    private void clearScreenAndDrawList() {


        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < lineList.size(); i++) {
            int r = 0, x, y;
            if ((lineList.get(i).first.getTopY() - lineList.get(i).first.getBottomY()) / 2 < 0)
                r = -(lineList.get(i).first.getTopY() - lineList.get(i).first.getBottomY()) / 2;
            else {
                r = (lineList.get(i).first.getTopY() - lineList.get(i).first.getBottomY()) / 2;
            }
            float d = (float) 0.707 * r;
            x = lineList.get(i).second.getCenterX() - lineList.get(i).first.getCenterX();
            y = lineList.get(i).second.getCenterY() - lineList.get(i).first.getCenterY();

            if (i == lineList.size() - 1) {
                lineList.get(i).second.setPointState(Constants.POINT_STATE_SELECTED, 0, 0);
                lineList.get(i).first.setPointState(Constants.POINT_STATE_SELECTED, x, y);
            } else {
                lineList.get(i).first.setPointState(Constants.POINT_STATE_SELECTED, x, y);
                lineList.get(i).second.setPointState(Constants.POINT_STATE_SELECTED, x, y);
            }


            if (y == 0 && x > 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX() + r,
                        lineList.get(i).first.getCenterY(), lineList.get(i).second.getCenterX() - r,
                        lineList.get(i).second.getCenterY(), paint);// 画线
            } else if (y == 0 && x < 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX() - r,
                        lineList.get(i).first.getCenterY(), lineList.get(i).second.getCenterX() + r,
                        lineList.get(i).second.getCenterY(), paint);// 画线
            } else if (x == 0 && y > 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX(),
                        lineList.get(i).first.getCenterY() + r, lineList.get(i).second.getCenterX(),
                        lineList.get(i).second.getCenterY() - r, paint);// 画线

            } else if (x == 0 && y < 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX(),
                        lineList.get(i).first.getCenterY() - r, lineList.get(i).second.getCenterX(),
                        lineList.get(i).second.getCenterY() + r, paint);// 画线
            } else if (x > 0 && y > 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() + d,
                        lineList.get(i).first.getCenterY() + d, lineList.get(i).second.getCenterX() - d,
                        lineList.get(i).second.getCenterY() - d, paint);// 画线
            } else if (x > 0 && y < 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() + d,
                        lineList.get(i).first.getCenterY() - d, lineList.get(i).second.getCenterX() - d,
                        lineList.get(i).second.getCenterY() + d, paint);// 画线
            } else if (x < 0 && y > 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() - d,
                        lineList.get(i).first.getCenterY() + d, lineList.get(i).second.getCenterX() + d,
                        lineList.get(i).second.getCenterY() - d, paint);// 画线
            } else if (x < 0 && y < 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() - d,
                        lineList.get(i).first.getCenterY() - d, lineList.get(i).second.getCenterX() + d,
                        lineList.get(i).second.getCenterY() + d, paint);// 画线
            }
        }

    }

    private void drawErrorPathTip() {


        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        paint.setColor(getResources().getColor(R.color.gestureline_red));// 设置默认线路颜色
        if (lineList.size() == 0 && currentPoint != null) {

            currentPoint.setPointState(Constants.POINT_STATE_WRONG, 0, 0);
        }
        for (int i = 0; i < lineList.size(); i++) {
            int r = 0, x, y;
            if ((lineList.get(i).first.getTopY() - lineList.get(i).first.getBottomY()) / 2 < 0)
                r = -(lineList.get(i).first.getTopY() - lineList.get(i).first.getBottomY()) / 2;
            else {
                r = (lineList.get(i).first.getTopY() - lineList.get(i).first.getBottomY()) / 2;
            }

            float d = (float) 0.707 * r;
            x = lineList.get(i).second.getCenterX() - lineList.get(i).first.getCenterX();
            y = lineList.get(i).second.getCenterY() - lineList.get(i).first.getCenterY();


            if (i == lineList.size() - 1) {
                lineList.get(i).second.setPointState(Constants.POINT_STATE_WRONG, 0, 0);
                lineList.get(i).first.setPointState(Constants.POINT_STATE_WRONG, x, y);
            } else {
                lineList.get(i).first.setPointState(Constants.POINT_STATE_WRONG, x, y);
                lineList.get(i).second.setPointState(Constants.POINT_STATE_WRONG, x, y);
            }

            if (y == 0 && x > 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() + r,
                        lineList.get(i).first.getCenterY(), lineList.get(i).second.getCenterX() - r,
                        lineList.get(i).second.getCenterY(), paint);// 画线
            } else if (y == 0 && x < 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX() - r,
                        lineList.get(i).first.getCenterY(), lineList.get(i).second.getCenterX() + r,
                        lineList.get(i).second.getCenterY(), paint);// 画线
            } else if (x == 0 && y > 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX(),
                        lineList.get(i).first.getCenterY() + r, lineList.get(i).second.getCenterX(),
                        lineList.get(i).second.getCenterY() - r, paint);// 画线

            } else if (x == 0 && y < 0) {
                canvas.drawLine(lineList.get(i).first.getCenterX(),
                        lineList.get(i).first.getCenterY() - r, lineList.get(i).second.getCenterX(),
                        lineList.get(i).second.getCenterY() + r, paint);// 画线
            } else if (x > 0 && y > 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() + d,
                        lineList.get(i).first.getCenterY() + d, lineList.get(i).second.getCenterX() - d,
                        lineList.get(i).second.getCenterY() - d, paint);// 画线
            } else if (x > 0 && y < 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() + d,
                        lineList.get(i).first.getCenterY() - d, lineList.get(i).second.getCenterX() - d,
                        lineList.get(i).second.getCenterY() + d, paint);// 画线
            } else if (x < 0 && y > 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() - d,
                        lineList.get(i).first.getCenterY() + d, lineList.get(i).second.getCenterX() + d,
                        lineList.get(i).second.getCenterY() - d, paint);// 画线
            } else if (x < 0 && y < 0) {

                canvas.drawLine(lineList.get(i).first.getCenterX() - d,
                        lineList.get(i).first.getCenterY() - d, lineList.get(i).second.getCenterX() + d,
                        lineList.get(i).second.getCenterY() + d, paint);// 画线
            }
        }
        invalidate();
    }


    public interface GestureCallBack {

        public abstract void onGestureCodeInput(String inputCode);

        public abstract void checkedSuccess();

        public abstract void checkedFail();


        public abstract void onGestureLineMove();

        public abstract void onPointDown();
    }

}
