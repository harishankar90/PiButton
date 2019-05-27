package com.hvdcreations.pibutton;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ConstraintLayout vlayout;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_info);
        vlayout = findViewById(R.id.layout);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("switch/data/status");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        value = ds.getValue(String.class);
                        dothis(value);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void dothis(String value) {
        String p = "Button Pressed";
        if (value.equals(p)){
            textView.setText(value);
            textView.setTextColor(Color.parseColor("#ffffff"));
            vlayout.setBackgroundColor(Color.parseColor("#519E8A"));

        }else{
            textView.setText(value);
            textView.setTextColor(Color.parseColor("#ffffff"));
            vlayout.setBackgroundColor(Color.parseColor("#FD151B"));
        }
    }

}
