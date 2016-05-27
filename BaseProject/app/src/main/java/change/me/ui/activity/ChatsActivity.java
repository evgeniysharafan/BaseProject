package change.me.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.evgeniysharafan.utils.Fragments;

import butterknife.BindView;
import butterknife.ButterKnife;
import change.me.R;
import change.me.ui.fragment.ChatsFragment;

public class ChatsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragments.replace(getSupportFragmentManager(), R.id.content, ChatsFragment.newInstance(), null);
        }
    }

}
