package com.github.rappi.di;

import com.github.rappi.theme.ThemeFragment;
import com.github.rappi.themedetail.DetailThemeFragment;

import dagger.Module;

/**
 * Created by Roger Patiño on 22/12/2015.
 */
@Module(injects = {
        ThemeFragment.class,
        DetailThemeFragment.class

}, complete = false, library = true)
public class FragmentGraphInjectModule {
}
