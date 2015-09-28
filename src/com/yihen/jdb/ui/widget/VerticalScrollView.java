/**   
* @Title: VerticalScrollView.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author fsjohnhuang
* @date 2015-4-29 下午12:30:30 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

import com.yihen.jdb.tools.ScreenTools;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * @ClassName: VerticalScrollView
 * @Description: 弹簧效果
 * @author Frosty
 * @date 2015-4-29 下午12:30:30
 *   
 */
public class VerticalScrollView extends ScrollView{

	
	private View inner;// 孩子View  
	  
    private float y;// 点击时y坐标  
    
    private float interceptX,interceptY;
  
    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)  
  
    private boolean isCount = false;// 是否开始计算  
  
    private final static int PRCISION = 3;//像素差距倍数
    
    private static int minWidthPix = 30;//最小滑动距离有效
    
    public VerticalScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    /*** 
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate 
     * 方法，也应该调用父类的方法，使该方法得以执行. 
     */  
    @Override  
    protected void onFinishInflate() {  
        if (getChildCount() > 0) {  
            inner = getChildAt(0);  
        }  
    }  
    
  //如果是滑动时拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float currentY = 0,currentX = 0;
        boolean flag = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                interceptY = ev.getY();
                interceptX = ev.getX();
                // 手指按下
                if (isNeedAnimation()) {  
                    animation();  
                    isCount = false;  
                }  
                flag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = ev.getY();
                currentX = ev.getX();
                if(Math.abs(interceptY-currentY)>=Math.abs(interceptX-currentX)*PRCISION){
                    if(Math.abs(interceptY-currentY)>minWidthPix){
                    		flag = true;
                    }else{
                    		flag = false;
                    }
                }else {
                    flag = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开.
                currentY = ev.getY();
                currentX = ev.getX();
                if(Math.abs(interceptY-currentY)>=Math.abs(interceptX-currentX)*PRCISION){
                    	if(Math.abs(interceptY-currentY)>minWidthPix){//大于100像素为滑动
                    		flag = true;
                    	}else{
                    		flag = false;
                    	}
                }else {
                    flag = false;
                }
                interceptX = 0;
                interceptY = 0;
                break;
        }
        return flag;
    }

    @Override  
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {  
        super.onScrollChanged(x, y, oldx, oldy);  
    }  
  
    /*** 
     * 监听touch 
     */  
    @Override  
    public boolean onTouchEvent(MotionEvent ev) {  
        if (inner != null) {  
        	 int action = ev.getAction();  
             switch (action) {  
             case MotionEvent.ACTION_DOWN:  
             	 // 手指松开.  
                 if (isNeedAnimation()) {  
                     animation();  
                     isCount = false;  
                 }  
                 break;  
             case MotionEvent.ACTION_UP:  
                 // 手指松开.  
                 if (isNeedAnimation()) {  
                     animation();  
                     isCount = false;  
                 }  
                 break;  
             /*** 
              * 排除出第一次移动计算，因为第一次无法得知y坐标， 在MotionEvent.ACTION_DOWN中获取不到， 
              * 因为此时是MyScrollView的touch事件传递到到了LIstView的孩子item上面.所以从第二次计算开始. 
              * 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0. 之后记录准确了就正常执行. 
              */  
             case MotionEvent.ACTION_MOVE:  
            	 
                 final float preY = y;// 按下时的y坐标  
                 float nowY = ev.getY();// 时时y坐标  
                 int deltaY = (int) (preY - nowY);// 滑动距离  
                 if (!isCount) {  
                     deltaY = 0; // 在这里要归0.  
                 }  
                 y = nowY;  
                 // 当滚动到最上或者最下时就不会再滚动，这时移动布局  
                 if (isNeedMove()) {  
                     // 初始化头部矩形  
                     if (normal.isEmpty()) {  
                         // 保存正常的布局位置  
                         normal.set(inner.getLeft(), inner.getTop(),  
                         inner.getRight(), inner.getBottom());  
                     }  
                     // 移动布局  
                     inner.layout(inner.getLeft(), inner.getTop() - deltaY/3,  
                     inner.getRight(), inner.getBottom() - deltaY/3);  
                 }
                 isCount = true;  
                 break;  
             default:  
                 break;  
             }  
        }  
  
        return super.onTouchEvent(ev);  
    }  
  
    /*** 
     * 回缩动画 
     */  
    public void animation() {  
        // 开启移动动画  
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),  
                normal.top);  
        ta.setInterpolator(new DecelerateInterpolator());//减速
        ta.setDuration(350);  
        inner.startAnimation(ta);  
        // 设置回到正常的布局位置  
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);  
        normal.setEmpty();  
  
    }  
  
    // 是否需要开启动画  
    public boolean isNeedAnimation() {  
        return !normal.isEmpty();  
    }  
  
    /*** 
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度 
     *  
     * getHeight()：获取的是屏幕的高度 
     *  
     * @return 
     */  
    public boolean isNeedMove() {  
        int offset = inner.getMeasuredHeight() - getHeight();  
        int scrollY = getScrollY();  
        // 0是顶部，后面那个是底部  
//        if((((nowY-preY)>0) && (scrollY == 0))||((scrollY == offset) && ((nowY-preY)<0))){
//        		return true;
//        }
        if (scrollY == 0 || scrollY == offset) {  
            return true;  
        }  
        return false;  
    }


	
}
