package my.edu.utem.ftmk.covid_19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etName, etEmail, etPassword, etPhoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        etName=(EditText) findViewById(R.id.editName);
        etEmail=(EditText) findViewById(R.id.editEmail);
        etPassword=(EditText) findViewById(R.id.editPassword);
        etPhoneNo=(EditText) findViewById(R.id.editPhoneNumber);



    }

    public void welcome(View view)
    {
        startActivity(new Intent(RegistrationActivity.this,WelcomeActivity.class));
    }

    public void register(View view)
    {
        String name=etName.getText().toString().trim();
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        String phoneNo=etPhoneNo.getText().toString().trim();

        if (name.isEmpty())
        {
            etName.setError("Name is required!");
            etName.requestFocus();
            return;
        }

        if (email.isEmpty())
        {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }

        if (phoneNo.isEmpty())
        {
            etPhoneNo.setError("Phone Number is required!");
            etPhoneNo.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email!");
            etEmail.requestFocus();
            return;
        }

        if(password.length() < 6 )
        {
            etPassword.setError("Min password length should be 6 characters!");
            etPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user=new User(name, email, password, phoneNo);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegistrationActivity.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();


                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "Failed to register! Try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegistrationActivity.this, "Failed to register!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}