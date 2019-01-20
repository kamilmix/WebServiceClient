package pl.lodz.uni.math.kamilmucha.webserviceclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public final static String API_URL="https://jsonplaceholder.typicode.com/posts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGET = findViewById(R.id.buttonGET);
        buttonGET.setOnClickListener(buttonGetOnClickListener);

        Button buttonDeleteActivity = findViewById(R.id.buttonDeleteActivity);
        buttonDeleteActivity.setOnClickListener(buttonDeleteActivityOnClickListener);

    }

    private View.OnClickListener buttonGetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonGETClicked();
        }
    };

    private void buttonGETClicked() {
        Intent intent = new Intent(this, GetActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener buttonDeleteActivityOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonDeleteActivityClicked();
        }
    };

    private void buttonDeleteActivityClicked() {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

}
