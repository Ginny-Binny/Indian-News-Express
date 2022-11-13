package com.example.indiannewsexpress;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SPLASH_SCREEN extends Fragment {


    Handler handler;

    public SPLASH_SCREEN() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_s_p_l_a_s_h__s_c_r_e_e_n, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null)
                {
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.maincontainer,new HOME()).commit();
                    FirebaseAuth.getInstance().signOut();
                }
                else
                {
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.maincontainer,new LOGIN()).commit();
                }

            }
        },3000);


        return view;
    }
}