package my.edu.utem.ftmk.covid_19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }



    public void loginUser(View view)
    {
        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
    }

    public void registerUser(View view)
    {
        startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
    }
}
