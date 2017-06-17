package haptik.producthunt.task.producthuntplus.base_view;

/**
 * Created by shashank on 6/14/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    public abstract boolean isBackKeyConsumed();

    public abstract String getTitle();

    public String getFragmentTag() {
        return "";
    }
}
