package my.edu.utem.ftmk.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.data.DataBufferObserverSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        etEmail=(EditText) findViewById(R.id.editEmail);
        etPassword=(EditText) findViewById(R.id.editPassword);


    }



    public void welcome(View view)
    {
        startActivity(new Intent(LoginActivity.this, my.edu.utem.ftmk.covid_19_tracker.WelcomeActivity.class));
    }

    public void login(View view)
    {
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();

        if (email.isEmpty())
        {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email!");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }

        if(password.length() < 6 )
        {
            etPassword.setError("Min password length should be 6 characters!");
            etPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            isUser();

                        } else
                        {
                            Toast.makeText(LoginActivity.this, "Failed to login. Try again !", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void isUser() {

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        DataSnapshot dataSnapshot = null;

        String email=etEmail.getText().toString().trim();

        String nameDB=dataSnapshot.child(email).child("name").getValue(String.class);
        String emailDB=dataSnapshot.child(email).child("email").getValue(String.class);
        String phoneNoDB=dataSnapshot.child(email).child("phoneNo").getValue(String.class);

        Intent intent= new Intent(getApplicationContext(), my.edu.utem.ftmk.covid_19tracker.ProfileActivity.class);

        intent.putExtra("name", nameDB);
        intent.putExtra("email", emailDB);
        intent.putExtra("phoneNo", phoneNoDB);

        startActivity(intent);


        
    }
}
