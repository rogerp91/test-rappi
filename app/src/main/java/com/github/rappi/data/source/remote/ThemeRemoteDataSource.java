package com.github.rappi.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.rappi.data.Theme;
import com.github.rappi.data.entity.Child;
import com.github.rappi.data.source.ThemeDataSource;
import com.github.rappi.executor.Interactor;
import com.github.rappi.executor.ThreadExecutor;
import com.github.rappi.theme.ThemeContract;
import com.github.rappi.utils.Networks;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public class ThemeRemoteDataSource implements ThemeContract.ThemeRemoteDataSource {

    private static String TAG = ThemeRemoteDataSource.class.getSimpleName();
    private ThreadExecutor threadExecutor;

    @Inject
    HttpRestClient client;

    @Inject
    ThemeRemoteDataSource() {
        threadExecutor = new ThreadExecutor();
    }

    @Override
    public void getThemes(@NonNull final LoadThemeCallback callback) {
        threadExecutor.run(new Interactor() {
            @Override
            public void run() {
                if (!Networks.isOnline(null)) {
                    callback.onErrorNetwork();
                    return;
                }
                Call<Theme> call = client.performTheme();
                call.enqueue(new Callback<Theme>() {
                    @Override
                    public void onResponse(Call<Theme> call, Response<Theme> response) {
                        if (!response.isSuccessful()) {
                            callback.onErrorOcurred();
                        } else {
                            callback.onThemeLoaded(response.body().getData().getChildren());
                        }
                    }

                    @Override
                    public void onFailure(Call<Theme> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        callback.onErrorOcurred();
                    }
                });
            }
        });
    }

    @Override
    public void getThemesLocal(@NonNull GetThemesLocalCallback callback) {

    }

    @Override
    public void getTheme(@NonNull String themeId, @NonNull GetThemeCallback callback) {

    }

    @Override
    public void saveTheme(@NonNull Child child) {

    }

    @Override
    public void refreshThemes(@NonNull LoadThemeCallback callback) {
        getThemes(callback);
    }

    @Override
    public void deleteAllThemes() {

    }
}
