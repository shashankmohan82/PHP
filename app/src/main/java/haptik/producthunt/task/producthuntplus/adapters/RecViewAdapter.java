package haptik.producthunt.task.producthuntplus.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.api.models.Comment;
import haptik.producthunt.task.producthuntplus.api.models.Comments;
import haptik.producthunt.task.producthuntplus.api.models.Posts;
import io.realm.OrderedRealmCollection;
import io.realm.RealmList;
import io.realm.annotations.RealmClass;

/**
 * Created by shashank on 6/15/2017.
 */

public class RecViewAdapter extends RealmRecViewAdapter<Comment, RecViewAdapter.ViewHolder> {

        private ItemTouchListener listener;
        private SparseBooleanArray selectedItems;
        private Activity activity;

        public RecViewAdapter(@Nullable RealmList<Comment> data, ItemTouchListener listener, Activity activity) {
            this(data, true);
            this.listener = listener;
            selectedItems = new SparseBooleanArray();
            this.activity = activity;
        }

        public RecViewAdapter(@Nullable RealmList<Comment> data, boolean autoUpdate) {
            super(data, autoUpdate);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(activity).inflate(R.layout.list_recycler, parent, false);
            return new ViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RealmList<Comment> data = getData();
            if (data == null) return;

//            Picasso.with(activity).load("https://image.tmdb.org/t/p/w500" + data.get(position).toString())
//                    .error(R.drawable.trailer1)
//                    .placeholder(R.drawable.trailer1)
//                    .into(holder.backdrop);
            holder.postTitle.setText(getData().get(position).getChildComments()+"");
        }

        public void toggleSelection(int position) {
            if (selectedItems.get(position, false)) {
                selectedItems.delete(position);
            } else {
                selectedItems.put(position, true);
            }
            notifyItemChanged(position);
        }

        public int getSelectedItemCount() {
            return selectedItems.size();
        }

        public void clearSelections() {
            List<Integer> list = getSelectedItems();
            selectedItems.clear();
            notifyDataSetChanged();
        }

        public List<Integer> getSelectedItems() {
            List<Integer> itemIndices = new ArrayList<>();
            for (int i = 0; i < selectedItems.size(); i++)
                itemIndices.add(selectedItems.keyAt(i));
            return itemIndices;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView postImage;
            private TextView postTitle;


            public ViewHolder(View itemView) {
                super(itemView);
                if (itemView instanceof LinearLayout) {
                    CardView cardView = (CardView) ((LinearLayout) itemView).findViewById(R.id.card_view_recycler);
                    postImage = (ImageView) itemView.findViewById(R.id.post_image);
                    postTitle = (TextView) itemView.findViewById(R.id.title);
                    cardView.setOnClickListener(view ->
                            listener.onItemClick(view, getAdapterPosition(), getItem(getAdapterPosition())));
                    cardView.setOnLongClickListener(view ->
                            listener.onItemLongClick(view, getAdapterPosition(), getItem(getAdapterPosition())));

                }
            }
        }

    interface ItemTouchListener {
        void onItemClick(View view, int position, Comment item);
        boolean onItemLongClick(View view, int position, Comment item);

        void onCheckboxClick(int position, Posts item);
    }
}

