/**   
* @Title: Fragment_Me.java 
* @Package com.yihen.jdb.ui.fragment 
* @Description: TODO
* @author frosty
* @date 2015-5-5 下午3:14:13 
* @version V1.0   
*/
package com.yihen.jdb.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yihen.jdb.R;
import com.yihen.jdb.bean.ServiceProjectViewModel;
import com.yihen.jdb.ui.activity.BusinessSearchActivity;
import com.yihen.jdb.ui.adapter.ServiceMenuAdapter;

/**
 * @ClassName: Fragment_Me
 * @Description: 个人中心
 * @author Frosty
 * @date 2015-5-5 下午3:14:13
 *   
 */
@SuppressLint("HandlerLeak")
public class Fragment_Discover extends BaseFragment {

	private Context context;
	/**
	 *左侧菜单栏
	 */
    @ViewInject(R.id.left_menu)
	private ListView listMenuView;


	/**
	 * 数据变量
	 */
	private List<ServiceProjectViewModel> menuList = new ArrayList<ServiceProjectViewModel>();
    private ServiceMenuAdapter serviceMenuAdapter;

	private void initData(){
		for (int i = 0 ; i< 40 ; i++){
			ServiceProjectViewModel item = new ServiceProjectViewModel();
			item.setIsSelected(i == 0);
			item.setItemName("电脑" + i);

			menuList.add(item);
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.service_fragment_layout, container, false);
        ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		serviceMenuAdapter = new ServiceMenuAdapter(context, R.layout.service_item_layout, menuList);
		listMenuView.setAdapter(serviceMenuAdapter);
	}

	@Override
	public void onAttach(Activity activity) {
		context = getActivity();
		super.onAttach(activity);
	}

    @OnItemClick(R.id.left_menu)
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
		for (int j = 0 ; j <  menuList.size() ; j++){
			menuList.get(j).setIsSelected(j==i);
		}
		serviceMenuAdapter.notifyDataSetChanged();
		int firstVisiblePosition = listMenuView.getFirstVisiblePosition();
		listMenuView.smoothScrollToPositionFromTop(i, i - firstVisiblePosition, 100 * (i - firstVisiblePosition));
	}
    @OnClick({R.id.search_button,R.id.edittext})
	public void onClick(View view){
		Intent intent = new Intent(context, BusinessSearchActivity.class);
		startActivity(intent);
	}
	/*
            * Title: onRefresh
            * Description:
            * @param pullToRefreshLayout
            * @see com.yihen.jdb.ui.widget.JFPullToRefreshLoadLayout.OnRefreshAndLoadListener#onRefresh(com.yihen.jdb.ui.widget.JFPullToRefreshLoadLayout)
            */
}
