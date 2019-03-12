package com.citizenfour.yummy;

import android.app.ProgressDialog;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.citizenfour.yummy.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignIn extends AppCompatActivity {

    @BindView(R.id.txtSlogan)
    TextView txtSlogan;

    @BindView(R.id.edtPhone)
    TextView edtPhone;

    @BindView(R.id.edtPassword)
    TextView edtPassword;

    // Write to the database
    final FirebaseDatabase database = FirebaseDatabase.getInstance();  //reference to the database
    final DatabaseReference table_user = database.getReference("User");  //reference to the User table

//    public void signIn(View view) {
    @OnClick(R.id.btnSignIn)
    public void signIn() {
        //display progressDialog
        final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
        progressDialog.setMessage("Please Wait ...");
        progressDialog.show();

        // Read from the database
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

               //check whether the user exist in the database
                if (dataSnapshot.child(edtPhone.getText().toString()).exists()){

                    //get User info
                    progressDialog.dismiss();
                    User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);

                    if (user.getPassword().equals(edtPassword.getText().toString())){
                        Toast.makeText(SignIn.this, "Sign In Succesful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn.this, "Sign In Failed: Wrong Password", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignIn.this, "Sorry, that User does Not Exist in the database", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);

        //call methods here
        signIn();




    }
}
