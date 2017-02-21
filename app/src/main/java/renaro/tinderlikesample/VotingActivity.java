package renaro.tinderlikesample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import renaro.tinderlikesample.base.BaseActivity;
import renaro.tinderlikesample.profile.bo.ProfileBO;
import renaro.tinderlikesample.profile.dao.AppProfileDAO;
import renaro.tinderlikesample.task.AppTaskExecutor;

public class VotingActivity extends BaseActivity<VotingPresenter>
        implements VotingActivityView, SwipeFlingAdapterView.onFlingListener, View.OnClickListener {

    private View loading;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list;
    private SwipeFlingAdapterView mSwipeList;

    @NonNull
    @Override
    protected VotingPresenter createPresenter(@NonNull final Context context) {
        return new VotingPresenter(this, new AppTaskExecutor(this), new ProfileBO(new AppProfileDAO()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        final View positiveButton = findViewById(R.id.positive_button);
        final View negativeButton = findViewById(R.id.negative_button);

        loading = findViewById(R.id.loading);
        mSwipeList = (SwipeFlingAdapterView) findViewById(R.id.swipe_list);
        populateListWithProfile();
        mSwipeList.setFlingListener(this);
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
    }

    private void populateListWithProfile() {
        list = new ArrayList<>();
        list.add("php");
        list.add("c");
        list.add("python");
        list.add("java");
        arrayAdapter = new ArrayAdapter<>(this, R.layout.profile_card, R.id.profile_info, list);
        mSwipeList.setAdapter(arrayAdapter);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProfiles(final List<UserProfile> profiles) {

    }

    @Override
    public void showNegativeVote() {
        mSwipeList.getTopCardListener().selectLeft();
    }

    @Override
    public void showPositiveVote() {
        mSwipeList.getTopCardListener().selectRight();
    }

    @Override
    public void removeFirstObjectInAdapter() {
        list.remove(0);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLeftCardExit(final Object o) {
        Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightCardExit(final Object o) {
        Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdapterAboutToEmpty(final int i) {

    }

    @Override
    public void onScroll(final float v) {

    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.positive_button:
                mPresenter.onPositiveButtonClicked();
                break;
            case R.id.negative_button:
                mPresenter.onNegativeButtonClicked();
                break;
        }
    }
}
