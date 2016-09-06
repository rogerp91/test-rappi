package com.github.rappi.themedetail;

import android.provider.BaseColumns;

import com.github.rappi.data.source.ThemeDataSource;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public interface ThemeDetailContract {

    // TODO: View
    interface View {

        void showTitle(String title);

        void showDate(String date);

        void showImageBanner(String img);

        void showDescription(String description);

        void showSubmit(String submit);

        boolean isActive();

    }


    // TODO: Presenter
    interface Presenter {

        void setView(ThemeDetailContract.View view);

        void start(String themeId);

    }

}