package com.example.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.data.LoginDataSource;
import com.example.bottomnavigation.data.LoginRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    TextView forgotPasswordRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        forgotPasswordRedirectText = findViewById(R.id.forgotPasswordRedirectText);

        //fonction qui se lance au click du loginButton
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifie les champs de nom d'utilisateur et de mot de passe
                if (!validateUsername() | !validatePassword()) {

                    //si les champs ne sont pas valides, rien ne se passe
                } else {
                    //sinon, la fonction "checkUser" est appelée pour vérifier les informations de l'utilisateur.
                    checkUser();
                }
            }
        });

        forgotPasswordRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("You must enter a username");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("You must enter a password");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){

        //recuperer les logins du forumulaire
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        //connexion à la bdd et requete sur l'username
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //regarde si l'utilisateur avec cet username existe
                if (snapshot.exists()){

                    loginUsername.setError(null);

                    //recupere le mdp utilisateur
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    // si l'utilisateur existe, verifie le mdp
                    if (!Objects.equals(passwordFromDB, userPassword)) {
                        loginUsername.setError(null);
                        //si l'utilistateur exite, recupere les données de l'utilisateur:
                        String userID = snapshot.child(userUsername).getValue(String.class);
                        String displayName = snapshot.child(userUsername).child("name").getValue(String.class);

                        // authentificate the user


                        //et redirige vers le mainactivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        //erreur si le mdp n'est pas bon
                        loginPassword.setError("Invalid password");
                        loginPassword.requestFocus();
                    }
                //erreur si l'utilisateur n'existe pas
                } else {
                    loginUsername.setError("The user does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}