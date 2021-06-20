package com.avijitmondal.together.ui.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avijitmondal.together.R;

public class ConversationViewAdapter extends BaseAdapter {
    private final String[] conversationList;
    private final int[] profileImage;
    private LayoutInflater inflater;

    public ConversationViewAdapter(Context applicationContext, String[] conversationList, int[] profileImage) {
        this.conversationList = conversationList;
        this.profileImage = profileImage;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return conversationList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.conversation_listview, null);
        TextView country = view.findViewById(R.id.conversation_textview);
        ImageView icon = view.findViewById(R.id.conversation_image);
        country.setText(conversationList[i]);
        icon.setImageResource(profileImage[i]);
        return view;
    }
}