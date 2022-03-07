package com.lalithsharma.volleypractise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    TextView gen, nam,cit,emai,ag,latitu,phoneNum;
    ImageView img;

    String jsonUrl="https://randomuser.me/api/";
    String data ="";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gen = findViewById(R.id.gender);
        nam = findViewById(R.id.name);
        cit= findViewById(R.id.city);
        img = findViewById(R.id.imageView);
        emai = findViewById(R.id.email);
        ag = findViewById(R.id.age);
        latitu = findViewById(R.id.latitude);
        phoneNum = findViewById(R.id.phoneNumber);
       // Toast.makeText(this, "Trying...", Toast.LENGTH_SHORT).show();

        requestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                  /* for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                     //   String name = jsonObject.getString("name");
                        String gender = jsonObject.getString("gender");
                        String s = jsonObject.getString("title");

                      //  name.append(s.toString());

                     //  gen.append(String.valueOf(gender));
                      //   gen.append(s.toString());
                      //  gen.setText(gender.toString());
                      //    gen.append(jsonObject.getString("gender"));

                        gen.append(gender.toString());
                    }*/



                try {


                    JSONArray jsonArray = response.getJSONArray("results");


                    JSONObject mainResultArray  = jsonArray.getJSONObject(0);
                    String gender = mainResultArray.getString("gender");
                    gen.append("Gender: "+gender);

                    // trying to add name
                    JSONObject name = jsonArray.getJSONObject(0);
                    JSONObject userName = name.getJSONObject("name");

                    // this is also correct
                    // JSONObject userName = mainResultArray.getJSONObject("name");
                    String title = userName.getString("title");
                    String firstName = userName.getString("first");
                    String lastName = userName.getString("last");

                    nam.append("Name: "+title+" "+firstName+" "+lastName);


                    // trying to add city
                    JSONObject userLocation = mainResultArray.getJSONObject("location");
                    String city = userLocation.getString("city");

                    cit.append("City "+city);

                    // trying to get email
                    String email = mainResultArray.getString("email");
                    emai.append("Email: "+email);

                    // trying to get dob
                    JSONObject dob = mainResultArray.getJSONObject("dob");
                    String age = dob.getString("age");
                    ag.append("Age: "+age);

                    // trying to get image url
                    JSONObject pic = mainResultArray.getJSONObject("picture");
                    String large = pic.getString("large");
                    Picasso.get().load(large).into(img);

                    // trying to get latitude
                    JSONObject coOrdinate = userLocation.getJSONObject("coordinates");
                    String latitude = coOrdinate.getString("latitude");
                    latitu.append("Latitude: "+latitude);

                    // trying to add phone number
                    String phone = mainResultArray.getString("phone");
                    phoneNum.append("Phone Number: "+phone);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "There is a error with volley library", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(objectRequest);

    }



}