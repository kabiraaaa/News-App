package com.example.newsapp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupTabFragment extends Fragment {
    EditText name, email, mnumber, password;
    CheckBox terms;
    RelativeLayout register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSTate){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container,false);

        name = root.findViewById(R.id.username);
        email = root.findViewById(R.id.useremail);
        mnumber = root.findViewById(R.id.usernumber);
        password = root.findViewById(R.id.userpassword);
        terms = root.findViewById(R.id.termscheckbox);
        register = root.findViewById(R.id.registerButton);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(),"Enter your Name",Toast.LENGTH_SHORT).show();
                }else {
                    if (email.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity().getApplicationContext(),"Enter Your Email Address",Toast.LENGTH_SHORT).show();
                    } else {
                        if(email.getText().toString().trim().matches(emailPattern)) {
                            if(mnumber.getText().toString().length()!=10) {
                                Toast.makeText(getActivity().getApplicationContext(),"Enter Valid Number",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(password.getText().toString().isEmpty()) {
                                    Toast.makeText(getActivity().getApplicationContext(),"Enter a Password",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    if(terms.isChecked()) {
                                        String emaill = email.getText().toString();
                                        String passwordd = password.getText().toString();
                                        auth.createUserWithEmailAndPassword(emaill, passwordd)
                                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            FirebaseUser user = auth.getCurrentUser();
                                                            Toast.makeText(getActivity().getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
                                                            Intent i = new Intent(getActivity(), MainActivity.class);
                                                            startActivity(i);
                                                            ((Activity) getActivity()).overridePendingTransition(0, 0);
                                                        } else {
                                                            Toast.makeText(getActivity().getApplicationContext(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(getActivity().getApplicationContext(),"Please Accept terms",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(),"Enter Valid Email",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return root;
    }
}
