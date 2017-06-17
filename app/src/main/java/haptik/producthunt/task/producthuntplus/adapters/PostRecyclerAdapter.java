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
import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.api.models.Post;
import io.realm.RealmList;

public class PostRecyclerAdapter extends RealmRecyclerAdapter<Post, PostRecyclerAdapter.ViewHolder> {

        private ItemTouchListener listener;
        private SparseBooleanArray selectedItems;
        private Activity activity;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        public PostRecyclerAdapter(@Nullable RealmList<Post> data, ItemTouchListener listener, Activity activity) {
            this(data, true);
            this.listener = listener;
            selectedItems = new SparseBooleanArray();
            this.activity = activity;
        }

        public PostRecyclerAdapter(@Nullable RealmList<Post> data, boolean autoUpdate) {
            super(data, autoUpdate);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View rootView = LayoutInflater.from(activity).inflate(R.layout.post_list_recycler, parent, false);
                return new ViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RealmList<Post> data = getData();
            if (data == null) return;
            //holder.commentText.setText(getData().get(position).getCommentsCount());
            holder.postTitle.setText(getData().get(position).getName());
            Picasso.with(activity)
                    .load(data.get(position).getThumbnail().getImageUrl())
                    .into(holder.backdrop);
            holder.tagText.setText(getData().get(position).getTagline());

        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView commentText;
            private TextView postTitle;
            private ImageView backdrop;
            private TextView tagText;

            public ViewHolder(View itemView) {
                super(itemView);

//                    commentText= (TextView) itemView.findViewById(R.id.comment_title);
                    postTitle = (TextView) itemView.findViewById(R.id.post_title);
                    backdrop = (ImageView) itemView.findViewById(R.id.backdrop);
                    tagText = (TextView)itemView.findViewById(R.id.tag_line);
                    CardView cardView = (CardView) ((RelativeLayout) itemView).findViewById(R.id.card_view_recycler);
                    cardView.setOnClickListener(view ->
                            listener.onItemClick(view, getAdapterPosition(), getItem(getAdapterPosition())));
                    cardView.setOnLongClickListener(view ->
                            listener.onItemLongClick(view, getAdapterPosition(), getItem(getAdapterPosition())));
            }
        }

    public interface ItemTouchListener {
        void onItemClick(View view, int position, Post item);
        boolean onItemLongClick(View view, int position, Post item);
    }
}

