package com.avijitmondal.together.ui.conversation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.avijitmondal.together.R;

public class ConversationFragment extends Fragment {

    private ConversationViewModel conversationViewModel;
    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X"};
    int[] flags = {R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp,
            R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp,
            R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp,
            R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp,
            R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp,
            R.drawable.ic_conversation_black_24dp, R.drawable.ic_conversation_black_24dp};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        conversationViewModel =
                new ViewModelProvider(this).get(ConversationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_conversation, container, false);
        ListView listView = root.findViewById(R.id.conversation_list);
        ConversationViewAdapter conversationViewAdapter = new ConversationViewAdapter(this.getActivity(), mobileArray, flags);


        listView.setAdapter(conversationViewAdapter);

//        listView.setOnItemClickListener((adapterView, view, position, l) -> {
//            String value = conversationViewAdapter.getItem(position);
//            Toast.makeText(getActivity().getBaseContext(), position, Toast.LENGTH_SHORT).show();

//        });

        return root;
    }
}