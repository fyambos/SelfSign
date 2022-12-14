package com.example.bottomnavigation.data;

import androidx.annotation.NonNull;

import com.example.bottomnavigation.data.model.LoggedInUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            //connexion à la bdd et requete sur l'username
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUser = reference.orderByChild("username").equalTo(username);


            //get userId
            //...
            //get displayName
            //...
            //instanciate logged in user with LoggedInUser user = new LoggedInUser(userId,displayName);
            //return new Result.Success<>(user);

            LoggedInUser fakeUser = new LoggedInUser(java.util.UUID.randomUUID().toString(),
                                                      "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

    }

    public void logout() {
        // TODO: revoke authentication
    }
}