package renaro.tinderlikesample.model;

/**
 * Created by renarosantos on 21/02/17.
 */
public class RemoteProfile {

    public final String name;
    public final String age;
    public final String cover;

    public RemoteProfile(final String name, final String age, final String cover) {
        this.name = name;
        this.age = age;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCover() {
        return cover;
    }
}
