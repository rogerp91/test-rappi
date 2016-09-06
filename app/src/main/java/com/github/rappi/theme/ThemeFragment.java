package com.github.rappi.theme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rappi.R;
import com.github.rappi.adapte.ThemeAdapte;
import com.github.rappi.data.entity.Child;
import com.github.rappi.data.entity.Data_;
import com.github.rappi.fragment.BaseFragment;
import com.github.rappi.themedetail.ThemeDetailActivity;
import com.github.roger91.mlprogress.MlProgress;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ThemeFragment extends BaseFragment implements ThemeContract.View {

    public static ThemeFragment newInstance() {
        return new ThemeFragment();
    }

    @BindView(R.id.progress)
    MlProgress mProgressView;
    @BindView(R.id.layout_message)
    RelativeLayout layout_message;
    @BindView(R.id.text_message)
    TextView text_message;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private ThemeAdapte adapte;

    @Inject
    ThemeContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setHasFixedSize(true);
        LinearLayoutManager linearManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearManager);
        presenter.setView(this);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.primary),
                ContextCompat.getColor(getActivity(), R.color.accent),
                ContextCompat.getColor(getActivity(), R.color.primary_dark)
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadModels(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //presenter.checkInternet();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //presenter.checkInternet();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl = (SwipeRefreshLayout) getView().findViewById(R.id.swipe);
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTheme(List<Child> children) {
        adapte = new ThemeAdapte(children, themeItemListener);
        adapte.setHasStableIds(true);
        recycler.setAdapter(adapte);
    }

    @Override
    public void showThemeDetailsUi(String themeId) {
        Intent intent = new Intent(getActivity(), ThemeDetailActivity.class);
        intent.putExtra(ThemeDetailActivity.EXTRA_TASK_ID, themeId);
        startActivity(intent);
    }

    @Override
    public void showProgress(final boolean active) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        mProgressView.setVisibility(active ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(active ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(active ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showNoTheme(final boolean active) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        layout_message.setVisibility(active ? View.VISIBLE : View.GONE);
        layout_message.animate().setDuration(shortAnimTime).alpha(active ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                layout_message.setVisibility(active ? View.VISIBLE : View.GONE);
            }
        });
        text_message.setText(getString(R.string.no_data_available));
    }

    @Override
    public void showSuccessfullyMessage() {
        Snackbar.make(getView(), getActivity().getResources().getString(R.string.load_content), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkError(final boolean active) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        layout_message.setVisibility(active ? View.VISIBLE : View.GONE);
        layout_message.animate().setDuration(shortAnimTime).alpha(active ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                layout_message.setVisibility(active ? View.VISIBLE : View.GONE);
            }
        });
        text_message.setText(getString(R.string.no_connection));
    }

    @Override
    public void showErrorOcurred(final boolean active) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        layout_message.setVisibility(active ? View.VISIBLE : View.GONE);
        layout_message.animate().setDuration(shortAnimTime).alpha(active ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                layout_message.setVisibility(active ? View.VISIBLE : View.GONE);
            }
        });
        text_message.setText(getString(R.string.error_occurred));
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showOnline() {
        Toast.makeText(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.yes_connection), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showNotOnline() {
        Toast.makeText(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @OnClick(R.id.text_try_again)
    void onClickTryAgain() {
        presenter.start();
    }

    ThemeItemListener themeItemListener = new ThemeItemListener() {
        @Override
        public void onDetailTheme(Data_ data_) {
            presenter.themeOpen(data_.getId());
        }
    };

    public interface ThemeItemListener {

        void onDetailTheme(Data_ data_);

    }

}