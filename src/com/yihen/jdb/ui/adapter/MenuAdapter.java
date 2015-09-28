/**   
* @Title: MenuAdapter.java 
* @Package com.yihen.jdb.ui.adapter 
* @Description: TODO
* @author frosty
* @date 2015-5-4 下午5:00:48 
* @version V1.0   
*/
package com.yihen.jdb.ui.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yihen.jdb.R;

/**
 * @ClassName: MenuAdapter
 * @Description: TODO
 * @author Frosty
 * @date 2015-5-4 下午5:00:48
 *   
 */
public class MenuAdapter extends BaseAdapter{

	private Context mContext;
	private Handler mHandler;
	private List<Map<String,Object>> menuList = null;
	private LayoutInflater inflater;
	
	public MenuAdapter(Context context,List<Map<String,Object>> menuList){
		this(context,menuList,null);
	}
	
	public MenuAdapter(Context context,List<Map<String,Object>> menuList,Handler mHandler){
		this.mContext = context;
		this.menuList = menuList;
		this.mHandler = mHandler;
		this.inflater = LayoutInflater.from(context);
	}

	/*
	* Title: getCount
	* Description: 
	* @return 
	* @see android.widget.Adapter#getCount() 
	*/
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuList == null ? 0 : menuList.size();
	}

	/*
	* Title: getItem
	* Description: 
	* @param arg0
	* @return 
	* @see android.widget.Adapter#getItem(int) 
	*/
	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return menuList.get(index).get("title");
	}

	/*
	* Title: getItemId
	* Description: 
	* @param arg0
	* @return 
	* @see android.widget.Adapter#getItemId(int) 
	*/
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	/*
	* Title: getView
	* Description: 
	* @param arg0
	* @param arg1
	* @param arg2
	* @return 
	* @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) 
	*/
	@Override
	public View getView(final int position, View viewFrame, ViewGroup parent) {
		
		MenuItem menuItem = null;
		
		if(viewFrame == null)
		{
			viewFrame = inflater.inflate(R.layout.adapter_menu_item, null);
			
			menuItem = new MenuItem();
			menuItem.menuLayout = (RelativeLayout) viewFrame.findViewById(R.id.menuLayout);
			menuItem.menuIcon = (ImageView) viewFrame.findViewById(R.id.menu2_icon_left);
			menuItem.menuTitle = (TextView) viewFrame.findViewById(R.id.menu_title);
			viewFrame.setTag(menuItem);
		}else{
			menuItem = (MenuItem) viewFrame.getTag();
		}
		
		//为对应位置position的item赋值
		menuItem.menuIcon.setBackgroundResource( (Integer)menuList.get(position).get("menuIcon"));
		menuItem.menuTitle.setText(menuList.get(position).get("menuTitle").toString());
		
		menuItem.menuLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, menuList.get(position).get("menuTitle").toString(), Toast.LENGTH_SHORT).show();
				if(mHandler != null){
					mHandler.sendEmptyMessage(position);
				}
			}
		});
		return viewFrame;
	}
	
	
	/**
	 * 
	 * @ClassName: MenuItemFrame
	 * @Description: item视图框架，用于缓存
	 * @author Frosty
	 * @date 2015-5-4 下午5:11:42
	 *
	 */
	private class MenuItem{
		private RelativeLayout menuLayout;
		private ImageView menuIcon;
		private TextView menuTitle;
	}

}
