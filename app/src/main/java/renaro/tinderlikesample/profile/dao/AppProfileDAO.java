package renaro.tinderlikesample.profile.dao;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import renaro.tinderlikesample.BuildConfig;
import renaro.tinderlikesample.UserProfile;
import renaro.tinderlikesample.model.FetchProfileResponse;
import renaro.tinderlikesample.remote.BackendServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renarosantos on 21/02/17.
 */

public class AppProfileDAO extends ProfileDAO {

    private static final int SOME_RANDOM_ID = 3;
    private final BackendServer mService;
    private int MOCK_WAIT_INTERVAL = 1500;

    public AppProfileDAO() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.MOCK_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(BackendServer.class);
    }

    @Override
    public List<UserProfile> fetchProfiles() {
        try {
            Response<FetchProfileResponse> response = mService.fetchProfiles().execute();
            if (response.body() != null) {
                Thread.sleep(MOCK_WAIT_INTERVAL);
                return response.body().getProfiles();
            }
        } catch (IOException e) {
            Log.e("ERROR", "Internet Connection", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public int fetchRemainingVotes() {
        return 3;
    }

    @Override
    public boolean voteProfile(final UserProfile profile, final boolean vote) {
        return profile.getId() == SOME_RANDOM_ID && vote;
    }
}
