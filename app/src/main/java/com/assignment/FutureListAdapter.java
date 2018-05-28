package com.assignment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FutureListAdapter extends BaseExpandableListAdapter {
    private ArrayList<Feature> features;
    private ExpandableListView list;
    private int lastOpenedGroup = -1;
    private String contentString = "";

    FutureListAdapter(ExpandableListView list, ArrayList<Feature> efb) {
        this.features = efb;
        this.list = list;
        list.setOnGroupExpandListener(expandListener);
    }

    @Override
    public int getGroupCount() {
        return (int) Math.floor(features.size() / 3);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_layout, null);
        }

        final Button btn1 = convertView.findViewById(R.id.btn1);
        final Button btn2 = convertView.findViewById(R.id.btn2);
        final Button btn3 = convertView.findViewById(R.id.btn3);
        btn1.setTextColor(Color.parseColor("#551A8B"));
        btn3.setTextColor(Color.parseColor("#551A8B"));
        btn2.setTextColor(Color.parseColor("#551A8B"));

        Feature f1 = features.get(0 + (3 * groupPosition));
        Feature f2 = features.get(1 + (3 * groupPosition));
        Feature f3 = features.get(2 + (3 * groupPosition));

        if (f1 != null) {
            btn1.setVisibility(View.VISIBLE);
            btn1.setText(f1.getName());
            btn1.setTag(f1);
        } else {
            btn1.setVisibility(View.INVISIBLE);
        }

        if (f2 != null) {
            convertView.findViewById(R.id.separator_view).setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn2.setText(f2.getName());
            btn2.setTag(f2);
        } else {
            btn2.setVisibility(View.INVISIBLE);
            convertView.findViewById(R.id.separator_view).setVisibility(View.INVISIBLE);
        }

        if (f3 != null) {
            convertView.findViewById(R.id.separator_view2).setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn3.setText(f3.getName());
            btn3.setTag(f3);
        } else {
            convertView.findViewById(R.id.separator_view2).setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentString = features.get(0 + (3 * groupPosition)).getValue();
                list.expandGroup(groupPosition);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentString = features.get(1 + (3 * groupPosition)).getValue();
                list.expandGroup(groupPosition);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentString = features.get(2 + (3 * groupPosition)).getValue();
                list.expandGroup(groupPosition);

            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.children_layout, null);
        }
        TextView tv = convertView.findViewById(R.id.description_tv);
        ImageView closeIv = convertView.findViewById(R.id.close_iv);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.collapseGroup(lastOpenedGroup);
                lastOpenedGroup = -1;
            }
        });
        if (contentString != null)
            tv.setText(contentString);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    ExpandableListView.OnGroupExpandListener expandListener = new ExpandableListView.OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {

            if (groupPosition != lastOpenedGroup)
                list.collapseGroup(lastOpenedGroup);
            lastOpenedGroup = groupPosition;
            notifyDataSetChanged();
        }
    };
}
