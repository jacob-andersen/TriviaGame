package com.example.triviagame;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.triviagame.model.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TRIVIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleyRequest(5);
    }

    public void volleyRequest(int count) {
        String baseUrl = "https://opentdb.com/api.php";
        String query = "?amount=" + count;
        String url = baseUrl + query;

        // 2: Create RequestQueue Object instance and init it with Volley.newRequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // 3 : Declare JsonArrayRequest or JsonObjectRequest
        //     Then init it with new JsonArrayRequest or JsonOBjectRequest
        JsonObjectRequest request = new JsonObjectRequest(
                url, // Param 1: The url string
                new JSONObject(),
                new Response.Listener<JSONObject>() { // Param 2: Success Listener
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d(TAG, "onResponse: response code is " + response.getInt("response_code"));

                            // Get the array of results from the response
                            JSONArray jsonArray = response.getJSONArray("results");

                            // This creates the type of data we are expecting back from the json
                            Type listType = new TypeToken<ArrayList<Question>>() {
                            }.getType();
                            // Gson converts the json to the type we specified above
                            List<Question> questions = new Gson().fromJson(jsonArray.toString(), listType);
                            questions.get(0).getDifficulty();
                            Log.d(TAG, "onResponse: " + questions.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Step 4 Pass the request object from Step 3 into Requestqueue object from step 2
        requestQueue.add(request);


    }


//                                    List<String> urls = new ArrayList<>();
//                            for(int i=0;i<response.length();i++) {
//                                urls.add(response.get(i).toString());
//                                Log.d(TAG, (i+1) +" "+response.get(i).toString());
//                            }
//                            System.out.println(urls);
////                            loadRecyclerView(urls);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() { // Param 3: Error Listener
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG,"onErrorResponse: "+ error.getLocalizedMessage());
//                    }
//                }
//        );
//
//        // 4: Pass the request object from Step 3 into the requestQueue object from Step 2
//        requestQueue.add(request);
//
//    }

    private String decodeBase64(String coded) {
        byte[] valueDecoded = new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return new String(valueDecoded);
    }
}
