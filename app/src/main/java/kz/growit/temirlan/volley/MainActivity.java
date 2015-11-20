package kz.growit.temirlan.volley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rey.material.widget.ProgressView;
import com.rey.material.widget.SnackBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button buttonCities;
    private ProgressView progressView;
    private TextView textView;
    private Spinner spinner;

    ArrayList<String> citiesName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.citiesSpinner);
        buttonCities = (Button) findViewById(R.id.buttonCities);
        progressView = (ProgressView) findViewById(R.id.progress);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.start();
                request();
            }
        });


        buttonCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.start();
                requestCities();


            }
        });


    }



    public void requestCities(){
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "http://altynorda.kz/api/citiesapi/getcities";
//        final ArrayList<Cities> citiesArrayList = new ArrayList<>();



        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject city = null;
                            try {
                                city = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Cities citiesModel = new Cities(city);
                            citiesName.add(i, citiesModel.getName());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, citiesName);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(dataAdapter);
                        progressView.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("sdfsd", "Error: " + error.getMessage());
                progressView.stop();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);

    }


    public void request() {


        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "http://api.androidhive.info/volley/person_object.json";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject phone = response.getJSONObject("phone");

                            textView.setText(phone.getString("mobile"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressView.stop();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                progressView.stop();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


    }
}
