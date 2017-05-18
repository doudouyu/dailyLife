package life.bean.com.beanlife.gesture;

import android.widget.ImageView;

import life.bean.com.beanlife.R;

public class GesturePoint {
	/**
	 * 左边x的值
	 */
	private int leftX;
	/**
	 * 右边x的值
	 */
	private int rightX;
	/**
	 * 上边y的值
	 */
	private int topY;
	/**
	 * 下边y的值
	 */
	private int bottomY;
	/**
	 * 这个点对应的ImageView控件
	 */
	private ImageView image;

	/**
	 * 中心x值
	 */
	private int centerX;

	/**
	 * 中心y值
	 */
	private int centerY;

	/**
	 * 状态值
	 */
	private int pointState;

	/**
	 * 代表这个Point对象代表的数字，从1开始(直接感觉从1开始)
	 */
	private int num;

	public GesturePoint(int leftX, int rightX, int topY, int bottomY,
			ImageView image, int num) {
		super();
		this.leftX = leftX;
		this.rightX = rightX;
		this.topY = topY;
		this.bottomY = bottomY;
		this.image = image;
		this.centerX = (leftX + rightX) / 2;
		this.centerY = (topY + bottomY) / 2;
		this.num = num;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}

	public int getBottomY() {
		return bottomY;
	}

	public void setBottomY(int bottomY) {
		this.bottomY = bottomY;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getPointState() {
		return pointState;
	}

	public void setPointState(int state, int x, int y) {

		pointState = state;
		switch (state) {

		case Constants.POINT_STATE_NORMAL:
			this.image.setBackgroundResource(R.mipmap.gesturewhite);
			break;
		case Constants.POINT_STATE_SELECTED:

			// 以原点为圆心，右下为正，左上为负
			if (y == 0 && x > 0) {
				this.image.setBackgroundResource(R.mipmap.blueright);
			} else if (y == 0 && x < 0) {
				this.image.setBackgroundResource(R.mipmap.blueleft);
			} else if (x == 0 && y > 0) {
				this.image.setBackgroundResource(R.mipmap.bluedown);
			} else if (x == 0 && y < 0) {
				this.image.setBackgroundResource(R.mipmap.blueup);
			} else if (x > 0 && y > 0) {
				this.image.setBackgroundResource(R.mipmap.bluerightdown);
			} else if (x < 0 && y < 0) {
				this.image.setBackgroundResource(R.mipmap.blueleftup);
			} else if (x < 0 && y > 0) {
				this.image.setBackgroundResource(R.mipmap.blueleftdown);
			} else if (x > 0 && y < 0) {
				this.image.setBackgroundResource(R.mipmap.bluerightup);
			} else if (x == 0 && y == 0) {
				this.image.setBackgroundResource(R.mipmap.bluedot);
			}

			break;
		case Constants.POINT_STATE_WRONG:

			if (y == 0 && x > 0) {
				this.image.setBackgroundResource(R.mipmap.redright);
			} else if (y == 0 && x < 0) {
				this.image.setBackgroundResource(R.mipmap.redleft);
			} else if (x == 0 && y > 0) {
				this.image.setBackgroundResource(R.mipmap.reddown);
			} else if (x == 0 && y < 0) {
				this.image.setBackgroundResource(R.mipmap.redup);
			} else if (x > 0 && y > 0) {
				this.image.setBackgroundResource(R.mipmap.redrightdown);
			} else if (x < 0 && y < 0) {
				this.image.setBackgroundResource(R.mipmap.redleftup);
			} else if (x < 0 && y > 0) {
				this.image.setBackgroundResource(R.mipmap.redleftdown);
			} else if (x > 0 && y < 0) {
				this.image.setBackgroundResource(R.mipmap.redrightup);
			} else if (x == 0 && y == 0) {
				this.image.setBackgroundResource(R.mipmap.reddot);
			}
			break;
		default:
			break;
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bottomY;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + leftX;
		result = prime * result + rightX;
		result = prime * result + topY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GesturePoint other = (GesturePoint) obj;
		if (bottomY != other.bottomY)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (leftX != other.leftX)
			return false;
		if (rightX != other.rightX)
			return false;
		if (topY != other.topY)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [leftX=" + leftX + ", rightX=" + rightX + ", topY="
				+ topY + ", bottomY=" + bottomY + "]";
	}
}
