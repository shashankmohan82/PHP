package haptik.producthunt.task.producthuntplus.commentlist;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.victor.loading.newton.NewtonCradleLoading;
import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.adapters.CommentRecyclerAdapter;
import haptik.producthunt.task.producthuntplus.api.ProductHuntAPI;
import haptik.producthunt.task.producthuntplus.api.models.Comment;
import haptik.producthunt.task.producthuntplus.api.models.Comments;
import haptik.producthunt.task.producthuntplus.base_view.BaseFragment;
import haptik.producthunt.task.producthuntplus.network.NetworkInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import static android.content.Context.CONNECTIVITY_SERVICE;

public class CommentFragment extends BaseFragment {

    private ProgressBar progressBar;
    private FrameLayout recyclerViewParent;
    private RecyclerView recyclerView;
    private CommentRecyclerAdapter recyclerViewAdapter;
    private NewtonCradleLoading progressView;
    private Realm realm;
    private int postId;
    private Comments realmComments;
    ProductHuntAPI api = new ProductHuntAPI();

    public static BaseFragment newInstance() {
        Log.d("hi","onInstance");
        return new CommentFragment();
    }

    public CommentFragment(){
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
        setHasOptionsMenu(true);
        realm = Realm.getDefaultInstance();
        postId = getActivity().getIntent().getIntExtra("id",0);
        Log.d("hi",postId+"");
        if(!checkRealmCache()) {
            startAPI();
        }
    }

    private void startAPI() {
        NetworkInteractor networkInteractor = new NetworkInteractor(
                (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE)
        );
        networkInteractor.hasInternetConnection()
                .subscribe(this::getComments, throwable -> {
                    if(progressBar!= null){
                        Log.d("hi","1");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private boolean checkRealmCache(){
        realmComments = realm.where(Comments.class).equalTo("postID",postId).findFirst();
        if(realmComments!= null && realmComments.getComments().size() > 0){
            Log.d("hi","3");
            return true;

        }
        return false;
    }

    private void getComments() {
        api.apiService().getComments(postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                    Log.d("hi","2");
                    comments.setPostID(postId);
                    addCommentsToRealm(comments);
                });
    }

    private void addCommentsToRealm(Comments comments) {
        realm.executeTransaction(realm1 -> {
            realm1.copyToRealmOrUpdate(comments);
        });
        Comments commentResult = realm.where(Comments.class).equalTo("postID",postId).findFirst();
        RealmList<Comment> commentList = commentResult.getComments();

        ItemTouchListener itemTouchListener = new ItemTouchListener() {
            @Override
            public void onItemClick(View view, int position, Comment item,int type) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, item.getUser().getName()
                        +"'s comments :'"+item.getBody()+"'");
                try {
                    activity.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(),"Whatsapp has not been installed",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public boolean onItemLongClick(View view, int position, Comment item) {
                return true;
            }
        };
        progressBar.setVisibility(View.INVISIBLE);
        Log.d("hi","4");
        recyclerViewAdapter = new CommentRecyclerAdapter(commentList,itemTouchListener,getActivity());
        Log.d("hi","5"+commentList.size());
        setUpDataFromNetwork();
    }


    interface ItemTouchListener extends CommentRecyclerAdapter.ItemTouchListener {
        void onItemClick(View view, int position, Comment item,int type);
        boolean onItemLongClick(View view, int position, Comment item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comment, container, false);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewParent = (FrameLayout) rootView.findViewById(R.id.recView_parent_comment);
        recyclerView = (RecyclerView) recyclerViewParent.findViewById(R.id.recView_comment);
        if(checkRealmCache()){
            Log.d("hi","6");
            setUpDataFromCache();
        }
        return rootView;
    }

    private void setUpDataFromNetwork() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        Log.d("hi","7");
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void setUpDataFromCache() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        realmComments = realm.where(Comments.class).equalTo("postID",postId).findFirst();
        RealmList <Comment> results = realmComments.getComments();

        ItemTouchListener itemTouchListener = new ItemTouchListener() {
            @Override
            public void onItemClick(View view, int position, Comment item,int type) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, item.getUser().getName()+"'s comments :'"+item.getBody()+"'");
                try {
                    activity.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(),"Whatsapp has not been installed",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public boolean onItemLongClick(View view, int position, Comment item) {
                return true;
            }
        };
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        Log.d("hi","8");
        recyclerViewAdapter = new CommentRecyclerAdapter(results,itemTouchListener,getActivity());
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
        return "Comments";
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
