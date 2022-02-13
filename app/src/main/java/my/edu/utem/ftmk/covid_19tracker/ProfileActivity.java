package my.edu.utem.ftmk.covid_19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView vName, vEmail, vPhone;
    //SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        vName=(TextView) findViewById(R.id.tvName);
        vEmail=(TextView) findViewById(R.id.tvEmail);
        vPhone=(TextView) findViewById(R.id.tvPhoneNo);

        showAllUserData();


    }

    private void showAllUserData()
    {
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("name");
        String user_email=intent.getStringExtra("email");
        String user_phoneNo=intent.getStringExtra("phoneNo");

        vName.setText(user_name);
        vEmail.setText(user_email);
        vPhone.setText(user_phoneNo);


    }
}