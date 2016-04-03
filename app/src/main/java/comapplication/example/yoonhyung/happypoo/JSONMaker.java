package comapplication.example.yoonhyung.happypoo;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;

/**
 * Created by Yoonhyung on 4/3/16.
 */
public class JSONMaker {

    public JSONObject makeJSON(String date, String time, String duration, int color, int texture, int amount, Context context) {
        JSONObject session = createSession(date, time, duration, color, texture, amount);
        JSONObject mainjo = null;

        try {
//            InputStream is = assets.open("poolog.json");
            FileInputStream is = context.openFileInput("poolog.json");
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);
            mainjo = new JSONObject(text);
            } catch (Exception e) {
                //somethinglater
                Log.d("myTag", "exception in makeJSON");
            //looks like we can never find the file.

            }

            JSONObject json = addSessionToMainJSON(session, mainjo);

        return json;
    }

    public JSONObject createSession(String date, String time, String duration, int color, int texture, int amount) {

        StringBuilder sb = new StringBuilder();
        sb.append(date); //"Mon Apr 02 1993" given by date
        sb.append(time); //"11:12" given by time
        sb.append(":00"); //default 00 seconds

        String dateTimeJSON = sb.toString();

        String[] durSplit = duration.split(":");
        int durationJSON = Integer.valueOf(durSplit[0])*3600 + Integer.valueOf(durSplit[1])*60 + Integer.valueOf(durSplit[2]);

        JSONObject session = new JSONObject();
        try {
            session.put("dateTime", dateTimeJSON);
            session.put("duration", durationJSON);
            session.put("color", color);
            session.put("texture", texture);
            session.put("amount", amount);
        } catch (JSONException e) {
            //dosomethinglater;
            Log.d("myTag", "exception in createSession");
        }
        return session;
    }

    public JSONObject addSessionToMainJSON(JSONObject session, JSONObject mainjo) {

        if (mainjo == null) {
            //create one
            mainjo = new JSONObject();

            //need to create json array too
            JSONArray ja = new JSONArray();

            try {
                //put in empty ja for now
                mainjo.put("sessions", ja);
            } catch (JSONException e) {
                //dosomethinglater;
                Log.d("myTag", "exception in addSessionToMainJSON 1");
            }
        }

        //if mainjo exists (which means array exists),
        JSONArray ja = mainjo.optJSONArray("sessions");
        ja.put(session);

        try {
            mainjo.put("sessions", ja);
        } catch (JSONException e) {
            //somethinglater
            Log.d("myTag", "exception in addSessionToMainJSON 2");
        }

        return mainjo;
    }
}
