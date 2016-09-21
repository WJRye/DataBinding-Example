package com.wj.databinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wj.databinding.databinding.UserBinding;
import com.wj.databinding.model.User;
import com.wj.databinding.module.main.MainActivity;

public class LoginActivity extends BaseActivity {

    private UserBinding mUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserBinding = (UserBinding) getViewDataBinding();
    }

    @Override
    public final int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public final void setListener() {
        ((UserBinding) getViewDataBinding()).setListener(this);
    }


    @Override
    public final void onClick(View v) {

        final String username = mUserBinding.loginUsername.getText().toString();
        final String password = mUserBinding.loginPassword.getText().toString();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            final LoginDialogFragment loginDialog = new LoginDialogFragment();
            loginDialog.show(getSupportFragmentManager(), "Login");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    loginDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    User user = new User();
                    user.setName(username);
                    user.setPassword(password);
                    user.setAge(18);
                    user.setSex(1);
                    user.setEmail("1126594968@qq.com");
                    user.setAddress("北京市海淀区中关村");
                    user.setNumber("08613568879279");
                    intent.putExtra(MainActivity.USER, user);
                    startActivity(intent);
                }
            }, 2000);

        }

    }

    public final TextWatcher addTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"123".equals(s.toString())) {
                    mUserBinding.loginUsername.setError(getString(R.string.user_real_name));
                    mUserBinding.loginButton.setEnabled(false);
                } else {
                    mUserBinding.loginButton.setEnabled(true);
                }
            }
        };
    }


    public static class LoginDialogFragment extends DialogFragment {

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            setStyle(STYLE_NO_TITLE, 0);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.login_dialog, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = getResources().getDisplayMetrics().widthPixels / 8 * 7;
            params.height = getResources().getDimensionPixelSize(R.dimen.size_100dp);
            window.setAttributes(params);

        }
    }

}
