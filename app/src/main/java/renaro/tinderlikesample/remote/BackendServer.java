package renaro.tinderlikesample.remote;

import java.util.List;

import renaro.tinderlikesample.model.FetchProfileResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by renarosantos on 21/02/17.
 */

public interface BackendServer {

    @GET("profiles/")
        Call<FetchProfileResponse> fetchProfiles();
}
