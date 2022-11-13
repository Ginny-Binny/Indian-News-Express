package com.example.indiannewsexpress;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.example.myloadingbutton.MyLoadingButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SIGNUP extends Fragment {
    MyLoadingButton loadingButton;
    CardView buttonbg;
    EditText sname,semail,spassword;
    ImageView eye;
    Integer i;
    TextView signinback,fp;





    public SIGNUP() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_s_i_g_n_u_p, container, false);
        i=0;
        sname=view.findViewById(R.id.sname);
        spassword=view.findViewById(R.id.spassword);
        semail=view.findViewById(R.id.semail);
        eye=view.findViewById(R.id.eye);


        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spassword.getText().toString().isEmpty())
                {

                    Snackbar.with(getContext(),null)
                            .type(Type.ERROR)
                            .message("Password is empty !!")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();


                }
                else
                {
                    if (i==0)
                    {
                        spassword.setTransformationMethod(null);
                        eye.setBackgroundResource(R.drawable.closedeye);
                        i=1;
                    }
                    else
                    {
                        spassword.setTransformationMethod(new PasswordTransformationMethod());
                        eye.setBackgroundResource(R.drawable.eye);
                        i=0;
                    }
                }

            }
        });


        fp=view.findViewById(R.id.fp);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fp.setTextColor(ContextCompat.getColor(getContext(), R.color.purple_500));
                if (semail.getText().toString().isEmpty())
                {

                    Snackbar.with(getContext(),null)
                            .type(Type.ERROR)
                            .message("Plese enter only your registered email in the email field!!")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();

                }
                else
                {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(semail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Snackbar.with(getContext(),null)
                                    .type(Type.SUCCESS)
                                    .message("Password Reset email sent successfully")
                                    .duration(Duration.LONG)
                                    .fillParent(true)
                                    .textAlign(Align.LEFT)
                                    .show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.with(getContext(),null)
                                    .type(Type.ERROR)
                                    .message(e.getMessage())
                                    .duration(Duration.LONG)
                                    .fillParent(true)
                                    .textAlign(Align.LEFT)
                                    .show();

                        }
                    });


                }

            }
        });




        signinback=view.findViewById(R.id.signinback);
        signinback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinback.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_700));
                getActivity().getSupportFragmentManager().beginTransaction()

                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.maincontainer,new LOGIN()).commit();
            }
        });



        loadingButton = view.findViewById(R.id.signup_btn);
        buttonbg=view.findViewById(R.id.btn_bg);
        loadingButton.setMyButtonClickListener(new MyLoadingButton.MyLoadingButtonClick() {
            @Override
            public void onMyLoadingButtonClick() {
                loadingButton.showLoadingButton();
                Snackbar.with(getContext(),null)
                        .type(Type.UPDATE)
                        .message("LOADING..")
                        .duration(Duration.LONG)
                        .fillParent(true)
                        .textAlign(Align.LEFT)
                        .show();
                loadingButton.showLoadingButton();
                buttonbg.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.maincolour));
                buttonbg.setCardElevation(0);
                if (sname.getText().toString().isEmpty())
                {

                    Snackbar.with(getContext(),null)
                            .type(Type.ERROR)
                            .message("Please enter name !!")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();
                    loadingButton.showNormalButton();

                }
                else if (semail.getText().toString().isEmpty())
                {
                    Snackbar.with(getContext(),null)
                            .type(Type.ERROR)
                            .message("please enter email")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();
                    loadingButton.showNormalButton();

                }
                else if (spassword.getText().toString().isEmpty())
                {
                    Snackbar.with(getContext(),null)
                            .type(Type.ERROR)
                            .message("please enter password!")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();
                    loadingButton.showNormalButton();

                }
                else {

                    loadingButton.showLoadingButton();
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(semail.getText().toString(),spassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            String name=sname.getText().toString();
                            String email=semail.getText().toString();

                            signupdata signupdata= new signupdata(email,name);
                            FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).setValue(signupdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    loadingButton.showDoneButton();
                                    replace();
                                    Snackbar.with(getContext(),null)
                                            .type(Type.SUCCESS)
                                            .message("you are registered successfully...")
                                            .duration(Duration.LONG)
                                            .fillParent(true)
                                            .textAlign(Align.LEFT)
                                            .show();
                                    FirebaseAuth.getInstance().signInWithEmailAndPassword(semail.getText().toString(),spassword.getText().toString());

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    loadingButton.showErrorButton();
                                    Snackbar.with(getContext(),null)
                                            .type(Type.ERROR)
                                            .message(e.getMessage())
                                            .duration(Duration.LONG)
                                            .fillParent(true)
                                            .textAlign(Align.LEFT)
                                            .show();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.with(getContext(),null)
                                    .type(Type.ERROR)
                                    .message(e.getMessage())
                                    .duration(Duration.LONG)
                                    .fillParent(true)
                                    .textAlign(Align.LEFT)
                                    .show();
                            loadingButton.showErrorButton();
                        }
                    });

                }
                loadingButton.showNormalButton();

                buttonbg.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.maincolour));
                buttonbg.setCardElevation(5);
                loadingButton.setClickable(true);

            }

            private void replace() {
                getActivity().getSupportFragmentManager().beginTransaction()

                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.maincontainer,new HOME()).commit();



            }

        });


        loadingButton.setAnimationDuration(300)
                .setButtonColor(R.color.teal_200) // Set MyLoadingButton custom background color.
                .setButtonLabel("REGISTER NOW") // Set MyLoadingButton button label text.
                .setButtonLabelSize(20) // Set button label size in integer.
                .setProgressLoaderColor(Color.WHITE) // Set Color att for loader color.
                .setButtonLabelColor(R.color.black) // Set button label/text color.
                .setProgressDoneIcon(getResources().getDrawable(R.drawable.successful)) // Set MyLoadingButton done icon drawable.
                .setProgressErrorIcon(getResources().getDrawable(R.drawable.unsuccessful)) //Set MyLoadingButton error icon drawable.
                .setNormalAfterError(true); // Button animate to normal state if its in error state ,by default true.
        return view;
    }
}