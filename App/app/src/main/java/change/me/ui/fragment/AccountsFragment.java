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
import change.me.ui.activity.AccountsActivity;
import change.me.ui.adapter.AccountsAdapter;

public class AccountsFragment extends Fragment {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    AccountsActivity accountsActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            accountsActivity = (AccountsActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getLocalClassName() + " must be " + AccountsActivity.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        ButterKnife.inject(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new AccountsAdapter(MockData.getAccounts()));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
