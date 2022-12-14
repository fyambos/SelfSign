package com.example.bottomnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button validate;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        validate = view.findViewById(R.id.validate);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Button clicked!", Toast.LENGTH_SHORT).show();
                String validate_value = "no";
                FirebaseDatabase.getInstance().getReference().child("attendance").child("-NJ0UrF7iLvK9hCiH8e6").child("attended").setValue(validate_value);
            }
        });

        return view;
    }

    public void getCourseData(String courseId) {
        // get a reference to the session node in the database
        DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference().child("session");

        // add a listener to the courseRef to get the data for the session with the
        // specified courseId
        courseRef.child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get the name
                // from the data snapshot
                String name = dataSnapshot.child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // handle error
            }
        });
    }
    public void getSessionData(String sessionId) {
        // get a reference to the session node in the database
        DatabaseReference sessionRef = FirebaseDatabase.getInstance().getReference().child("session");

        // add a listener to the sessionRef to get the data for the session with the
        // specified sessionId
        sessionRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get the class_id, course_id, duration_minutes, teacher_id, and timestamp
                // from the data snapshot
                String classId = dataSnapshot.child("class_id").getValue(String.class);
                String courseId = dataSnapshot.child("course_id").getValue(String.class);
                String durationMinutes = dataSnapshot.child("duration_minutes").getValue(String.class);
                String teacherId = dataSnapshot.child("teacher_id").getValue(String.class);
                String timestamp = dataSnapshot.child("timestamp").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // handle error
            }
        });
    }
    public String[] getCurrentSession(List<String> sessionIds) {
        // Reference the "session" node
        DatabaseReference sessionRef = FirebaseDatabase.getInstance().getReference().child("session");
        boolean[] current = {false};
        String[] currentSession = {""};

        // Loop through each element of the list
        for (String sessionId : sessionIds) {
            // Reference the child of "session" node with the given session ID
            DatabaseReference sessionChildRef = sessionRef.child(sessionId);

            // Read the values of the "timestamp" and "duration_minutes" children of the session
            sessionChildRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String timestamp = snapshot.child("timestamp").getValue(String.class);
                    String durationMinutes = snapshot.child("duration_minutes").getValue(String.class);
                    int durationInMinutes = Integer.parseInt(durationMinutes);
                    while (!current[0]) {
                        // Check the currentSchedule[0] value again
                        current[0] = checkSchedule(timestamp, durationInMinutes);
                        if(current[0]) {
                            // Save the sessionId to the currentSession array
                            currentSession[0] = sessionId;
                            break;
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                }
            });
        }
        // Return the current session ID
        return currentSession;
    }
    public boolean checkSchedule(String timestamp, int durationInMinutes) {
        // Convert the timestamp string to a long value representing the number of milliseconds since the epoch
        long timestampInMilliseconds = Long.parseLong(timestamp) * 1000;

        // Get the current time in milliseconds since the epoch
        long currentTimeInMilliseconds = System.currentTimeMillis();

        // Calculate the end time by adding the duration in minutes to the timestamp in milliseconds
        long endTimeInMilliseconds = timestampInMilliseconds + (durationInMinutes * 60 * 1000);

        // Return true if the current time is between the start and end times
        return currentTimeInMilliseconds >= timestampInMilliseconds && currentTimeInMilliseconds <= endTimeInMilliseconds;
    }
    public String[] getUserClass() {
        String[] classId = {""};

        // Reference the "user" node
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user");
        // Reference the "-NJCufi9HnkdeXTQ2-_g" child of the "user" node
        DatabaseReference userChildRef = userRef.child("-NJCufi9HnkdeXTQ2-_g");
        // Read the value of the "class_id" child of the "-NJCufi9HnkdeXTQ2-_g" child
        userChildRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String classIdValue = snapshot.child("class_id").getValue(String.class);
                // Store the value of "class_id" in the final variable
                classId[0] = classIdValue;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });

        return classId;
    }
    public void findSessions(String class_id) {
        // select session_id where class_id = class_id

        // Reference the "session" node
        DatabaseReference sessionRef = FirebaseDatabase.getInstance().getReference().child("attendance");

        // Query to retrieve all the children of "session" where the "class_id" value is class_id
        Query sessionClassIdQuery = sessionRef.orderByChild("class_id").equalTo(class_id);

        // Create a list to store the "session_id" values
        List<String> sessionsIds = new ArrayList<>();

        // Read the values of the query results
        sessionClassIdQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sessionClassIdSnapshot : dataSnapshot.getChildren()) {
                    String sessionIdValue = sessionClassIdSnapshot.child("session_id").getValue(String.class);
                    // Add the "session_id" value to the list
                    sessionsIds.add(sessionIdValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    public void getAttendance() {
        // Reference the "attendance" node
        DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference().child("attendance");

        // Reference the "-NJ0UrF7iLvK9hCiH8e6" child of the "attendance" node
        DatabaseReference attendanceChildRef = attendanceRef.child("-NJ0UrF7iLvK9hCiH8e6");

        // Read the value of the "attended" child of the "-NJ0UrF7iLvK9hCiH8e6" child
        attendanceChildRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String attendedValue = snapshot.child("attended").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}