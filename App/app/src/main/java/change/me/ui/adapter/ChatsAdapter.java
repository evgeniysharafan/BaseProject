package change.me.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import change.me.model.Chat;
import change.me.util.MessageUtils;
import change.me.util.library.CircleTransformation;
import change.me.util.library.L;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private List<Chat> chats;
    private Transformation roundedTransformation = new CircleTransformation(0, 0);
    private Context context;

    public ChatsAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }

        View v = LayoutInflater.from(context).inflate(R.layout.row_chat, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        L.d("Element " + position + " set.");

        Chat chat = chats.get(position);

        Picasso.with(context)
                .load(R.drawable.ic_test)
                .transform(roundedTransformation)
                .into(holder.icon);
        holder.name.setText(chat.name);
        holder.message.setText(chat.messages.get(0).text);
        holder.time.setText(MessageUtils.formatTime(context, chat.messages.get(0).timeMillis, false));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.icon)
        ImageButton icon;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.message)
        TextView message;
        @InjectView(R.id.time)
        TextView time;

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
