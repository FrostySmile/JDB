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

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.yihen.jdb.R;
import com.yihen.jdb.bean.dto.StoreDto;
import com.yihen.jdb.business.model.ServiceDao;
import com.yihen.jdb.business.model.StoreDao;
import com.yihen.jdb.ui.widget.ToastUtil;


/**
 * @ClassName: MenuAdapter
 * @Description: ListView 关注模块的adapter
 * @author Frosty
 * @date 2015-5-4 下午5:00:48
 *   
 */
public class AttetionAdapter extends BaseAdapter  implements SectionIndexer{

	private Context mContext;
	private Handler mHandler;
	private List<StoreDao> attention = null;
	private LayoutInflater inflater;
	private BitmapUtils bitmapUtils = null;

	public AttetionAdapter(Context context,List<StoreDao> attention){
		this(context,attention,null);
	}
	
	public AttetionAdapter(Context context,List<StoreDao> attention,Handler mHandler){
		this.mContext = context;
		this.attention = attention;
		this.mHandler = mHandler;
		this.inflater = LayoutInflater.from(mContext);
		this.bitmapUtils =  new BitmapUtils(context);
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * @param list
	 */
	public void updateListView(List<StoreDao> attention){
		this.attention = attention;
		notifyDataSetChanged();
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
		return attention == null ? 0 : attention.size();
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
		return attention.get(index);
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
		
		ItemFrame itemFrame = null;
		
		if(viewFrame == null)
		{
			itemFrame = new ItemFrame();
			
			viewFrame = inflater.inflate(R.layout.list_attention_item, null);
			itemFrame.attenItemLayout = (LinearLayout) viewFrame.findViewById(R.id.attenItemLayout);
			itemFrame.tvLetter =  (TextView) viewFrame.findViewById(R.id.tvLetter);
			itemFrame.header = (ImageView) viewFrame.findViewById(R.id.header);
			itemFrame.userStoreName = (TextView) viewFrame.findViewById(R.id.userStoreName);
			itemFrame.userAddress = (TextView) viewFrame.findViewById(R.id.address);
			itemFrame.userService1 =  (TextView) viewFrame.findViewById(R.id.service1);
			itemFrame.userService2 =  (TextView) viewFrame.findViewById(R.id.service2);
			itemFrame.userService3 =  (TextView) viewFrame.findViewById(R.id.service3);
			itemFrame.userService4 =  (TextView) viewFrame.findViewById(R.id.service4);
			viewFrame.setTag(itemFrame);
		}else{
			itemFrame = (ItemFrame) viewFrame.getTag();
		}
		
		StoreDao atten = attention.get(position);
		if(null == atten) return null;
		
		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		
		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if(position == getPositionForSection(section)){
			itemFrame.tvLetter.setVisibility(View.VISIBLE);
			itemFrame.tvLetter.setText(atten.getSortLetters());
		}else{
			itemFrame.tvLetter.setVisibility(View.GONE);
		}

		
		itemFrame.userStoreName.setText(atten.getsName());
		itemFrame.userAddress.setText(atten.getsAddress());
//		List<ServiceDao> service = atten.get
//		if(service!=null){
//				if(service.size()==0){
//					itemFrame.userService4.setText(R.string.nolabel);
//					itemFrame.userService4.setVisibility(View.VISIBLE);
//				}
//				if(service.size()>0){
//					itemFrame.userService1.setText(service.get(0));
//					itemFrame.userService1.setVisibility(View.VISIBLE);
//				}
//				if(service.size()>1){
//					itemFrame.userService2.setText(service.get(1));
//					itemFrame.userService2.setVisibility(View.VISIBLE);
//				}
//				if(service.size()>2){
//					itemFrame.userService3.setText(service.get(2));
//					itemFrame.userService3.setVisibility(View.VISIBLE);
//				}
//				if(service.size()>3){
//					itemFrame.userService4.setText(R.string.more);
//					itemFrame.userService4.setVisibility(View.VISIBLE);
//				}
//		}else{
//			itemFrame.userService4.setText(R.string.nolabel);
//			itemFrame.userService4.setVisibility(View.VISIBLE);
//		}
		
		//点击进入详情
		itemFrame.attenItemLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
	private class ItemFrame{
		private LinearLayout attenItemLayout;//item项布局
		private TextView tvLetter;//分组标签
		private ImageView header;
		private TextView userStoreName;
		private TextView userAddress;
		private TextView userService1,userService2,userService3,userService4;//服务内容，默认最多显示3个
	}

	//分组
	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return attention.get(position).getSortLetters().charAt(0);
	}
	
	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = attention.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
	
	
}
