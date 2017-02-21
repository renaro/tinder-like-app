package renaro.tinderlikesample.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

public class AppTaskExecutor implements TaskExecutor {

    private final WeakReference<Activity> mActivityWeakReference;

    public AppTaskExecutor(@NonNull final Activity activity) {
        mActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public <T> void async(@NonNull final AppTask<T> task) {
        new SimpleAsyncTask<>(task).execute();
    }

    private class SimpleAsyncTask<R> extends AsyncTask<Void, Void, R> {

        private final AppTask<R> mTask;

        public SimpleAsyncTask(final AppTask<R> task) {
            mTask = task;
        }

        @Override
        protected R doInBackground(final Void... params) {
            return mTask.execute();
        }

        @Override
        protected void onPostExecute(final R r) {
            if (activityNotFinished()) {
                mTask.onPostExecute(r);
            }
        }

        private boolean activityNotFinished() {
            final Activity activity = mActivityWeakReference.get();
            return activity != null && !activity.isFinishing();
        }
    }
}
