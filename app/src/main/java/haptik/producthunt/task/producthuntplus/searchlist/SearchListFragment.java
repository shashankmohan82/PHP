package haptik.producthunt.task.producthuntplus.searchlist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.victor.loading.newton.NewtonCradleLoading;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import haptik.producthunt.task.producthuntplus.adapters.RecViewAdapter;
import haptik.producthunt.task.producthuntplus.api.ProductHuntAPI;
import haptik.producthunt.task.producthuntplus.api.models.Comment;
import haptik.producthunt.task.producthuntplus.api.models.Comments;
import haptik.producthunt.task.producthuntplus.base_view.BaseFragment;
import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.network.NetworkInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class SearchListFragment extends BaseFragment{


    private static final String TAG = SearchListFragment.class.getName();
    private static final String SEARCH_LIST = "searchList";
    private static final String CLICKED_ITEM = "clickedItem";
    private static final String SEARCH_ITEM = "searchItem";
    private OnReplaceFragmentListener listener;
    private FrameLayout recyclerViewParent;
    private RecyclerView recyclerView;
    private FrameLayout fragmentContainer;
    private RecViewAdapter recyclerViewAdapter;
    private NewtonCradleLoading progressView;
    private Realm realm;
    ProductHuntAPI api = new ProductHuntAPI();

    public static BaseFragment newInstance() {
        Log.d("hi","onInstance");
        return new SearchListFragment();
    }

//    public static SearchListFragment newInstance(ArrayList<? extends MediaBasic> movieDbs, MediaType filterType) {
//        SearchListFragment fragment = new SearchListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(SEARCH_LIST, movieDbs);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
    public SearchListFragment(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
        realm.close();
        realm = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("hi","onCrete");
        setHasOptionsMenu(true);
        realm = Realm.getDefaultInstance();
        startAPI();


    }


    private void startAPI() {
        NetworkInteractor networkInteractor = new NetworkInteractor(
                (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE)
        );
        networkInteractor.hasInternetConnection()
                .subscribe(this::getComments, throwable -> {
                    throwable.printStackTrace();
                    Log.d("hi","connecting inside");
                });
    }

    private void getComments() {
        Log.d("hi","connecting");
        api.apiService().getComments(101340)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                    comments.setPostID(101340);
                    addCommentsToRealm(comments);
                    Log.d("hi","connectingRealm"+comments.getComments().size());


                    RealmResults<Comment> commentList = realm.where(Comment.class).findAll();
                    RealmList <Comment> results = new RealmList<Comment>();

                    results.addAll(commentList.subList(0, commentList.size()));
                    Log.d("commentlist",commentList.size()+"");
                    recyclerViewAdapter = new RecViewAdapter(results,null,getActivity());

                    setUpRecyclerView();
                });
    }

    private void addCommentsToRealm(Comments comments) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.d("hi","connectingRealm");
                realm.copyToRealmOrUpdate(comments);
            }
        });
    }

    private void getPosts(){
        api.apiService().getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {


                });
    }



//    private void addCommentsToRealm(Comments comments) {

//    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        fragmentContainer = (FrameLayout) getActivity().findViewById(R.id.frame_search_list);
//        progressView = (NewtonCradleLoading) fragmentContainer.findViewById(R.id.progressView);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searchlist, container, false);
        recyclerViewParent = (FrameLayout) rootView.findViewById(R.id.recView_parent);
//        Log.d("hi","onCreteView");
        recyclerView = (RecyclerView) recyclerViewParent.findViewById(R.id.recView);

        Comments comments = realm.where(Comments.class)
                .equalTo("postID", 101340)
                .findFirst();

        if(comments!=null && comments.isValid()) {
            Log.d("hi","onCreteViewlszvlk");
            Log.d("hi","connecting");
            comments = realm.copyFromRealm(comments);
            RealmList<Comment> commentList = comments.getComments();
            recyclerViewAdapter = new RecViewAdapter(commentList,null,getActivity());
        }
        setUpRecyclerView();
        return rootView;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public boolean isBackKeyConsumed() {
        return false;
    }

    @Override
    public String getTitle() {
        return "";
    }

//    @Override
//    public void showSearchList(ArrayList<? extends MediaBasic> movieDbs) {
////        recyclerViewAdapter.replaceData(movieDbs);
//
//    }
//
//    @Override
//    public <T extends MediaBasic> Void showItemDetailsUi(T item) {
//        Intent intent = new Intent(getActivity(), SearchItemDetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(CLICKED_ITEM, item);
//        intent.putExtra(SEARCH_ITEM, bundle);
//        startActivity(intent);
//        return null;
//    }

//    @Override
//    public void showLoadingIndicator(boolean show) {
//        Context context = ApplicationClass.getInstance();
//        if(show) {
//            recyclerViewParent.setVisibility(View.GONE);
//            fragmentContainer.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//            progressView.setVisibility(View.VISIBLE);
//            progressView.start();
//        } else {
//            progressView.setVisibility(View.GONE);
//            progressView.stop();
//            fragmentContainer.setBackgroundColor(context.getResources().getColor(android.R.color.background_light));
//            recyclerViewParent.setVisibility(View.VISIBLE);
//        }
//    }

//    @Override
//    public void showNoResults() {
//        recyclerViewAdapter.replaceData(new ArrayList<>());
//        recyclerViewAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void showLoadingResultsError() {
//
//    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
       // outState.putSerializable(SEARCH_LIST, recyclerViewAdapter.getCurrentData());
        super.onSaveInstanceState(outState);
    }


    private class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>{
        private Context context;
        private ArrayList<String> data;
        private SearchItemClickListener listener;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        public SearchListAdapter(Context context, ArrayList<String> data, SearchItemClickListener listener) {
            this.context = context;
            this.data = data;
            this.listener = listener;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            if(viewType == TYPE_ITEM) {
//                View itemView = LayoutInflater.from(context).inflate(R.layout.list_recycler, parent, false);
//                return new ViewHolder(itemView, TYPE_ITEM);
//            }
//            if(viewType == TYPE_HEADER){
//                View itemView = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false);
//                return new ViewHolder(itemView, TYPE_HEADER);
//            }
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.HOLDER_ID == 1) {


            } else {
                 holder.header.setText(data.size() + " results returned");
            }
        }

        public void replaceData(ArrayList<String> movieDbs) {
            //this.oldData = data;
            notifyDataSetChanged();
        }

        public ArrayList<String> getCurrentData() {
            return this.data;
        }

        @Override
        public int getItemCount() {
            return data.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private ImageView icon;
            private TextView year;
            private TextView header;
            private CircleImageView imageView;
            private int HOLDER_ID;

            public ViewHolder(View itemView, int viewType) {
                super(itemView);
//                if (viewType == TYPE_ITEM) {
//                    title = (TextView) itemView.findViewById(R.id.title);
//                    year = (TextView) itemView.findViewById(R.id.year);
//                    imageView = (CircleImageView) itemView.findViewById(R.id.item_image);
//                    itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            listener.onItemClick(v, data.get(getAdapterPosition() - 1));
//                        }
//                    });
//                    HOLDER_ID = 1;
//                } else {
//                    header = (TextView) itemView.findViewById(R.id.header);
//                    HOLDER_ID = 0;
//                }

            }
        }

    }

    public interface SearchItemClickListener {
        <String> Void onItemClick(View view, String item);
    }

    public interface OnReplaceFragmentListener {
        void replaceFragment(ArrayList<String> movieDbs);
    }

}
