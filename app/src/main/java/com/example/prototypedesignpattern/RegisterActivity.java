package com.example.prototypedesignpattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RegisterActivity extends AppCompatActivity {

    @BindViews({ R.id.welcome_register_linearlayout_root, R.id.first_register_linearlayout_root, R.id.second_register_linearlayout_root})
    View[] arrayRoot;
    @BindViews({ R.id.indicator1, R.id.indicator2, R.id.indicator3})View[] arrayIndicator;
    @BindView(R.id.first_register_edittext_name) EditText edittext_name;
    @BindView(R.id.first_register_edittext_family) EditText edittext_family;
    @BindView(R.id.first_register_edittext_phone) EditText edittext_phone;
    @BindView(R.id.first_register_edittext_address) EditText edittext_address;
    @BindView(R.id.second_register_edittext_age) EditText edittext_age;
    @BindView(R.id.second_register_edittext_weight) EditText edittext_weight;
    @BindView(R.id.second_register_edittext_height) EditText edittext_height;

    String name,family,phone,address,age,weight,height;
    Register register = new Register();
    byte counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        ButterKnife.bind(RegisterActivity.this);
//        register.addMember();
//        System.out.println(register.getRegisterList());
        gestureDetector(arrayRoot);

    }

    private void gestureDetector(final View...views){
        for (View view : views) {
            view.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this) {
                public void onSwipeTop() {
//                    Toast.makeText(RegisterActivity.this, "top", Toast.LENGTH_SHORT).show();
                }
                public void onSwipeRight() {
//                    Toast.makeText(RegisterActivity.this, "right", Toast.LENGTH_SHORT).show();
                    goBack(views);
                }
                public void onSwipeLeft() {
//                    Toast.makeText(RegisterActivity.this, "left", Toast.LENGTH_SHORT).show();
                    goNext(views);
                }
                public void onSwipeBottom() {
//                    Toast.makeText(RegisterActivity.this, "bottom", Toast.LENGTH_SHORT).show();
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

    private void goNext(View...views){
        if(counter<2) {
            final int finalC = counter;
            views[finalC].setVisibility(View.GONE);
            final int finalN = ++counter;
            views[finalN].setVisibility(View.VISIBLE);
            showStep(arrayIndicator[counter]);
        }else if (counter==arrayIndicator.length-1){
            name=edittext_name.getText().toString();
            family=edittext_family.getText().toString();
            phone=edittext_phone.getText().toString();
            address=edittext_address.getText().toString();
            age=edittext_age.getText().toString();
            weight=edittext_weight.getText().toString();
            height=edittext_height.getText().toString();
            register.addMember(getKey(),name,family,phone,address,age,weight,height);
            Log.e("Hani", "goNext: "+register.getRegisterList());
        }
    }

    private void goBack(View...views){
        if(counter>0) {
            final int finalC = counter;
            views[finalC].setVisibility(View.GONE);
            final int finalP = --counter;
            views[finalP].setVisibility(View.VISIBLE);
            showStep(arrayIndicator[counter]);
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
//        String current_date_time = date_format.format(date);

        timeZone = TimeZone.getTimeZone("Asia/Tehran");
        date_format.setTimeZone(timeZone);
        String current_date_time = date_format.format(date);
//        String iranTimeZone=("Asia/Tehran-" + current_date_time);
        return current_date_time;

    }
}
