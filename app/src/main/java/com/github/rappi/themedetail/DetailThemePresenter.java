package com.github.rappi.themedetail;

import com.github.rappi.data.entity.Child;
import com.github.rappi.data.entity.Data_;
import com.github.rappi.data.source.ThemeDataSource;
import com.github.rappi.theme.ThemeContract;
import com.github.rappi.utils.Functions;
import com.github.rappi.utils.SelectsImage;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roger Patiño on 06/09/2016.
 */

public class DetailThemePresenter implements ThemeDetailContract.Presenter {

    private ThemeDetailContract.View view;
    private ThemeContract.Repository repository;

    @Inject
    DetailThemePresenter(ThemeContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(ThemeDetailContract.View view) {
        checkNotNull(view, "View not null!");
        this.view = view;
    }

    @Override
    public void start(String themeId) {
        repository.getTheme(themeId, new ThemeDataSource.GetThemeCallback() {
            @Override
            public void onThemeLoaded(Child child) {
                if (!view.isActive()) {
                    return;
                }

                showTheme(child);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void showTheme(Child child) {
        Data_ data_ = child.getData();
        view.showTitle(data_.getTitle());
        view.showDate(Functions.getDate(data_.getCreatedUtc()));
        String url = data_.getBannerImg();
        if (url.isEmpty()) {
            url = SelectsImage.selectImg();
        }
        view.showImageBanner(url);

        view.showDescription(data_.getDescription());
        view.showSubmit(data_.getSubmitText());

    }

}
