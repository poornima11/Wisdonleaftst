package com.first.wislftst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MylistAdapter extends ArrayAdapter<ListModel> {

    private ArrayList<ListModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView Name;
        TextView Description;
        ImageView imgSrc;
    }

    public MylistAdapter(ArrayList<ListModel> data, Context context) {
        super(context, R.layout.layout_inflatr_list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_inflatr_list_item, parent, false);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.layout_item_title);
            viewHolder.Description = (TextView) convertView.findViewById(R.id.layout_item_description);
            viewHolder.imgSrc = (ImageView) convertView.findViewById(R.id.layout_item_image);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.Name.setText(dataModel.getTitle());
        viewHolder.Description.setText(dataModel.getDescription());
        Picasso.get().load(dataModel.getImage()).into(viewHolder.imgSrc);

        return convertView;
    }

}
