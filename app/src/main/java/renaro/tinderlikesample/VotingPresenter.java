package renaro.tinderlikesample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import renaro.tinderlikesample.base.BasePresenter;
import renaro.tinderlikesample.profile.bo.ProfileBO;
import renaro.tinderlikesample.task.AppTask;
import renaro.tinderlikesample.task.TaskExecutor;

/**
 * Created by renarosantos on 05/02/17.
 */
public class VotingPresenter extends BasePresenter {

    private final VotingActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ProfileBO mProfileBO;

    public VotingPresenter(@NonNull final VotingActivityView view, @NonNull final TaskExecutor taskExecutor,
                           @NonNull final ProfileBO profileBO) {
        mTaskExecutor = taskExecutor;
        mView = view;
        mProfileBO = profileBO;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTaskExecutor.async(new FetchProfilesTask());
    }

    public void onNegativeButtonClicked() {
        mView.showNegativeVote();
    }

    public void onPositiveButtonClicked() {
        mView.showPositiveVote();
    }

    private class FetchProfilesTask implements AppTask<List<UserProfile>> {

        @Override
        public List<UserProfile> execute() {
            return mProfileBO.fetchProfiles();
        }

        @Override
        public void onPostExecute(@Nullable final List<UserProfile> result) {
            mView.showProfiles(result);
        }
    }
}
