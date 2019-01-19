package pl.lodz.uni.math.kamilmucha.webserviceclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GETActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textViewTitle;
    HttpsURLConnection myConnection;
    private TextView textViewBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        Button buttonGetData = findViewById(R.id.buttonGETdata);
        buttonGetData.setOnClickListener(buttonGetDataOnClickListener);
        editText = findViewById(R.id.editText);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewBody = findViewById(R.id.textViewBody);
    }


    private View.OnClickListener buttonGetDataOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonGetDataClicked();
        }
    };


    private void buttonGetDataClicked() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL restApiEndpoint = new URL("https://jsonplaceholder.typicode.com/posts/1");
                    HttpsURLConnection myConnection = (HttpsURLConnection) restApiEndpoint.openConnection();
                    if(myConnection.getResponseCode() == 200){
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        wypelnij(responseBodyReader);
                        myConnection.disconnect();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void wypelnij(InputStreamReader responseBodyReader) throws IOException {
        JsonReader jsonReader = new JsonReader(responseBodyReader);
        jsonReader.beginObject();

        while(jsonReader.hasNext()){
            String key = jsonReader.nextName();

           // Log.d("ABC", value);
            if(key.equals("title")){
                String value = "TITLE: " + jsonReader.nextString();
                textViewTitle.setText(value);
            }
            else if(key.equals("body")){
                String value = jsonReader.nextString();
                textViewBody.setText(value);
            }
            else {
                jsonReader.skipValue();
            }
        }
        jsonReader.close();
    }
}
