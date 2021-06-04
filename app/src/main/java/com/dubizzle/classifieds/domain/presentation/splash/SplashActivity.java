package com.dubizzle.classifieds.domain.presentation.splash;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import com.dubizzle.classifieds.databinding.ActivitySplashBinding;
import com.dubizzle.classifieds.domain.presentation.base.BaseActivity;
import com.dubizzle.classifieds.domain.presentation.classifiedslist.ClassifiedsActivity;
import com.dubizzle.classifieds.utility.Constants;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;

    @Deprecated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
    }

    private void initUI() {
        startLogoAnimation();
        new Handler(Looper.getMainLooper()).postDelayed(this::openClassifiedsListActivity, Constants.SPLASH_TIME);
    }

    private void openClassifiedsListActivity() {
        finish();
        Intent intent = new Intent(SplashActivity.this, ClassifiedsActivity.class);
        startActivity(intent);
    }

    private void startLogoAnimation() {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(binding.logoImageView, "alpha", 0, 1f);
        animator1.setRepeatCount(0);
        animator1.setDuration(500);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(binding.logoImageView, "scaleX", 0, 1f);
        animator2.setRepeatCount(0);
        animator2.setDuration(500);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(binding.logoImageView, "scaleY", 0, 1f);
        animator3.setRepeatCount(0);
        animator3.setDuration(500);
        set.playTogether(animator1, animator2, animator3);
        set.start();
    }
}