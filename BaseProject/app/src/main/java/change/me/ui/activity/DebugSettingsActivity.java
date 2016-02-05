package change.me.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import change.me.R;
import change.me.ui.fragment.DebugSettingsFragment;

public class DebugSettingsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static void launch(Activity activity, @Nullable String... emailsForSending) {
        activity.startActivity(getIntent(activity, emailsForSending));
    }

    public static Intent getIntent(Activity activity, @Nullable String... emailsForSending) {
        return new Intent(activity, DebugSettingsActivity.class)
                .putExtra(DebugSettingsFragment.ARG_EMAILS, emailsForSending);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.content, DebugSettingsFragment
                            .newInstance(getIntent().getStringArrayExtra(DebugSettingsFragment.ARG_EMAILS)),
                    DebugSettingsFragment.class.getSimpleName()).commit();
        }
    }

}
