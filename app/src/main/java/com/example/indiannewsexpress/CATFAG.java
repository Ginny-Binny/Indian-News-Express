package com.example.indiannewsexpress;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CATFAG extends Fragment {
    //blank fragment to inherit category page


    public CATFAG() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_c_a_t_f_a_g, container, false);


      getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer,new CATEGORIES()).commit();


        return view;
    }
}