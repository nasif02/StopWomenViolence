package com.ewu.bug.swv.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ewu.bug.swv.R;
import com.ewu.bug.swv.model.ListItem;

import java.util.ArrayList;

import xplo.library.ekush.MyBanglaSupport;

public class CustomListAdapterSupport extends ArrayAdapter<ListItem> {

    private final String TAG = CustomListAdapterSupport.class.getSimpleName();

    private final Activity context;
    private Typeface tf = null;
    private static int layoutId= R.layout.cell_lv_support;


    public CustomListAdapterSupport(Activity context, ArrayList<ListItem> items,
                                    Typeface tf) {
        super(context, layoutId, items);
        this.context = context;
        this.tf = tf;



    }



    private class ViewHolder {
        TextView tvHeader;
        //TextView tvHeader;
        ImageView ivIcon;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        // View rowView = inflater.inflate(layoutId, null, true);
        // TextView tvHeader = (TextView) rowView.findViewById(R.id.tvListText);
        // if (tf != null) {
        // tvHeader.setTypeface(tf);
        // }

        View rowView = view;
        ViewHolder holder = new ViewHolder();

        if (rowView == null) {

            rowView = inflater.inflate(layoutId, null, true);

            holder = new ViewHolder();
            holder.tvHeader = (TextView) rowView.findViewById(R.id.lvHeader);
            //holder.tvFooter = (TextView) rowView.findViewById(R.id.lvHeader);
            holder.ivIcon = (ImageView) rowView.findViewById(R.id.lvIcon);

            int imageId=R.drawable.logo_care;
            switch (position){

                case 0:
                    imageId=R.drawable.logo_we_can_orange;
                    break;
                case 1:
                    imageId=R.drawable.logo_usaid;
                    break;
                case 2:
                    imageId=R.drawable.logo_care;
                    break;
                case 3:
                    imageId=R.drawable.logo_brac;
                    break;

            }

            holder.ivIcon.setImageResource(imageId);


//            tf = Typeface.createFromAsset(context.getAssets(),
//                    context.getString(R.string.font_bn));

            if (tf != null) {
                holder.tvHeader.setTypeface(tf);
                //holder.tvFooter.setTypeface(tf);

            }

            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        ListItem mItem = getItem(position);
        String text = mItem.getData();

        if (android.os.Build.VERSION.SDK_INT < 16
                && !MyBanglaSupport.isDeviceSupportBangla()) {

            text = MyBanglaSupport.getBanglaString(text);
        }

        holder.tvHeader.setText(text);
        //holder.tvFooter.setText(text + "...");


        return rowView;
    }

}