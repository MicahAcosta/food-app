package com.citizenfour.yummy;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//import android.support.v7.app.AppCompatActivity;
import com.citizenfour.yummy.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {


    @BindView(R.id.edtName)
    TextView edtName;

    @BindView(R.id.edtPhone)
    TextView edtPhone;

    @BindView(R.id.edtPassword)
    TextView edtPassword;

    // Write to the database
    final FirebaseDatabase database = FirebaseDatabase.getInstance();  //reference to the database
    final DatabaseReference table_user = database.getReference("User");  //reference to the User table

//    public void signIn(View view) {
    @OnClick(R.id.btnSignUp)
    public void signUp(View view) {
        //display progressDialog
        final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setMessage("Please Wait ...");
        progressDialog.show();


        // Read from the database
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //check whether the phone number exist in the database
                if (dataSnapshot.child(edtPhone.getText().toString()).exists()){

                    //get User info
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this, "The Phone Number is already registred", Toast.LENGTH_SHORT).show();


                } else {
                    progressDialog.dismiss();
                    User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                    table_user.child(edtPhone.getText().toString()).setValue(user);
                    Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                    finish();
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
        setContentView(R.layout.activity_sign_up);
    }
}
