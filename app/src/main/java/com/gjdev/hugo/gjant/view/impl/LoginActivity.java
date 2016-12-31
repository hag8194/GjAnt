package com.gjdev.hugo.gjant.view.impl;

import android.support.v4.app.ActivityOptionsCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.LoginView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.LoginPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.LoginViewModule;
import com.gjdev.hugo.gjant.injection.DaggerLoginViewComponent;
import com.squareup.otto.Bus;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {
    @Inject
    PresenterFactory<LoginPresenter> mPresenterFactory;

    @Inject
    Bus bus;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login_progressbar)
    ProgressBar loginProgressBar;

    @OnClick(R.id.login)
    public void onClick() {
        mPresenter.onSubmit(username.getText().toString(), password.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.

    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerLoginViewComponent.builder()
                .appComponent(parentComponent)
                .loginViewModule(new LoginViewModule())
                .build()
                .inject(this);
    }

    @Override
    public void toggleFieldsState() {
        Button loginButton = ButterKnife.findById(this, R.id.login);
        username.setEnabled(!username.isEnabled());
        password.setEnabled(!password.isEnabled());
        loginButton.setEnabled(!loginButton.isEnabled());
    }

    @Override
    public void showProgressBar() {
        this.loginProgressBar.setVisibility(this.loginProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        this.loginProgressBar.setVisibility(this.loginProgressBar.GONE);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.login_container), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
