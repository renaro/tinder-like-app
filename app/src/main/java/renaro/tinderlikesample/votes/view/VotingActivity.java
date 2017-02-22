package renaro.tinderlikesample.votes.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.List;

import renaro.tinderlikesample.R;
import renaro.tinderlikesample.UserProfile;
import renaro.tinderlikesample.base.BaseActivity;
import renaro.tinderlikesample.profile.bo.ProfileBO;
import renaro.tinderlikesample.profile.dao.AppProfileDAO;
import renaro.tinderlikesample.task.AppTaskExecutor;
import renaro.tinderlikesample.votes.presenter.VotingPresenter;

public class VotingActivity extends BaseActivity<VotingPresenter>
        implements VotingActivityView, SwipeFlingAdapterView.onFlingListener, View.OnClickListener,
                   ProfileAdapter.ProfileListener {

    private View loading;
    private SwipeFlingAdapterView mSwipeList;
    private ProfileAdapter mAdapter;

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
        mSwipeList.setFlingListener(this);
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
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
        UserProfile[] array = new UserProfile[profiles.size()];
        mAdapter = new ProfileAdapter(this, R.layout.profile_card, profiles.toArray(array));
        mAdapter.setListener(this);
        mSwipeList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

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
        mAdapter.removeTop();
    }

    @Override
    public void onLeftCardExit(final Object o) {
        mPresenter.leftCardExit();
    }

    @Override
    public void onRightCardExit(final Object o) {
        mPresenter.rightCardExit();
    }

    @Override
    public void onAdapterAboutToEmpty(final int i) {
        //intentionally left in blank
    }

    @Override
    public void onScroll(final float v) {
        //intentionally left in blank
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

    @Override
    public void onProfileRemoved(@NonNull final UserProfile profile) {
        mPresenter.onProfileRemoved(profile);
    }

    @Override
    public void onEmptyList() {
        mPresenter.onEmptyList();
    }
}
