package com.example.b206.quizmdp6561;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        queue = Volley.newRequestQueue(this);
    }

    public void loginClick(View v)
    {
        if(edUsername.getText().toString() != "" || edPassword.getText().toString() != "") {
            String url = "http://simpleservicesoa2018.000webhostapp.com/login.php";
            StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject tamp = new JSONObject(response);
                        String kode = tamp.getString("code");
                        String message = tamp.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        if(kode.equals("1"))
                        {
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            intent.putExtra("Nama",edUsername.getText().toString());
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", edUsername.getText().toString());
                    params.put("password", edPassword.getText().toString());
                    return params;
                }
            };
            queue.add(req);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Tidak boleh Kosong", Toast.LENGTH_LONG).show();
        }
    }
}
