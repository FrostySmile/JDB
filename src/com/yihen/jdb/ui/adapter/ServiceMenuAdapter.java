package com.yihen.jdb.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yihen.jdb.R;
import com.yihen.jdb.bean.ServiceProjectViewModel;
import com.yihen.jdb.tools.GetDataFromRes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:37
 */
public class ServiceMenuAdapter extends ArrayAdapter<ServiceProjectViewModel>{

    private Context context;

    public ServiceMenuAdapter(Context context, int resource, List<ServiceProjectViewModel> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.service_item_layout,null);
            ViewUtils.inject(holder,convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder)convertView.getTag();
        }
        ServiceProjectViewModel item = getItem(position);
        holder.mItemMenu.setSelected(item.isSelected());
        if(item.isSelected()){
            holder.mItemMenu.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            holder.mItemMenu.setTextColor(context.getResources().getColor(R.color.black_font));
        }
        holder.mItemMenu.setText(item.getItemName());
        return convertView;
    }

    class Holder{
        @ViewInject(R.id.service_name)
        TextView mItemMenu;
    }
}
