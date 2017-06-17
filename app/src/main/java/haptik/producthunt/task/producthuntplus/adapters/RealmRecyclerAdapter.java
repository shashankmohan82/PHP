package haptik.producthunt.task.producthuntplus.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import haptik.producthunt.task.producthuntplus.api.models.Post;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollection;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;

public abstract class RealmRecyclerAdapter<T extends RealmModel, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Nullable
    private RealmList<T> data;
    private OrderedRealmCollectionChangeListener listener;
    private boolean isUpdateRequired;
    private boolean autoUpdate;

    private OrderedRealmCollectionChangeListener createListener() {
        return (o, changeSet) -> {
            if (changeSet == null) {
                notifyDataSetChanged();
                return;
            }

            OrderedCollectionChangeSet.Range[] del = changeSet.getDeletionRanges();
            for (OrderedCollectionChangeSet.Range range : del) {
                notifyItemRangeRemoved(range.startIndex, range.length);
            }

            for (OrderedCollectionChangeSet.Range range : changeSet.getInsertionRanges()) {
                notifyItemRangeInserted(range.startIndex, range.length);
            }

            if (isUpdateRequired) {
                for (OrderedCollectionChangeSet.Range range : changeSet.getChangeRanges()) {
                    notifyItemRangeChanged(range.startIndex, range.length);
                }
            }
        };
    }


    public RealmRecyclerAdapter(@Nullable RealmList<T> data, boolean autoUpdate) {
        this(data, autoUpdate, true);
    }

    public RealmRecyclerAdapter(@Nullable RealmList<T> data, boolean autoUpdate, boolean isUpdateRequired) {

        this.data = data;
        this.autoUpdate = autoUpdate;
        this.isUpdateRequired = isUpdateRequired;
        this.listener = autoUpdate ? createListener() : null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (autoUpdate && isDatasetValid()){
        }
            //addChangeListener(data);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (autoUpdate && isDatasetValid()){

        }
            //removeChangeListener(data);
    }

    public void updateData(@Nullable RealmList<T> data) {
        if (listener != null) {
            if (isDatasetValid()) {
                removeChangeListener(this.data);
            }
            if (data != null && data.isValid()) {
                addChangeListener(data);
            }
        }

        this.data = data;
        notifyDataSetChanged();
    }

    private void addChangeListener(@NonNull OrderedRealmCollection<T> data) {
        if (data instanceof RealmResults) {
            RealmResults<T> res = (RealmResults<T>) data;
            res.addChangeListener(listener);
        } else if (data instanceof RealmList) {
            RealmList<T> res = (RealmList<T>) data;
            res.addChangeListener(listener);
        } else {
            throw new IllegalStateException("OrderedRealmCollection is not supported: " + data.getClass());
        }
    }

    private void removeChangeListener(@NonNull OrderedRealmCollection<T> data) {
        if (data instanceof RealmResults) {
            RealmResults<T> res = (RealmResults<T>) data;
            res.removeChangeListener(listener);
        } else if (data instanceof RealmList) {
            RealmList<T> res = (RealmList<T>) data;
            res.removeChangeListener(listener);
        } else {
            throw new IllegalStateException("OrderedRealmCollection is not supported: " + data.getClass());
        }
    }


    private boolean isDatasetValid() {
        return data != null && data.isValid();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return isDatasetValid() ? data.size() : 0;
    }

    @Nullable
    public T getItem(int position) {
        Log.d("position",position+"");
        return isDatasetValid() && position < data.size() ? (T) data.get(position) : null;
    }

    @Nullable
    public RealmList<T> getData() {
        return (RealmList<T>) data;
    }
}
