package com.yihen.jdb.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.yihen.jdb.R;
import com.yihen.jdb.tools.ScreenTools;

/**
 * 
 * @author Frosty
 * @date 2015-4-29 上午10:40:53
 * @description  侧滑菜单
 *
 */
public class SlidingPageMenu extends HorizontalScrollView{

	private int mScreenWidth;//屏幕宽度

	private ViewGroup mMenu;//侧滑菜单
	private ViewGroup mContent;//右边内容
	
	private int mMenuWidth;//菜单宽度
	private int mHalfMenuWidth;//菜单半宽
	
	private  boolean isOpen;//是否滑出，菜单开关状态

	private boolean once;//保证只调用一次

	private JFAttrs  mAttrs= null;//属性集合

	private float mY,mX;//事件拦截处理点
	
	private static int unUsedWidth = 100;//右侧无用区宽度
	
	private static int minWidthPix = 30;//最小滑动距离有效
	
	private static int calcullateNum = 3;//倍数
	
	private ChangeMenuStateListener  listener= null; 
	
	public SlidingPageMenu(Context context) {
		this(context,null);
	}
	
	public SlidingPageMenu(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public SlidingPageMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	


	/**
	 * 
	 * @description 
	 * @param 
	 * @return void
	 */
	private void init(Context context, AttributeSet attrs, int defStyle){
		
		this.listener = (ChangeMenuStateListener) context;
		mScreenWidth = ScreenTools.getScreenWidth(context);
		
		/*读取xml使用的自定义属性*/
		mAttrs = readAttrs(context,attrs,defStyle);
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		if(!once){
				LinearLayout wrapper = (LinearLayout) getChildAt(0);
				mMenu = (ViewGroup) wrapper.getChildAt(0);
				mContent = (ViewGroup) wrapper.getChildAt(1);
				
				mMenuWidth = mScreenWidth - mAttrs.mMenuRightMargin;
				mHalfMenuWidth = mMenuWidth / 2;
				mMenu.getLayoutParams().width = mMenuWidth;
				mContent.getLayoutParams().width = mScreenWidth;
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		// 将菜单隐藏
		this.scrollTo(mMenuWidth, 0);
		once = true;
	}

	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		float currentX,currentY;
		
		switch (action)
		{
			//按下时
			case MotionEvent.ACTION_DOWN:
				currentX = mX = ev.getX();
				currentY = mY = ev.getY();
				if(mX>mScreenWidth-unUsedWidth && isOpen==false){
					return false;
				}
				break;
			//滑动时
			case MotionEvent.ACTION_MOVE:
				currentX = ev.getX();
				currentY = ev.getY();
				if(mX>mScreenWidth-unUsedWidth && isOpen==false){
					return false;
				}
				if(Math.abs(currentX-mX)>Math.abs(currentY-mY)*calcullateNum){//横向距离大
					if(Math.abs(currentX-mX)>minWidthPix){//大于30像素为滑动
						return  true;
					}else{
						return  false;
					}
				}else{
					return false;
				}
			//抬起时
			case MotionEvent.ACTION_UP:
				currentX = ev.getX();
				currentY = ev.getY();

				if(mX>mScreenWidth-unUsedWidth && isOpen==false){
					return false;
				}
				
				if(Math.abs(currentX-mX)>Math.abs(currentY-mY)*calcullateNum){//横向滑动
					if(Math.abs(currentX-mX)>minWidthPix){//大于30像素为滑动
						return  true;
					}else{
						return  false;
					}
				}else{
					return  false;
				}
			}
		return super.onInterceptTouchEvent(ev);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
		// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
		case MotionEvent.ACTION_UP:
			
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
				listener.Tog(false);
			} else
			{
				this.smoothScrollTo(0, 0);
				isOpen = true;
				listener.Tog(true);
			}
			
			return true;
		
		}
		return super.onTouchEvent(ev);
	}

	
	
	
	
	/**
	 * 打开菜单
	 */
	public void openMenu()
	{
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
		listener.Tog(true);
	}

	/**
	 * 关闭菜单
	 */
	public void closeMenu()
	{
		if (isOpen)
		{
			this.smoothScrollTo(mMenuWidth, 0);
			isOpen = false;
			listener.Tog(false);
		}
	}

	/**
	 * 切换菜单状态
	 */
	public void toggle()
	{
		if (isOpen)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}

	
	/**
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuWidth;
		float leftScale = 1 - 0.3f * scale;
		float rightScale = 0.8f + scale * 0.2f;
		
		listener.ChangeAlfa(scale);//header部分头像渐变
				
		if(mAttrs.useMenuAnim){//菜单动画
			ViewHelper.setScaleX(mMenu, leftScale);
			ViewHelper.setScaleY(mMenu, leftScale);
			ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));//透明度渐变动画
			if(mAttrs.useDrawer){//抽屉式切换
				ViewHelper.setTranslationX(mMenu, mMenuWidth * scale);//位移动画
			}else{
				ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);//位移动画
			}
		}
		
		if(mAttrs.useContentAnim){//内容动画
			ViewHelper.setPivotX(mContent, 0);
			ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
			ViewHelper.setScaleX(mContent, rightScale);
			ViewHelper.setScaleY(mContent, rightScale);
		}


	}

	
	
	
	
	
	/**
	 * @description 读属性
	 * @param 
	 * @return JFAttrs
	 */
	private JFAttrs readAttrs(Context context,AttributeSet attrs, int defStyle){
		
		JFAttrs myAttr = new JFAttrs();
		
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.JFSlidingPagerMenu, defStyle, 0);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
				case R.styleable.JFSlidingPagerMenu_mMenuRightMarging:
					// 默认80
					myAttr.mMenuRightMargin = a.getDimensionPixelSize(attr,myAttr.mMenuRightMargin);
					break;
				case R.styleable.JFSlidingPagerMenu_useDrawer:
					myAttr.useDrawer = a.getBoolean(attr, myAttr.useDrawer);
					break;
				case R.styleable.JFSlidingPagerMenu_useMenuAnim:
					myAttr.useMenuAnim = a.getBoolean(attr, myAttr.useMenuAnim);
					break;
				case R.styleable.JFSlidingPagerMenu_useContentAnim:
					myAttr.useContentAnim = a.getBoolean(attr, myAttr.useContentAnim);
					break;
				default:break;
			}
		}
		a.recycle();
		return myAttr;
}

	
	/**
	 * 
	 * @author Frosty
	 * @date 2015-4-29 上午10:44:35
	 * @description  属性封装
	 *
	 */
	private class JFAttrs{
		private int mMenuRightMargin;//右边距
		private boolean useMenuAnim;//菜单动画
		private boolean useContentAnim;//内容动画
		private boolean useDrawer;//抽屉式滑动
		
		public JFAttrs(){
			super();
			this.mMenuRightMargin = any2dp(80);//默认80dp
			this.useContentAnim = true;
			this.useMenuAnim = true;
			this.useDrawer = false;
		}

		private int any2dp (float value){
			int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,getResources().getDisplayMetrics());// 默认为50DP
			return dp;
		}
	}

	
	//开关回调
	public interface ChangeMenuStateListener{
		void Tog(boolean isOpen);
		void ChangeAlfa(float alfa);
	}


	
}
