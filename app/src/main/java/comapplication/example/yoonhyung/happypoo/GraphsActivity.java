package comapplication.example.yoonhyung.happypoo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by catzhangy1 on 4/2/16.
 */
public class GraphsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUpdatedSessions();
        setContentView(R.layout.activity_graphs);
    }

    void getUpdatedSessions(){
        try {
            InputStream is = getAssets().open("test.json");
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);

            try {
                JSONObject json = new JSONObject(text);
                JSONArray jsonArray = json.optJSONArray("sessions");

                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject session = jsonArray.getJSONObject(i);
                    String dateTime = session.optString("dateTime").toString();
                    int duration = Integer.parseInt(session.optString("duration").toString());
                    int color = Integer.parseInt(session.optString("color").toString());
                    int amount = Integer.parseInt(session.optString("amount").toString());
                }
            } catch (org.json.JSONException e){
            }
        } catch (IOException ex){

        }
    }

}
