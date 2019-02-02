package com.vlad.lesson4.presentation.ui.splashscreen;

import android.content.Context;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import static com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenActivity.SECONDS_SLEEP;

public class SplashScreenTask extends AsyncTask<Void, Void, Void> {
    private final WeakReference<SplashScreenPresenter> splashScreenPresenterWeakReference;
    private final WeakReference<Context> contextWeakReference;
    private final WeakReference<SplashScreenMvpView> splashScreenMvpViewWeakReference;

    SplashScreenTask(SplashScreenPresenter splashScreenPresenter, Context context,
                     SplashScreenMvpView splashScreenMvpView) {
        splashScreenPresenterWeakReference = new WeakReference<>(splashScreenPresenter);
        contextWeakReference = new WeakReference<>(context);
        splashScreenMvpViewWeakReference = new WeakReference<>(splashScreenMvpView);
    }

    @Override
    protected void onPreExecute() {
        if (splashScreenMvpViewWeakReference.get() != null) {
            splashScreenMvpViewWeakReference.get().showProgressView();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (contextWeakReference.get() != null && splashScreenPresenterWeakReference.get() != null) {
            splashScreenPresenterWeakReference.get().saveToRealm(contextWeakReference.get());
            try {
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (contextWeakReference.get() != null && splashScreenMvpViewWeakReference.get() != null) {
            splashScreenMvpViewWeakReference.get().startMainActivity();
        } else {
            splashScreenMvpViewWeakReference.get().showLoadingError();
        }
    }
}
