package pl.lodz.uni.math.kamilmucha.webserviceclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DeleteActivity extends AppCompatActivity {

    private EditText editTextNumberToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        editTextNumberToDelete = findViewById(R.id.editTextNumbetToDelete);
        Button buttonDeleteData = findViewById(R.id.buttonDeleteData);
        buttonDeleteData.setOnClickListener(buttonDeleteDataOnClickListener);
    }

    private View.OnClickListener buttonDeleteDataOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonDeleteDataClicked();
        }
    };

    private void buttonDeleteDataClicked() {
        new DeleteTask().execute();
    }

    private class DeleteTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                URL restApiEndpoint = new URL(MainActivity.API_URL + editTextNumberToDelete.getText().toString());
                HttpsURLConnection myConnection = (HttpsURLConnection) restApiEndpoint.openConnection();
                myConnection.setRequestMethod("DELETE");
                return myConnection.getResponseCode();

            } catch (Exception e) {
                Toast.makeText(DeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer responseCode) {
            if (responseCode == 200) {
                onDeleteSuccessful();
            } else {
                onDeleteFail();
            }

        }
    }

    private void onDeleteSuccessful() {
        Toast.makeText(DeleteActivity.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private void onDeleteFail() {
        Toast.makeText(DeleteActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
    }
}
