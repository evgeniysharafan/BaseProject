package change.me.ui.fragment;

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
import change.me.ui.adapter.ChatsAdapter;
import change.me.ui.adapter.ChatsAdapter.OnChatClickListener;
import change.me.util.library.Toasts;

public class ChatsFragment extends Fragment implements OnChatClickListener {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        ButterKnife.inject(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ChatsAdapter(MockData.getChats(100, 5, 20), this));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onChatClick(int position) {
        Toasts.showShort("onChatClick");
    }

    @Override
    public void onChatIconClick(int position) {
        Toasts.showShort("onChatIconClick");
    }

}
