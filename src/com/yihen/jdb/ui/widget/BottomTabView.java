/**   
* @Title: BottomTabView.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author frosty
* @date 2015-5-4 下午1:51:26 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yihen.jdb.R;

/**
 * @ClassName: BottomTabView
 * @Description: TODO
 * @author Frosty
 * @date 2015-5-4 下午1:51:26
 *   
 */
public class BottomTabView extends LinearLayout{

	/**/
	private Context mContext;

	private JAttributes jattr;
	
	private static int oldIndex = 0;//纪录触摸到的item
	
	/*单个radioubutton布局 */
	private LayoutInflater inflater;
	/*单个radioubutton布局参数*/
	private LayoutParams itemLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
	/*回调监听*/
	private OnItemSelectedListener onItemSelectedListener = null;
	
	//当前选择的条目
	private int checkedIndex=0;
	
	//item
	private List<TabItem> itemsList=new ArrayList<BottomTabView.TabItem>();
	
	/**
	 * IMAGE/TEXT 	:	条目的图片/文字  
	 * START/DIF	: 	初始值/和目标的差值
	 * R/G/B		：  	目标颜色RGB格式下的R/G/B
	 */
	private static  int TEXT_START_COLOR=Color.GRAY;
	private static  int	END_COLOR=Color.parseColor("#45c01a");//绿色默认
	private static  int TEXT_START_R=Color.red(TEXT_START_COLOR);
	private static  int TEXT_START_G=Color.green(TEXT_START_COLOR);	
	private static  int TEXT_START_B=Color.blue(TEXT_START_COLOR);
	private static  int TEXT_DIF_R=Color.red(END_COLOR)-TEXT_START_R;
	private static  int TEXT_DIF_G=Color.green(END_COLOR)-TEXT_START_G;	
	private static  int TEXT_DIF_B=Color.blue(END_COLOR)-TEXT_START_B;
	
	
	/**
	 * 包含3个部分
	 * 1.主图标
	 * 2.文本
	 * 3.指示图标（显示消息个数）
	 * @param context
	 */
	public BottomTabView(Context context) {
		this(context,null);
	}

	public BottomTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init(attrs);	
	}
	/**
	 * 构造函数里适用的初始化部分
	 */
	private void init(AttributeSet attrs) {
		
		inflater=LayoutInflater.from(getContext());
		itemLayoutParams.weight=1;
		jattr = readAttribute(attrs);//读属性
		setColor(jattr.tabTextStartColor,jattr.tabTextEndColor);
		//初始化界面
		setOrientation(jattr.itemOrientation);
	}
	
	private void setColor(int startColor,int endColor){
		TEXT_START_COLOR=startColor;//用户设置默认颜色
		TEXT_START_R=Color.red(TEXT_START_COLOR);
		TEXT_START_G=Color.green(TEXT_START_COLOR);	
		TEXT_START_B=Color.blue(TEXT_START_COLOR);
		END_COLOR=endColor;//用户设置的颜色默认
		TEXT_DIF_R=Color.red(END_COLOR)-TEXT_START_R;
		TEXT_DIF_G=Color.green(END_COLOR)-TEXT_START_G;	
		TEXT_DIF_B=Color.blue(END_COLOR)-TEXT_START_B;
	}
	
	/**
	 * 添加条目
	 * @param unSelected 没有选中时的图标
	 * @param selected	 选中时的图标
	 * @param text		 文本内容
	 */
	public void addItem(int unSelectedIcon,int selectedIcon, String text,int unSelectedBg,int selectedBg){
		TabItem rb=new TabItem(unSelectedIcon,selectedIcon,text,unSelectedBg,selectedBg);
		final int i=itemsList.size();
		if(!jattr.useTouch){
			rb.rb_layout.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					setCheckedIndex(i,true);
				}
			});
		}
		addView(rb.rb_layout);
		itemsList.add(rb);
	}

	
	
	/**
	 * @param f 		颜色渐变参考值。
	 */
	private static int getNewColor(float f){
		int newR,newG,newB;
		newR=(int)(TEXT_DIF_R*f)+TEXT_START_R;
		newG=(int)(TEXT_DIF_G*f)+TEXT_START_G;
		newB=(int)(TEXT_DIF_B*f)+TEXT_START_B;
		return Color.rgb(newR, newG, newB);
	}

	
	
	
	
	/**
	 * 两个item 改变透明度
	 * @param leftIndex 	左边的条目索引
	 * @param rightIndex	右边的条目索引
	 * @param alpha			[0,1)透明度
	 */
	public void itemChangeChecked(int leftIndex,int rightIndex,float alpha){
		
		if(jattr.useAlpha){
			if (leftIndex<0||leftIndex>=itemsList.size()||rightIndex<0||rightIndex>=itemsList.size()) {
				return ;
			}
			TabItem a=itemsList.get(leftIndex);
			TabItem b=itemsList.get(rightIndex);
			a.top.setAlpha(alpha);
			a.bottom.setAlpha(1-alpha);
			b.top.setAlpha(1-alpha);
			b.bottom.setAlpha(alpha);
			int aColor=getNewColor(1-alpha);
			int bColor=getNewColor(alpha);
			a.text.setTextColor(aColor);
			b.text.setTextColor(bColor);
		}
		
	}

	
	
	/**
	 * 选择制定索引的条目
	 */
	public void setCheckedIndex(int index,boolean complete) {
		for (int i = 0; i < itemsList.size(); i++) {
			if (i==index) {
				if(jattr.useItemBg){
					itemsList.get(i).itemLayout.setBackgroundResource(jattr.itemSelectedBg);
				}
				itemsList.get(i).setCheceked(true);
				itemsList.get(i).text.setTextColor(END_COLOR);
			}else{
				if(jattr.useItemBg){
					itemsList.get(i).itemLayout.setBackgroundResource(jattr.itemUnSelectedBg);
				}
				itemsList.get(i).setCheceked(false);
				itemsList.get(i).text.setTextColor(TEXT_START_COLOR);
			}
		}
		this.checkedIndex=index;
		if (this.onItemSelectedListener!=null) {
			if(jattr.useTouch){//触摸的话，要判断是否激发监听
				if(complete){
					onItemSelectedListener.onItemSelected(index);
				}
			}else{
				onItemSelectedListener.onItemSelected(index);
			}
		}
	}

	
	/**
	 * 获取选中的条目索引
	 */
	public int getCheckedIndex() {
		return checkedIndex;
	}	
	
	/**
	 * 设置指定索引的条目的消息数量
	 * @param index 条目的索引 
	 * @param count	消息的数量
	 */
	public void setItemNewsCount(int index,int count){
		itemsList.get(index).setNewsCount(count);
	}

	
	
	
	/**
	 * 设置条目变更监听器
	 * @param OnItemSelectedListener
	 */
	public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
		this.onItemSelectedListener = onItemSelectedListener;
	}

	
	
	/**
	 * 自定义的RadioButton
	 */
	private class TabItem{
		RelativeLayout itemLayout ;
		View rb_layout;//单个item
		ImageView top,bottom;	//条目的图片
		TextView text,news;	//条目的文字，消息
		
		
		
		//构造item
		@SuppressWarnings("deprecation")
		public TabItem(int unSelectedIcon,int selectedIcon,String title,int unSelectedBg,int selectedBg){
			
			jattr.itemSelectedBg = selectedBg;
			jattr.itemUnSelectedBg = unSelectedBg;
			
			if(jattr.useIcon){//带图标
				rb_layout = inflater.inflate(R.layout.ui_multitab_with_icon,null);
			}else{//不带图标
				rb_layout = inflater.inflate(R.layout.ui_multitab_only_text,null);
			}
			itemLayout  = (RelativeLayout) rb_layout.findViewById(R.id.itemLayout);
			top=(ImageView)rb_layout.findViewById(R.id.custom_radio_button_image_top);
			bottom=(ImageView)rb_layout.findViewById(R.id.custom_radio_button_image_botom);
			text=(TextView)rb_layout.findViewById(R.id.custom_radio_button_text);
			news=(TextView)rb_layout.findViewById(R.id.custom_radio_button_news);
			itemLayout.setBackgroundDrawable(jattr.background);//背景
			
			int userPadding = jattr.tabPadding;
			if(userPadding != 0){
				setPadding(userPadding, userPadding, userPadding, userPadding);
			}else{
				setPadding(jattr.tabPaddingLeft, jattr.tabPaddingTop, jattr.tabPaddingRight, jattr.tabPaddingBottom);
			}
			int userMargin = jattr.tabSpace;
			if(0!=userMargin){
				itemLayoutParams.leftMargin = userMargin;
				itemLayoutParams.topMargin = userMargin;
				itemLayoutParams.rightMargin = userMargin;
				itemLayoutParams.bottomMargin = userMargin;
			}else{
				itemLayoutParams.leftMargin = jattr.tabSpaceLeft;
				itemLayoutParams.topMargin = jattr.tabSpaceTop;
				itemLayoutParams.rightMargin = jattr.tabSpaceRight;
				itemLayoutParams.bottomMargin = jattr.tabPaddingBottom;
			}
			
			top.setImageResource(jattr.useIcon?unSelectedIcon:0);
			top.setAlpha(1.0f);
			bottom.setImageResource(jattr.useIcon?selectedIcon:0);
			bottom.setAlpha(0.0f);
			
			text.setText(title);
			text.setTextSize(jattr.tabTextSize);
			news.setVisibility(View.INVISIBLE);
			rb_layout.setLayoutParams(itemLayoutParams);

			
		}
		
		/**
		 * 设置选中，取消选时图片的透明度
		 * @param b
		 */
		void setCheceked(boolean b){
			if (b) {
				top.setAlpha(0.0f);
				bottom.setAlpha(1.0f);
			}else{
				top.setAlpha(1.0f);
				bottom.setAlpha(0.0f);
			}
		}
			
		/**
		 * 设置消息数量
		 * @param count 消息数量为0,不显示news的TextView，消息数量>0 显示news的TextView
		 */
		void setNewsCount(int count){
			if (count<=0) {
				news.setVisibility(INVISIBLE);
			}else {
				news.setText(count+"");
				news.setVisibility(VISIBLE);
			}
		}

	}
	
	
	/**
	 * 条目变更监听接口
	 */
	public interface OnItemSelectedListener{
		public void onItemSelected(int position);
	}

	
	/**
	 * 手势触摸反应
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(!jattr.useTouch){
			return true;
		}
		float	viewWidth = getWidth();
		float	viewHight = getHeight();
		
		int action = event.getAction();
		float X =  event.getX();
		float Y =  event.getY();
		
		if(action == MotionEvent.ACTION_DOWN){
		}
		if(action == MotionEvent.ACTION_UP){
			return false;
		}
		
		switch(jattr.itemOrientation){//排列方向
		
		case VERTICAL://树向排列
			//单个高度
			int itemHeight = (int) (viewHight/itemsList.size());
			int selectedY = (int) (Y/itemHeight);
			if(oldIndex != selectedY){
				if( action == MotionEvent.ACTION_UP){
					setCheckedIndex(selectedY,false);
				}else if(action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_DOWN ){
					setCheckedIndex(selectedY,true);
				}
				oldIndex = selectedY;
			}
			
						
			//垂直渐变过度
			
			break;
		case HORIZONTAL:
			//单个item宽度
			int itemWidth = (int) (viewWidth/itemsList.size());
			int selectedX = (int) (X/itemWidth);
			if(oldIndex != selectedX){
				if( action == MotionEvent.ACTION_UP){
					setCheckedIndex(selectedX,false);
				}else if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE){
					setCheckedIndex(selectedX,true);
				}
				oldIndex = selectedX;
			}

			break;
			default :break;
			
		}
		
		return true;
	}
	
	
	
	
	/**
	 * @description 自定义属性的控件主题属性
	 * @author JFrosty
	 */
	private class JAttributes
	{
		private Context mContext;

		private Drawable background;//整体背景，默认透明
		private int tabTextStartColor;//字体默认颜色
		private int tabTextEndColor;//字体结束渐变颜色
		private float tabTextSize;//字体大小
		private int itemSelectedBg;//选中item背景资源id
		private int itemUnSelectedBg;//选不中item背景id
		//内边距
		private int tabPadding;
		private int tabPaddingLeft;
		private int tabPaddingTop;
		private int tabPaddingRight;
		private int tabPaddingBottom;
		
		//外边距
		private int tabSpace;
		private int tabSpaceTop;
		private int tabSpaceLeft;
		private int tabSpaceRight;
		private int tabSpaceBottom;
		
		//样式相关
		private boolean useIcon = true;//有图标
		private int itemOrientation = HORIZONTAL;//默认水平
		private boolean useAlpha = true;//用选中渐变色
		private boolean useTouch = false;//触摸选中
		private boolean useItemBg = true;//item背景
		
		public JAttributes(Context context)
		{
			mContext = context;
			this.background = new ColorDrawable(Color.TRANSPARENT);
			this.tabTextStartColor = Color.GRAY;
			this.tabTextEndColor = Color.GREEN;
			
			this.tabTextSize = dp2px(6);
			
			this.tabPadding  =  dp2px(0);
			this.tabPaddingTop  = dp2px(0);
			this.tabPaddingRight  = dp2px(0);
			this.tabPaddingBottom  = dp2px(0);
			this.tabPaddingLeft = dp2px(0);
			
			
			this.tabSpace  =  dp2px(0);
			this.tabSpace  =  dp2px(0);
			this.tabSpaceLeft  =  dp2px(0);
			this.tabSpaceRight  =  dp2px(0);
			this.tabSpaceBottom  =  dp2px(0);
		}

		//dp转px
		private int dp2px(int dp)
		{
			return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources()
					.getDisplayMetrics());
		}


	}

	/**
	 * 获取主题属性的方法
	 * @return Attributes
	 */
	private JAttributes readAttribute(AttributeSet attrs)
	{
		JAttributes jattr = new JAttributes(mContext);
		
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.JFMultiPurposeTabAttr);
			jattr.itemOrientation  = a.getInteger(R.styleable.JFMultiPurposeTabAttr_itemOrientation, HORIZONTAL);
			jattr.useIcon = a.getBoolean(R.styleable.JFMultiPurposeTabAttr_useIcon, true);
			jattr.useAlpha = a.getBoolean(R.styleable.JFMultiPurposeTabAttr_useAlpha, true);
			jattr.useTouch = a.getBoolean(R.styleable.JFMultiPurposeTabAttr_useTouch, false);
			jattr.useItemBg = a.getBoolean(R.styleable.JFMultiPurposeTabAttr_useItemBg,true);
			
			jattr.tabTextStartColor = a.getColor(R.styleable.JFMultiPurposeTabAttr_tabTextStartColor,
					jattr.tabTextStartColor);
			jattr.tabTextEndColor = a.getColor(R.styleable.JFMultiPurposeTabAttr_tabTextEndColor,
					jattr.tabTextEndColor);
			
			jattr.tabTextSize = a.getDimensionPixelSize(R.styleable.JFMultiPurposeTabAttr_tabTextSize,
					(int) jattr.tabTextSize);

			
			Drawable background = a
					.getDrawable(R.styleable.JFMultiPurposeTabAttr_cusbackgroud);
			if (background != null)
				jattr.background = background;
			
			jattr.tabPadding  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabPadding, jattr.tabPadding);
			jattr.tabPaddingTop  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabPaddingTop, jattr.tabPaddingTop);
			jattr.tabPaddingRight  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabPaddingRight, jattr.tabPaddingRight);
			jattr.tabPaddingBottom  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabPaddingBottom, jattr.tabPaddingBottom);
			jattr.tabPaddingLeft  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabPaddingLeft, jattr.tabPaddingLeft);
			
			jattr.tabSpace  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabSpace, jattr.tabSpace);
			jattr.tabSpaceTop  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabSpaceTop, jattr.tabSpaceTop);
			jattr.tabSpaceRight  = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabSpaceRight, jattr.tabSpaceRight);
			jattr.tabSpaceBottom = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabSpaceBottom, jattr.tabSpaceBottom);
			jattr.tabSpaceLeft = (int) a.getDimension(R.styleable.JFMultiPurposeTabAttr_tabSpaceLeft, jattr.tabSpaceLeft);
			
			a.recycle();
		}

		return jattr;
	}

}
