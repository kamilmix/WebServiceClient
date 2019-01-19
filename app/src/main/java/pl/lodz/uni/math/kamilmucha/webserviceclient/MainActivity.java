package pl.lodz.uni.math.kamilmucha.webserviceclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGET = findViewById(R.id.buttonGET);
        buttonGET.setOnClickListener(buttonGetOnClickListener);

    }

    private View.OnClickListener buttonGetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonGETClicked();
        }
    };

    private void buttonGETClicked() {
        Intent intent = new Intent(this, GETActivity.class);
        startActivity(intent);
    }
}
