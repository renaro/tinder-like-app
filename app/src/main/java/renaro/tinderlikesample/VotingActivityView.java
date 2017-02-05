package renaro.tinderlikesample;

import java.util.List;

/**
 * Created by renarosantos on 05/02/17.
 */
public interface VotingActivityView {

    void showLoading();

    void hideLoading();

    void showProfiles(List<UserProfile> profiles);
}
