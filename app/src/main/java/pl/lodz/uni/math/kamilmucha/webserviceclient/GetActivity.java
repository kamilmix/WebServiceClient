package pl.lodz.uni.math.kamilmucha.webserviceclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetActivity extends AppCompatActivity {


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
        editText = findViewById(R.id.editText2);
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
                    URL restApiEndpoint = new URL( MainActivity.API_URL + editText.getText().toString());
                    HttpsURLConnection myConnection = (HttpsURLConnection) restApiEndpoint.openConnection();
                    if(myConnection.getResponseCode() == 200){
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        fillTextViews(responseBodyReader);
                        myConnection.disconnect();
                    }

                } catch (Exception e) {
                    Toast.makeText(GetActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
    }

    private void fillTextViews(InputStreamReader responseBodyReader) throws IOException {
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
