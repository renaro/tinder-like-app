package renaro.tinderlikesample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import renaro.tinderlikesample.base.BaseActivity;

public class VotingActivity extends BaseActivity<VotingPresenter> implements VotingActivityView {

    private View loading;

    @NonNull
    @Override
    protected VotingPresenter createPresenter(@NonNull final Context context) {
        return new VotingPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        loading = findViewById(R.id.loading);
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
}
