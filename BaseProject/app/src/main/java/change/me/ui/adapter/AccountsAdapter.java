package change.me.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import change.me.R;
import change.me.model.Account;
import change.me.util.library.CircleTransformation;
import change.me.util.library.L;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private List<Account> accounts;
    private Transformation roundedTransformation = new CircleTransformation(0, 0);
    private Context context;

    public AccountsAdapter(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }

        View v = LayoutInflater.from(context).inflate(R.layout.row_account, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        L.d("Element " + position + " set.");

        Account account = accounts.get(position);

        Picasso.with(context)
                .load(R.drawable.ic_test)
                .transform(roundedTransformation)
                .into(holder.icon);
        holder.name.setText(account.name);
        holder.type.setText(account.type);
        holder.active.setChecked(account.isActive);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.icon)
        ImageButton icon;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.type)
        TextView type;
        @InjectView(R.id.active)
        SwitchCompat active;

        ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    if (clickedPosition != RecyclerView.NO_POSITION) {
                        L.d("Element " + clickedPosition + " clicked.");
                    } else {
                        L.i("clickedPosition == RecyclerView.NO_POSITION");
                    }
                }
            });
        }
    }

}
