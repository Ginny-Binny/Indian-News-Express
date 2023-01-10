package com.example.indiannewsexpress;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.example.indiannewsexpress.models.savedata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class SAVED_NEWS extends Fragment {
    RecyclerView recycler;
    ArrayList<savedata> data;
    savednewsadapter savednewsadapter;
    //saved news page


    public SAVED_NEWS() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_s_a_v_e_d__n_e_w_s, container, false);


        recycler = view.findViewById(R.id.savednewsrecycler);


        data = new ArrayList<>();
        data.clear();
        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        savednewsadapter = new savednewsadapter(getContext(), data);
        recycler.setAdapter(savednewsadapter);
        try {
            FirebaseDatabase.getInstance().getReference().child("users")
                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                    .addValueEventListener(new ValueEventListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            data.clear();


                            for (DataSnapshot dataSnapshot : snapshot.child("saved news").getChildren()) {
                                savedata sdata = dataSnapshot.getValue(savedata.class);

                                data.add(sdata);
                            }
                            savednewsadapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "error happened", Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (Exception e) {
            // Log.e("FIREBASE AUTH ERROR", e.getMessage());
            Snackbar.with(getContext(), null)
                    .type(Type.ERROR)
                    .message("Please log in again")
                    .duration(Duration.LONG)
                    .fillParent(true)
                    .textAlign(Align.LEFT)
                    .show();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.maincontainer, new LOGIN());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }


        return view;
    }
}