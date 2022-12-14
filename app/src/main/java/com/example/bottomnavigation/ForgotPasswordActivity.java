package com.example.bottomnavigation;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    //TO-DO formulaire d'entrée de username, récupération vers firebase
    //TO-DO montrer erreur si le username n'existe pas
    //TO-DO: Afficher un message : "nous allons envoyer un mail à votre messagerie ..."
    //TO-DO: Dans LoginActivity, changer le lien de redirection "signupRedirectText.setOnClickListener(...)" à cette page
    //To-DO: Envoi de mail

    EditText loginUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
}