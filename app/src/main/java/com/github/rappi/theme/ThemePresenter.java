package com.github.rappi.theme;

import com.github.rappi.R;
import com.github.rappi.RappiApplication;
import com.github.rappi.data.entity.Child;
import com.github.rappi.data.source.ThemeDataSource;
import com.github.rappi.utils.Networks;

import java.util.List;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public class ThemePresenter implements ThemeContract.Presenter {

    private ThemeContract.View view;
    private ThemeContract.Repository repository;

    @Inject
    ThemePresenter(ThemeContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(ThemeContract.View view) {
        checkNotNull(view, "View not null!");
        this.view = view;
    }

    @Override
    public void start() {
        loadModels(true);
    }

    @Override
    public void loadModels(boolean showLoadingUI) {
        if (showLoadingUI) { // Colocar el cargando
            onProgress();
        } else {
            onIndicator();
        }
    }

    /**
     * Progress
     */
    private void onProgress() {
        view.showProgress(true);
        view.showNoTheme(false);
        view.showNetworkError(false);
        view.showErrorOcurred(false);
        repository.getThemes(new ThemeDataSource.LoadThemeCallback() {
            @Override
            public void onThemeLoaded(List<Child> children) {
                if (!view.isActive()) {
                    return;
                }
                view.showProgress(false);
                if (children.isEmpty()) {
                    view.showNoTheme(true);
                } else {
                    //view.showSuccessfullyMessage();
                    view.showTheme(children);
                }
            }

            @Override
            public void onErrorOcurred() {
                if (!view.isActive()) {
                    return;
                }
                view.showProgress(false);
                view.showErrorOcurred(true);
            }

            @Override
            public void onErrorNetwork() {
                if (!view.isActive()) {
                    return;
                }
                view.showProgress(false);
                view.showNetworkError(true);
            }

            @Override
            public void onDataNotAvailable() {
                if (!view.isActive()) {
                    return;
                }
                view.showProgress(false);
                view.showNoTheme(true);
            }
        });
    }

    /**
     * SwipeRefreshLayout
     */
    private void onIndicator() {
        view.setLoadingIndicator(true);
        repository.refreshThemes(new ThemeDataSource.LoadThemeCallback() {
            @Override
            public void onThemeLoaded(List<Child> children) {
                if (!view.isActive()) {
                    return;
                }
                view.setLoadingIndicator(false);
                if (children.isEmpty()) {
                    view.showNoTheme(true);
                } else {
                    //view.showSuccessfullyMessage();
                    view.showTheme(children);
                }
            }

            @Override
            public void onErrorOcurred() {
                if (!view.isActive()) {
                    return;
                }
                view.setLoadingIndicator(false);
                view.showMessage(RappiApplication.getContext().getString(R.string.error_occurred));
            }

            @Override
            public void onErrorNetwork() {
                if (!view.isActive()) {
                    return;
                }
                view.setLoadingIndicator(false);
                view.showMessage(RappiApplication.getContext().getString(R.string.no_connection));
            }

            @Override
            public void onDataNotAvailable() {
                if (!view.isActive()) {
                    return;
                }
                view.setLoadingIndicator(false);
                view.showMessage(RappiApplication.getContext().getString(R.string.no_data_available));
            }
        });
    }

    @Override
    public void deleteAllModels() {
        repository.deleteAllThemes();
    }

    @Override
    public void checkInternet() {
        if (!Networks.isOnline(null)) view.showNotOnline();
        else view.showOnline();
    }

    @Override
    public void themeOpen(String idTheme) {
        view.showThemeDetailsUi(idTheme);
    }
}