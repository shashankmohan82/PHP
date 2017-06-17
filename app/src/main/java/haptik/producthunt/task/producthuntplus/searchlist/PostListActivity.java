package haptik.producthunt.task.producthuntplus.searchlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import haptik.producthunt.task.producthuntplus.base_view.BaseActivity;
import haptik.producthunt.task.producthuntplus.base_view.BaseFragment;
import haptik.producthunt.task.producthuntplus.R;


public class PostListActivity extends BaseActivity {

    private BaseFragment currentFragment;
    private AppCompatTextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layoutView = getLayoutInflater().inflate(R.layout.activity_search_list, null, false);
        setContentView(layoutView);
        showThisFragment(PostListFragment.newInstance());
        Log.d("hi","onCreteActi");
        title = (AppCompatTextView)findViewById(R.id.post_title);
        title.setText("Posts");
    }

    @Override
    public void onHomeBackKeyPressed() {
        super.onBackPressed();
    }

    public void showThisFragment(BaseFragment baseFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_search_list, baseFragment, baseFragment.getFragmentTag());
        fragmentTransaction.commitAllowingStateLoss();
        currentFragment = baseFragment;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
