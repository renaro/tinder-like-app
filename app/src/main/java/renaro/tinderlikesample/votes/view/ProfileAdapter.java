package renaro.tinderlikesample.votes.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.Locale;

import renaro.tinderlikesample.R;
import renaro.tinderlikesample.UserProfile;

/**
 * Created by renarosantos on 21/02/17.
 */
public class ProfileAdapter extends ArrayAdapter<UserProfile> {

    public static final String NAME_AND_AGE_STRING_FORMAT = "%s, %d";
    private final Context mContext;
    private UserProfile[] mProfiles;
    private ProfileListener mListener;

    public ProfileAdapter(final Context context, final int resource, final UserProfile[] objects) {
        super(context, resource, objects);
        mContext = context;
        mProfiles = objects;
    }

    @Override
    public int getCount() {
        return mProfiles.length;
    }

    public void setListener(final ProfileListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View root = null;
        if (mProfiles != null && mProfiles.length > position) {
            UserProfile profile = mProfiles[position];
            LayoutInflater inflater = LayoutInflater.from(mContext);
            if (convertView == null) {
                root = inflater.inflate(R.layout.profile_card, parent, false);
            } else {
                root = convertView;
            }
            TextView nameTextView = (TextView) root.findViewById(R.id.profile_info);
            ImageView imageView = (ImageView) root.findViewById(R.id.image);
            nameTextView.setText(String.format(Locale.getDefault(), NAME_AND_AGE_STRING_FORMAT, profile.getName(),
                    profile.getAge()));

            Glide.with(mContext).load(profile.getImageUrl()).centerCrop().crossFade().into(imageView);
        }
        return root != null ? root : convertView;
    }

    public void removeTop() {
        if(mProfiles.length == 0){
            return;
        }
        UserProfile removedProfile = mProfiles[0];
        if (mProfiles.length > 1) {
            mProfiles = Arrays.copyOfRange(mProfiles, 1, mProfiles.length);
        } else {
            mProfiles = new UserProfile[0];
        }
        if (mListener != null) {
            if (mProfiles.length > 0) {
                mListener.onProfileRemoved(removedProfile);
            } else {
                mListener.onEmptyList();
            }
        }
        notifyDataSetChanged();
    }

    public interface ProfileListener {

        void onProfileRemoved(@NonNull final UserProfile profile);

        void onEmptyList();
    }
}
