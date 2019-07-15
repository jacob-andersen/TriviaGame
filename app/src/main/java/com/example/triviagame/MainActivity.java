package com.example.triviagame;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TRIVIA";

    SingleFragment singleFragment;
    MultipleFragment multipleFragment;

    TextView category;
    TextView difficulty;
    TextView question;

    int score =0, number = 0, amount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleFragment = (SingleFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_single);
        getQuestions(amount);
    }

    public void getQuestions(int count) {
        String baseUrl = "https://opentdb.com/api.php";
        String query = "?amount=" + count;
        String url = baseUrl + query + "&encode=base64";

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

                            // decode question and populate properties in Java Question object
                            Question triviaquestion = initQuestion(questions.get(number));

                            // place  decoded questions in view
                            displayQuestion(triviaquestion);

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

    public String decode(String question) {
        byte[] decodedBytes = Base64.getDecoder().decode(question);
        String decodedString = new String(decodedBytes);
        return decodedString;

    }

    // made for Single Question Fragment, adapt to Multiple Choice Fragment by adding Incorrect Answers
    public void displayQuestion(Question questions) {
        category = (TextView) findViewById(R.id.tv_category);
        difficulty = (TextView) findViewById(R.id.tv_difficulty);
        question = (TextView) findViewById(R.id.tv_question);

        category.setText(questions.getCategory());
        difficulty.setText(questions.getDifficulty());
        question.setText(questions.getQuestion());
    }

    public Question initQuestion(Question q) {
        q.setCategory(decode(q.getCategory()));
        q.setDifficulty(decode(q.getDifficulty()));
        q.setQuestion(decode(q.getQuestion()));
        q.setCorrect_answer(decode(q.getCorrect_answer()));
        q.setType(decode(q.getType()));
        String[] wrong_answers = q.getIncorrect_answers();
        for (int i = 0; i < wrong_answers.length; i++) {
            wrong_answers[i] = decode(wrong_answers[i]);
        }
        q.setIncorrect_answers(wrong_answers);
        return q;
    }
}

