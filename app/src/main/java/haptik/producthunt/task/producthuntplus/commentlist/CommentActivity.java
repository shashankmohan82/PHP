package haptik.producthunt.task.producthuntplus.commentlist;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import haptik.producthunt.task.producthuntplus.R;
import haptik.producthunt.task.producthuntplus.base_view.BaseActivity;
import haptik.producthunt.task.producthuntplus.base_view.BaseFragment;

public class CommentActivity extends BaseActivity {

    private BaseFragment currentFragment;
    private AppCompatTextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layoutView = getLayoutInflater().inflate(R.layout.activity_search_list, null, false);
        setContentView(layoutView);
        showThisFragment(CommentFragment.newInstance());
        title = (AppCompatTextView)findViewById(R.id.post_title);
        title.setText("Comments");
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
