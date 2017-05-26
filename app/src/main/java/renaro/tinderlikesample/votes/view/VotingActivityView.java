package renaro.tinderlikesample.votes.view;

import java.util.List;

import renaro.tinderlikesample.UserProfile;

/**
 * Created by renarosantos on 05/02/17.
 */
public interface VotingActivityView {

    void showLoading();

    void hideLoading();

    void showProfiles(List<UserProfile> profiles);

    void showNegativeVote();

    void showPositiveVote();

    void showMatch(UserProfile profile);

    int cardsLeft();

    void showOutOfVotes();
}
