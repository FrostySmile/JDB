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
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yihen.jdb.R;
import com.yihen.jdb.business.model.StoreDao;
import com.yihen.jdb.db.DBHelper;
import com.yihen.jdb.tools.CharacterParser;
import com.yihen.jdb.tools.PinyinComparator;
import com.yihen.jdb.ui.adapter.AttetionAdapter;
import com.yihen.jdb.ui.widget.SideBar;

/**
 * @ClassName: Fragment_Me
 * @Description:  关注
 * @author Frosty
 * @date 2015-5-5 下午3:14:13
 *   
 */
@SuppressLint("HandlerLeak")
public class Fragment_Attention extends BaseFragment {

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	/**
	 * 适配器
	 */
	private AttetionAdapter adapter;
	/**
	 * 数据源
	 */
	private List<StoreDao> attentionList;
	
	/**
	 *view
	 */
    @ViewInject(R.id.sidrbar)
	private SideBar sideBar;
    @ViewInject(R.id.dialog)
	private TextView dialog;
    @ViewInject(R.id.filter_edit)
    private EditText filter_edit;
    @ViewInject(R.id.list_view)
	private ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DBHelper.getInstance().getDataBase(getActivity());
		
//		characterParser = CharacterParser.getInstance();
//		pinyinComparator = new PinyinComparator();
//		initListData();//初始化模拟数据 
//		adapter = new AttetionAdapter(getActivity(),attentionList);
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_attention_layout, container, false);
        ViewUtils.inject(this, view);
		return view;
	}

	
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		sideBar.setTextView(dialog);
//		listView.setAdapter(adapter);
//		//设置右侧触摸监听
//		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
//			
//			@Override
//			public void onTouchingLetterChanged(String s) {
//				//该字母首次出现的位置
//				int position = adapter.getPositionForSection(s.charAt(0));
//				if(position != -1){
//					try {
//						for(int k =0;k<adapter.getCount();k++){
//							TextView tv = (TextView) listView.getChildAt(k).findViewById(R.id.tvLetter);
//							String selectStr = tv.getText().toString();
//							if(!TextUtils.isEmpty(selectStr)){
//								if((s.charAt(0))==(selectStr.charAt(0))){
//									tv.startAnimation(AnimationTools.shakeAnimation(3, 700));
//								}
//							}
//						}
//						
//					} catch (Exception e) {
//					}finally{
//						listView.setSelection(position);
//					}
//				}
//			}
//		});
//
//		//根据输入框输入值的改变来过滤搜索
//		filter_edit.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
//				filterData(s.toString());
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//			}
//		});
//
//	}
	
	private List<StoreDao> initListData(){
		attentionList = new ArrayList<StoreDao>();
		
//		
//		StoreDto a8 = new StoreDto();
//		a8.setUserStoreName("坦克专卖店");
//		a8.setUserLocation("成都金牛区曙光路11号");
//		List<String> service8 = new ArrayList<String>();
//		service8.add("俄罗斯坦克");
//		service8.add("美国坦克");
//		service8.add("中国坦克");
//		service8.add("修厕所");
//		a8.setUserService(service8);
//		String pinyin8 = characterParser.getSelling("坦克专卖店");
//		String sortString8 = pinyin8.substring(0, 1).toUpperCase();
//		// 正则表达式，判断首字母是否是英文字母
//		if(sortString8.matches("[A-Z]")){
//			a8.setSortLetters(sortString8.toUpperCase());
//		}else{
//			a8.setSortLetters("#");
//		}
//	
//		
//		attentionList.add(a1);
//		attentionList.add(a2);
//		attentionList.add(a3);
//		attentionList.add(a4);
//		attentionList.add(a5);
//		attentionList.add(a6);
//		attentionList.add(a7);
//		attentionList.add(a8);

		// 根据a-z进行排序源数据
		Collections.sort(attentionList, pinyinComparator);
		return attentionList;
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<StoreDao> filterDateList = new ArrayList<StoreDao>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = attentionList;
		}else{
			filterDateList.clear();
			for(StoreDao atten : attentionList){
//				String name = atten.getuPetName();//用户昵称
//				List<ServiceDto> serviceList = atten.getUserService();
//				if(serviceList!=null){
//					for(int i = 0;i<serviceList.size();i++){
//						if(serviceList.get(i).indexOf(filterStr.toString())!=-1){
//							filterDateList.add(atten);
//						}
//					}
//				}
//				if(name!=null){
//					if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
//						filterDateList.add(atten);
//					}
//				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	
}
