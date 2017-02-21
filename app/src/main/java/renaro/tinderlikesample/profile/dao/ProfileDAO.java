package renaro.tinderlikesample.profile.dao;

import java.util.List;

import renaro.tinderlikesample.UserProfile;

/**
 * Created by renarosantos on 21/02/17.
 */
public abstract class ProfileDAO {

    public abstract List<UserProfile> fetchProfiles();
}
