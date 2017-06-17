package haptik.producthunt.task.producthuntplus.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.api.models.Comment;
import haptik.producthunt.task.producthuntplus.api.models.Post;
import io.realm.RealmList;

/**
 * Created by shashank on 6/17/2017.
 */

public class CommentRecyclerAdapter extends RealmRecyclerAdapter<Comment, CommentRecyclerAdapter.ViewHolder> {

    private ItemTouchListener listener;
    private SparseBooleanArray selectedItems;
    private Activity activity;

    public CommentRecyclerAdapter(@Nullable RealmList<Comment> data, ItemTouchListener listener, Activity activity) {
        this(data, true);
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
        this.activity = activity;
    }

    public CommentRecyclerAdapter(@Nullable RealmList<Comment> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(activity).inflate(R.layout.comment_list_recycler, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RealmList<Comment> data = getData();
        if (data == null) return;
        Picasso.with(activity)
                .load(data.get(position).getUser().getImageUrl().get48px())
                .into(holder.backdrop);
        holder.commentText.setText(getData().get(position).getBody());
        holder.userTitle.setText(getData().get(position).getUser().getName());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView shareImage;
        private TextView commentText;
        private ImageView backdrop;
        private TextView userTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            backdrop = (ImageView) itemView.findViewById(R.id.post_image);
            CardView cardView = (CardView) ((RelativeLayout) itemView).findViewById(R.id.card_view_recycler);
            commentText = (TextView)itemView.findViewById(R.id.comment_text);
            shareImage = (ImageView) itemView.findViewById(R.id.shareView);
            userTitle = (TextView) itemView.findViewById(R.id.username_title);
            shareImage.setOnClickListener(view ->
                    listener.onItemClick(view, getAdapterPosition(), getItem(getAdapterPosition()),1));
        }
    }



    public interface ItemTouchListener {
        void onItemClick(View view, int position, Comment item,int type);
        boolean onItemLongClick(View view, int position, Comment item);
    }
}


