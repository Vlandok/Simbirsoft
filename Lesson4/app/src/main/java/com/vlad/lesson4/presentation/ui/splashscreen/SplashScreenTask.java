package com.vlad.lesson4.presentation.ui.splashscreen;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import static com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenActivity.SECONDS_SLEEP;

public class SplashScreenTask extends AsyncTask<Void, Void, Void> {
    private final WeakReference<SplashScreenPresenter> splashScreenPresenterWeakReference;
    private final WeakReference<SplashScreenMvpView> splashScreenMvpViewWeakReference;

    SplashScreenTask(SplashScreenPresenter splashScreenPresenter,
                     SplashScreenMvpView splashScreenMvpView) {
        splashScreenPresenterWeakReference = new WeakReference<>(splashScreenPresenter);
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
        if (splashScreenPresenterWeakReference.get() != null) {
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
        if (splashScreenMvpViewWeakReference.get() != null) {
            splashScreenMvpViewWeakReference.get().startMainActivity();
        } else {
            splashScreenMvpViewWeakReference.get().showLoadingError();
        }
    }
}
