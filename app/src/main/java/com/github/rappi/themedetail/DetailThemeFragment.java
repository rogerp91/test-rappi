package com.github.rappi.themedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rappi.R;
import com.github.rappi.RappiApplication;
import com.github.rappi.fragment.BaseFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class DetailThemeFragment extends BaseFragment implements ThemeDetailContract.View {

    private static String TAG = DetailThemeFragment.class.getSimpleName();

    public static final String ARGUMENT_THEME_ID = "THEME_ID";

    @State
    String mThemeId; // Landscape o portrait se pierde el contexto


    public static DetailThemeFragment newInstance(String themeId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_THEME_ID, themeId);
        DetailThemeFragment fragment = new DetailThemeFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @BindView(R.id.img_banner)
    ImageView imageBanner;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.date)
    TextView mDate;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.submit_text)
    TextView mSubmitText;

    @Inject
    ThemeDetailContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mThemeId = getArguments().getString(ARGUMENT_THEME_ID);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            presenter.start(mThemeId);
        } catch (NullPointerException e) {// Landscape o portrait se pierde el contexto
            Log.e(TAG, "onResume: Null savedInstanceState");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
    }

    @Override
    public void showTitle(String title) {
        mName.setText(title);
        ab.setTitle(title);
    }

    @Override
    public void showDate(String date) {
        mDate.setText(date);
    }

    @Override
    public void showImageBanner(String img) {
        Picasso.with(RappiApplication.getContext()).load(img).into(imageBanner);
    }

    @Override
    public void showDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void showSubmit(String submit) {
        mSubmitText.setText(submit);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}