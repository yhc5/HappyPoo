package comapplication.example.yoonhyung.happypoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by catzhangy1 on 4/2/16.
 */
public class GraphsActivity extends AppCompatActivity{

    private ArrayList<HashMap<String, String>> masterSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUpdatedSessions();
        setContentView(R.layout.activity_graphs);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMakeSession(1);
            }
        });
    }



    void getUpdatedSessions(){
        masterSessions = new ArrayList<HashMap<String,String>>();
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
                    HashMap<String, String> sessionMap = new HashMap<String, String>();
                    sessionMap.put("dateTime", session.optString("dateTime").toString());
                    sessionMap.put("duration", session.optString("duration").toString());
                    sessionMap.put("color", session.optString("color").toString());
                    sessionMap.put("amount", session.optString("amount").toString());
                    masterSessions.add(sessionMap);
                }
            } catch (org.json.JSONException e){
                /** TODO: Exception catching **/
            }
        } catch (IOException ex){
            /** TODO: Exception catching **/
        }
    }

    public void openMakeSession(int i){
        HashMap<String, String> map = masterSessions.get(i);
        Intent intent = new Intent(this, SessionDetailActivity.class);
        intent.putExtra("dateTime", map.get("dateTime"));
        intent.putExtra("duration", map.get("duration"));
        intent.putExtra("color", map.get("color"));
        intent.putExtra("amount", map.get("amount"));
        startActivity(intent);
    }

}
