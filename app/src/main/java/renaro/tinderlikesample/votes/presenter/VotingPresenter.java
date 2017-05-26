package renaro.tinderlikesample.votes.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import renaro.tinderlikesample.UserProfile;
import renaro.tinderlikesample.base.BasePresenter;
import renaro.tinderlikesample.profile.bo.ProfileBO;
import renaro.tinderlikesample.task.AppTask;
import renaro.tinderlikesample.task.TaskExecutor;
import renaro.tinderlikesample.votes.model.VoteResponse;
import renaro.tinderlikesample.votes.view.VotingActivityView;

/**
 * Created by renarosantos on 05/02/17.
 */
public class VotingPresenter extends BasePresenter {

    private final VotingActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ProfileBO mProfileBO;
    private UserProfile mLastSeenProfile;

    public VotingPresenter(@NonNull final VotingActivityView view, @NonNull final TaskExecutor taskExecutor,
                           @NonNull final ProfileBO profileBO) {
        mTaskExecutor = taskExecutor;
        mView = view;
        mProfileBO = profileBO;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchProfilesTask();
    }

    private void fetchProfilesTask() {
        mView.showLoading();
        mTaskExecutor.async(new FetchProfilesTask());
    }

    public void onNegativeButtonClicked() {
        mView.showNegativeVote();
    }

    public void onPositiveButtonClicked() {
        mView.showPositiveVote();
    }

    public void onProfileRemoved(final UserProfile profile) {
        mLastSeenProfile = profile;
    }

    public void onSlideProfileToLeft() {
        mTaskExecutor.async(new VoteTask(false));
    }

    public void onSlideProfileToRight() {
        mTaskExecutor.async(new VoteTask(true));
    }

    public void onEmptyList() {
        fetchProfilesTask();
    }

    private class FetchProfilesTask implements AppTask<List<UserProfile>> {

        @Override
        public List<UserProfile> execute() {
            return mProfileBO.fetchProfiles();
        }

        @Override
        public void onPostExecute(@Nullable final List<UserProfile> result) {
            mView.hideLoading();
            mView.showProfiles(result);
        }
    }

    private class VoteTask implements AppTask<VoteResponse> {

        private final boolean mVote;

        public VoteTask(final boolean vote) {
            mVote = vote;
        }

        @Override
        public VoteResponse execute() {
            return mProfileBO.profileVoted(mLastSeenProfile, mVote);
        }

        @Override
        public void onPostExecute(@Nullable final VoteResponse result) {
            if (result != null && result.isMatch()) {
                mView.showMatch(mLastSeenProfile);
            } else if(result !=null && result.isOutOfVotes()){
                mView.showOutOfVotes();
            }
        }
    }


    /*
    * method to demonstrate how to get data from the view Interface
    * */
    private void checkCardsLeft() {
        int amountLeft = mView.cardsLeft();
        checkIfActionIsRerquired(amountLeft);
    }

    /*
    * This method is intentionally left blank for demo purposes
    * */
    private void checkIfActionIsRerquired(int amountLeft) {
        //intentionally left blank
    }


}
