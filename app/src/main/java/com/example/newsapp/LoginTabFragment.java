package com.example.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {

    EditText email, password;
    RelativeLayout login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
    FirebaseAuth auth;
    FloatingActionButton google, fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSTate){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container,false);

        email = root.findViewById(R.id.useremail);
        password = root.findViewById(R.id.userpassword);
        login = root.findViewById(R.id.loginButton);
        auth = FirebaseAuth.getInstance();
        google = root.findViewById(R.id.googlefab);
        fb = root.findViewById(R.id.facebookfab);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Your Email Address", Toast.LENGTH_SHORT).show();
                } else {
                    if (email.getText().toString().trim().matches(emailPattern)) {
                        if (password.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity().getApplicationContext(), "Enter a Password", Toast.LENGTH_SHORT).show();
                        } else {
                            String emaill = email.getText().toString();
                            String passwordd = password.getText().toString();
                            auth.signInWithEmailAndPassword(emaill, passwordd)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getActivity().getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getActivity(), MainActivity.class);
                                                startActivity(i);
                                                
                                                ((Activity) getActivity()).overridePendingTransition(0, 0);
                                            } else {
                                                Toast.makeText(getActivity().getApplicationContext(), "Unknown Error Occurred", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Google Sign In", Toast.LENGTH_SHORT).show();
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Facebook Sign In", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
