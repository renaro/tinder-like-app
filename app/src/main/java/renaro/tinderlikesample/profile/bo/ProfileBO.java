package renaro.tinderlikesample.profile.bo;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.util.List;

import renaro.tinderlikesample.UserProfile;
import renaro.tinderlikesample.profile.dao.ProfileDAO;

/**
 * Created by renarosantos on 21/02/17.
 */
public class ProfileBO {

    private static final int SOME_RANDOM_ID = 3;
    private final ProfileDAO mDao;

    public ProfileBO(@NonNull final ProfileDAO dao) {
        mDao = dao;
    }

    public List<UserProfile> fetchProfiles() {
        return mDao.fetchProfiles();
    }

    @WorkerThread
    public boolean profileVoted(final UserProfile profile, final boolean vote) {
        if (vote && profile.getId() == SOME_RANDOM_ID) {
            return true;
        }
        return false;
    }
}
