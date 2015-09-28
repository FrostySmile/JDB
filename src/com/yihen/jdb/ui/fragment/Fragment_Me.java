/**   
* @Title: Fragment_Me.java 
* @Package com.yihen.jdb.ui.fragment 
* @Description: TODO
* @author frosty
* @date 2015-5-5 下午3:14:13 
* @version V1.0   
*/
package com.yihen.jdb.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.yihen.jdb.R;


/**
 * @ClassName: Fragment_Me
 * @Description: 个人中心
 * @author Frosty
 * @date 2015-5-5 下午3:14:13
 *   
 */
public class Fragment_Me extends BaseFragment  {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_me_layout, container, false);
        ViewUtils.inject(this, view);
		return view;
	}

	
}
