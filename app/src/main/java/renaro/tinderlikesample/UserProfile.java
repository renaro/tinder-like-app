package renaro.tinderlikesample;

/**
 * Created by renarosantos on 05/02/17.
 */
public class UserProfile {

    private final String imageUrl;
    private final String name;
    private final int age;

    public UserProfile(final String imageUrl, final String name, final int age) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
