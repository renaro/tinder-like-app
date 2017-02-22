package renaro.tinderlikesample.profile.bo;

import android.support.annotation.NonNull;

import java.util.List;

import renaro.tinderlikesample.UserProfile;
import renaro.tinderlikesample.profile.dao.ProfileDAO;

/**
 * Created by renarosantos on 21/02/17.
 */
public class ProfileBO {

    private final ProfileDAO mDao;

    public ProfileBO(@NonNull final ProfileDAO dao) {
        mDao = dao;
    }

    public List<UserProfile> fetchProfiles() {
        return mDao.fetchProfiles();
    }

    public void profileVoted(final UserProfile profile, final boolean vote) {

    }
}
