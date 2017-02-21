package renaro.tinderlikesample.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import renaro.tinderlikesample.UserProfile;

/**
 * Created by renarosantos on 21/02/17.
 */
public class FetchProfileResponse {

    final ArrayList<RemoteProfile> profiles;

    public FetchProfileResponse(final ArrayList<RemoteProfile> profiles) {
        this.profiles = profiles;
    }

    @NonNull
    public ArrayList<UserProfile> getProfiles() {
        return convertRemoteToLocalProfiles(profiles);
    }

    @NonNull
    private ArrayList<UserProfile> convertRemoteToLocalProfiles(final ArrayList<RemoteProfile> profiles) {
        if (profiles != null) {
            final ArrayList<UserProfile> result = new ArrayList<>(profiles.size());
            for (final RemoteProfile profile : profiles) {
                result.add(UserProfile.from(profile));
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
