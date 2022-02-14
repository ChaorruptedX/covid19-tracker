package my.edu.utem.ftmk.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;


public class AssessmentActivity extends AppCompatActivity {
    Button btn;
    RadioButton y1,y2,y3,y4,y5,n1,n2,n3,n4,n5;
    FirebaseFirestore database;
    DocumentReference reference;
    int i = 0;
    String user_id;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        y1 = findViewById(R.id.yes1);
        y2 = findViewById(R.id.yes2);
        y3 = findViewById(R.id.yes3);
        y4 = findViewById(R.id.yes4);
        y5 = findViewById(R.id.yes5);
        n1 = findViewById(R.id.no1);
        n2 = findViewById(R.id.no2);
        n3 = findViewById(R.id.no3);
        n4 = findViewById(R.id.no4);
        n5 = findViewById(R.id.no5);
        btn = findViewById(R.id.button_submit);
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        user_id = fAuth.getCurrentUser().getUid();
        reference = database.collection("assessment").document(user_id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> answer = new HashMap<>();
                String yes1 = y1.getText().toString();
                String yes2 = y2.getText().toString();
                String yes3 = y3.getText().toString();
                String yes4 = y4.getText().toString();
                String yes5 = y5.getText().toString();
                String no1 = n1.getText().toString();
                String no2 = n2.getText().toString();
                String no3 = n3.getText().toString();
                String no4 = n4.getText().toString();
                String no5 = n5.getText().toString();
                String covid_status;
                answer.put("CovidStatus", "Low Risk");
                if (y1.isChecked()) {
                    answer.put("Q1", "Yes");
                    answer.put("CovidStatus", "High Risk");
                }
                else if(n1.isChecked()) {
                    answer.put("Q1", "No");
                }

                if (y2.isChecked()) {
                    answer.put("Q2", "Yes");
                    answer.put("CovidStatus", "High Risk");
                }
                else if(n2.isChecked()) {
                    answer.put("Q2", "No");
                }

                if (y3.isChecked()) {
                    answer.put("Q3", "Yes");
                    answer.put("CovidStatus", "High Risk");
                }
                else if(n3.isChecked()) {
                    answer.put("Q3", "No");
                }

                if (y4.isChecked()) {
                    answer.put("Q4", "Yes");
                    answer.put("CovidStatus", "High Risk");
                }
                else if(n4.isChecked()) {
                    answer.put("Q4", "No");
                }

                if (y5.isChecked()) {
                    answer.put("Q5", "Yes");
                    answer.put("CovidStatus", "High Risk");
                }
                else if(n5.isChecked()) {
                    answer.put("Q5", "No");
                }

                reference.set(answer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AssessmentActivity.this, "Assessment submitted", Toast.LENGTH_SHORT).show();

                    }
                });
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });


    }
}