package my.edu.utem.ftmk.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Create the object of TextView Class
    TextView idTestedPositive, idRecovered, idDeceased, idActiveCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idTestedPositive = findViewById(R.id.idTestedPositive);
        idRecovered = findViewById(R.id.idRecovered);
        idDeceased = findViewById(R.id.idDeceased);
        idActiveCases = findViewById(R.id.idActiveCases);

        fetchData();
    }

    private void fetchData() {

        // Create a String request using Volley Library

        // https://apify.com/covid-19 - APIs for COVID-19 Statistics (Refreshed every hour)

        // Malaysia COVID-19 Statistics
        String url = "https://api.apify.com/v2/key-value-stores/6t65lJVfs3d8s6aKc/records/LATEST?disableRedirect=true";

        StringRequest request
                = new StringRequest(Request.Method.GET, url,
                (Response.Listener<String>) response -> {
                    // Handle the JSON object and handle it inside try and catch

                    try {
                        // Creating object of JSONObject
                        JSONObject jsonObject = new JSONObject(response.toString());

                        idTestedPositive.setText(jsonObject.getString("testedPositive"));
                        idRecovered.setText(jsonObject.getString("recovered"));
                        idDeceased.setText(jsonObject.getString("deceased"));
                        idActiveCases.setText(jsonObject.getString("activeCases"));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void assessment(View view) {
        Intent intent = new Intent(this, AssessmentActivity.class);
        startActivity(intent);
    }

    public void do_map(View view) {
        Intent intent = new Intent(this, HotSpotActivity.class);
        startActivity(intent);

        finish();
    }
}