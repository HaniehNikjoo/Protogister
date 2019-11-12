package com.example.prototypedesignpattern;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.prototypedesignpattern.db.Member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindViews({ R.id.welcome_register_linearlayout_root, R.id.first_register_linearlayout_root, R.id.second_register_linearlayout_root})
    View[] arrayRoot;
    @BindViews({ R.id.indicator1, R.id.indicator2, R.id.indicator3})View[] arrayIndicator;
    @BindView(R.id.first_register_edittext_name) EditText edittext_name;
    @BindView(R.id.first_register_edittext_family) EditText edittext_family;
    @BindView(R.id.first_register_edittext_phone) EditText edittext_phone;
    @BindView(R.id.first_register_edittext_address) EditText edittext_address;
    @BindView(R.id.second_register_edittext_sex) EditText edittext_sex;
    @BindView(R.id.second_register_edittext_age) EditText edittext_age;
    @BindView(R.id.second_register_edittext_weight) EditText edittext_weight;
    @BindView(R.id.second_register_edittext_height) EditText edittext_height;
    private static final int COUNTER_SLIDER=2;

    String name,family,phone,address,age,weight,height;
    Member member=new Member();
    Register register;

    byte counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        ButterKnife.bind(RegisterActivity.this);
        register = new Register(member);

        gestureDetector(arrayRoot);
    }

    private void gestureDetector(final View...views){
        for (View view : views) {
            view.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this) {
                public void onSwipeRight() {
                    goBack(views);
                }
                public void onSwipeLeft() {
                    goNext(views);
                }
            });
        }
    }

    private void showStep(View view){
        for (View indicator : arrayIndicator) {
            if (indicator.equals(view)) {
                indicator.setBackground(getResources().getDrawable(R.drawable.circle));
                continue;
            }
            indicator.setBackground(getResources().getDrawable(R.drawable.circle_off));
        }
    }

    @SuppressLint("ResourceType")
    private void goNext(View...views){
        if(counter<COUNTER_SLIDER) {
            final int finalC = counter;
            views[finalC].setVisibility(View.GONE);
            final int finalN = ++counter;
            views[finalN].setVisibility(View.VISIBLE);
            showStep(arrayIndicator[counter]);
            views[finalN].startAnimation(AnimationUtils.loadAnimation(this,R.transition.animation_leave));
            views[finalC].startAnimation(AnimationUtils.loadAnimation(this,R.transition.animation_enter));
        }else if (counter==arrayIndicator.length-1){
            register.setId(getKey());
            register.setName(edittext_name.getText().toString());
            register.setFamily(edittext_family.getText().toString());
            register.setPhone(edittext_phone.getText().toString());
            register.setAddress(edittext_address.getText().toString());
            register.setSex(edittext_sex.getText().toString());
            register.setAge(edittext_age.getText().toString());
            register.setWeight(edittext_weight.getText().toString());
            register.setHeight(edittext_height.getText().toString());
//            register.addMember(getKey(),name,family,phone,address,age,weight,height);
            register.addMember();
            Intent intent =new Intent(RegisterActivity.this,ListActivity.class);
            this.overridePendingTransition(R.transition.fadeout,R.transition.fadein);
            startActivity(intent);
//            register.getAllMembers();
        }
    }

    @SuppressLint("ResourceType")
    private void goBack(View...views){
        if(counter>0) {
            final int finalC = counter;
            views[finalC].setVisibility(View.GONE);
            final int finalP = --counter;
            views[finalP].setVisibility(View.VISIBLE);
            showStep(arrayIndicator[counter]);
            views[finalP].startAnimation(AnimationUtils.loadAnimation(this,R.transition.animation_leave));
            views[finalC].startAnimation(AnimationUtils.loadAnimation(this,R.transition.animation_enter));
        }
    }

    @OnClick({R.id.first_register_button_next, R.id.second_register_button_next})
    public void onClickNext() {
        goNext(arrayRoot);
    }

    @Override
    public void onBackPressed() {
        if(counter==0)finish();
        goBack(arrayRoot);
    }

    public String getKey(){
        String key=(md5(getTimeZone()+"yourkey")).toLowerCase();
        return key;
    }

    private String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getTimeZone() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat date_format = new SimpleDateFormat("yyyyMdHmS");
        date_format.setTimeZone(timeZone);
        Date date = new Date();
        timeZone = TimeZone.getTimeZone("Asia/Tehran");
        date_format.setTimeZone(timeZone);
        String current_date_time = date_format.format(date);
        return current_date_time;
    }
}
