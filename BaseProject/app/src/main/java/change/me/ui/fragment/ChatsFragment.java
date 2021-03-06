package change.me.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.evgeniysharafan.utils.Toasts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import change.me.R;
import change.me.model.mockdata.MockData;
import change.me.ui.activity.AccountsActivity;
import change.me.ui.activity.SettingsActivity;
import change.me.ui.adapter.ChatsAdapter;
import change.me.ui.adapter.ChatsAdapter.OnChatClickListener;

public class ChatsFragment extends Fragment implements OnChatClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    public static ChatsFragment newInstance() {
        return new ChatsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        unbinder = ButterKnife.bind(this, view);

        prepareActionBar();
        initUI();

        return view;
    }

    private void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ChatsAdapter(MockData.getChats(100, 5, 20), this));
    }

    private void prepareActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onChatClick(int position) {
        Toasts.showShort("onChatClick");
    }

    @Override
    public void onChatIconClick(int position) {
        Toasts.showShort("onChatIconClick");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chats, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_accounts:
                AccountsActivity.start(getActivity());
                return true;

            case R.id.action_settings:
                SettingsActivity.start(getActivity());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
