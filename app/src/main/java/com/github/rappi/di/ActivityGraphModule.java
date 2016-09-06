package com.github.rappi.di;


import com.github.rappi.theme.ThemeActivity;
import com.github.rappi.themedetail.ThemeDetailActivity;

import dagger.Module;

@Module(
        injects = {
                ThemeActivity.class,
                ThemeDetailActivity.class
        },
        complete = false
)
class ActivityGraphModule {

}
