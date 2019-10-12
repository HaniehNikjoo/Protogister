package com.example.prototypedesignpattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindViews({ R.id.indicator1, R.id.indicator2, R.id.indicator3})
    View[] arrayIndicator;
    @BindViews({ R.id.welcome_register_linearlayout_root, R.id.first_register_linearlayout_root, R.id.second_register_linearlayout_root})
    View[] arrayRoot;
    byte counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(RegisterActivity.this);

        Register register = new Register();
//        register.addMember();
//        System.out.println(register.getRegisterList());

        gestureDetector(arrayRoot);



    }

    private void gestureDetector(final View...views){
        for (View view : views) {
            view.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this) {
                public void onSwipeTop() {
                    Toast.makeText(RegisterActivity.this, "top", Toast.LENGTH_SHORT).show();
                }
                public void onSwipeRight() {
//                    Toast.makeText(RegisterActivity.this, "right", Toast.LENGTH_SHORT).show();
                   if(counter>0) {
                       final int finalC = counter;
                       views[finalC].setVisibility(View.GONE);
                       final int finalP = --counter;
                       views[finalP].setVisibility(View.VISIBLE);
                       showStep(arrayIndicator[counter]);
                   }
                }
                public void onSwipeLeft() {
//                    Toast.makeText(RegisterActivity.this, "left", Toast.LENGTH_SHORT).show();
                    if(counter<2) {
                        final int finalC = counter;
                        views[finalC].setVisibility(View.GONE);
                        final int finalN = ++counter;
                        views[finalN].setVisibility(View.VISIBLE);
                        showStep(arrayIndicator[counter]);
                    }
                }
                public void onSwipeBottom() {
                    Toast.makeText(RegisterActivity.this, "bottom", Toast.LENGTH_SHORT).show();
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

}
