/**   
* @Title: AnimationTools.java 
* @Package com.yihen.jdb.common.tools 
* @Description: TODO
* @author fsjohnhuang
* @date 2015-4-29 下午2:45:45 
* @version V1.0   
*/
package com.yihen.jdb.tools;

import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.yihen.jdb.R;

/**
 * @ClassName: AnimationTools
 * @Description: 动画处理类
 * @author Frosty
 * @date 2015-4-29 下午2:45:45
 *   
 */
public class AnimationTools {

	/**
	 * 
	 * @description 进度等待、加载中的动画
	 * @param 
	 * @return void
	 */
	public static void loadingAnim(ImageView imgv)
	{
		try {
			AnimationDrawable animDraw = (AnimationDrawable) imgv.getDrawable();
			if(animDraw == null){
				imgv.setImageResource(R.drawable.anim_progress);
				animDraw = (AnimationDrawable) imgv.getDrawable();
			}
			animDraw.start();
		} catch (Exception e) {}
		
		
	}
	
	
	/**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts,int timeMills){
	    	Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
	    	translateAnimation.setInterpolator(new CycleInterpolator(counts));
	    	translateAnimation.setDuration(timeMills);
	    	return translateAnimation;
    }

	
}
