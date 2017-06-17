package haptik.producthunt.task.producthuntplus.postlist;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import haptik.producthunt.task.producthuntplus.adapters.PostRecyclerAdapter;
import haptik.producthunt.task.producthuntplus.api.ProductHuntAPI;
import haptik.producthunt.task.producthuntplus.api.models.Post;
import haptik.producthunt.task.producthuntplus.api.models.Posts;
import haptik.producthunt.task.producthuntplus.base_view.BaseFragment;
import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.commentlist.CommentActivity;
import haptik.producthunt.task.producthuntplus.network.NetworkInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import static android.content.Context.CONNECTIVITY_SERVICE;

public class PostListFragment extends BaseFragment{

    private FrameLayout recyclerViewParent;
    private ItemTouchListener itemTouchListener;
    private RecyclerView recyclerView;
    private PostRecyclerAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private Realm realm;
    private String dateParam;
    private AppCompatImageButton dateButton;
    private Calendar mCalendar;
    ProductHuntAPI api = new ProductHuntAPI();
    private static final String POSTS_PRIMARY_KEY= "date";
    private DatePickerDialog date;
    private Posts realmPosts;
    public static BaseFragment newInstance() {
        return new PostListFragment();
    }

    public PostListFragment(){
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
        initDate();
        if(!checkRealmCache()) {
            startAPI();
        }
    }

    private void startAPI()   {
        NetworkInteractor networkInteractor = new NetworkInteractor(
                (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE)
        );
        networkInteractor.hasInternetConnection()
                .subscribe(this::getPosts, throwable -> {
                    if(progressBar != null )
                        progressBar.setVisibility(View.INVISIBLE);
                    throwable.printStackTrace();
                    Toast.makeText(getActivity(), R.string.network_error,
                            Toast.LENGTH_SHORT).show();
                });
    }
    private void getPosts(){
        getPostsByDate(dateParam);
    }

    private void addPostsToRealm(Posts posts) {
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(posts));
        realmPosts = realm.where(Posts.class).equalTo(POSTS_PRIMARY_KEY,dateParam).findFirst();
        RealmList <Post> results = realmPosts.getPosts();

        progressBar.setVisibility(View.INVISIBLE);
        initItemTouchListener();
        recyclerViewAdapter = new PostRecyclerAdapter(results,itemTouchListener,getActivity());
         setUpDataFromNetwork();
    }

    private void getPostsByDate(String date){
        api.apiService().getPostsByDate(date)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    posts.setDate(date);
                    addPostsToRealm(posts);
                });
    }

    interface ItemTouchListener extends PostRecyclerAdapter.ItemTouchListener {
        void onItemClick(View view, int position, Post item);
        boolean onItemLongClick(View view, int position, Post item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initItemTouchListener(){
        itemTouchListener = new ItemTouchListener() {
            @Override
            public void onItemClick(View view, int position, Post item) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("postName",item.getName());
                startActivity(intent);
            }
            @Override
            public boolean onItemLongClick(View view, int position, Post item) {
                return true;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searchlist, container, false);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewParent = (FrameLayout) rootView.findViewById(R.id.recView_parent);
        recyclerView = (RecyclerView) recyclerViewParent.findViewById(R.id.recView);

        if(checkRealmCache()){
            progressBar.setVisibility(View.VISIBLE);
            setUpDataFromCache();
        }
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.calendar_fab);
        fab.setOnClickListener(view -> date.show());
        initDialog();
        return rootView;
    }

    private void setUpDataFromNetwork() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        initDialog();
    }

    private void setUpDataFromCache() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        realmPosts = realm.where(Posts.class).equalTo(POSTS_PRIMARY_KEY,dateParam).findFirst();
        RealmList <Post> results = realmPosts.getPosts();
        initItemTouchListener();
        recyclerViewAdapter = new PostRecyclerAdapter(results,itemTouchListener,getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        initDialog();
    }


    public void initDialog(){
         mCalendar = Calendar.getInstance();
            date = new DatePickerDialog(getActivity(), (view, year, monthOfYear, dayOfMonth) -> {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,monthOfYear,dayOfMonth);
                mCalendar = newDate;
                mCalendar.add(Calendar.DATE,0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                dateParam = format1.format(mCalendar.getTime());
                recyclerView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                startAPI();

            }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.DAY_OF_MONTH));

        date.getDatePicker().setMaxDate(System.currentTimeMillis()+1000);
    }

    private void initDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        dateParam = format1.format(cal.getTime());
    }

    private boolean checkRealmCache(){
        realmPosts = realm.where(Posts.class).equalTo("date",dateParam).findFirst();
        if(realmPosts!= null && realmPosts.getPosts().size() > 0){
            return true;
        }
        return false;
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
        return "Product Hunt Plus";
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
