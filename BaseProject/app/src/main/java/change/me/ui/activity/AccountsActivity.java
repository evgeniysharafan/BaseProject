package change.me.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.evgeniysharafan.utils.Fragments;

import butterknife.Bind;
import butterknife.ButterKnife;
import change.me.R;
import change.me.ui.fragment.AccountsFragment;

public class AccountsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, AccountsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragments.replace(getSupportFragmentManager(), R.id.content, AccountsFragment.newInstance(), null);
        }
    }

}
