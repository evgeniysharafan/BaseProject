package change.me.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import change.me.R;
import change.me.model.mockdata.MockData;
import change.me.ui.activity.ChatsActivity;
import change.me.ui.adapter.ChatsAdapter;

public class ChatsFragment extends Fragment {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    ChatsAdapter adapter;

    ChatsActivity chatsActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            chatsActivity = (ChatsActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getLocalClassName() + " must be " + ChatsActivity.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        ButterKnife.inject(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ChatsAdapter(MockData.getChats(100, 5, 20)));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
