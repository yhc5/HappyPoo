package comapplication.example.yoonhyung.happypoo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MakeSessionActivity extends Activity implements OnClickListener {

    private EditText dateET;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    private EditText timeET;
    private EditText durationET;
    private TimePickerDialog timePickerDialog;
    private Calendar newCalendar;
    private Spinner spinnerColor;
    private Button submitButton;
    private Spinner spinnerTexture;
    private Spinner spinnerAmount;

    private String dateTime;
    private int duration;
    private int color;
    private int texture;
    private int amount;

    String prepopDateTime;
    String prepopDuration;


    //private SessionDataRecorder dataRecorder = new SessionDataRecorder();
    private SpinnerMaker spinnerMaker = new SpinnerMaker();

    private static Integer[] textureImageDB = {R.drawable.texture_hardlumps,
            R.drawable.texture_smoothsausage, R.drawable.texture_watery, R.drawable.texture_sausagelumpy,
            R.drawable.texture_softblobs, R.drawable.texture_sausagecracked, R.drawable.texture_fluffy, R.drawable.texture_sticky};

    //edit these names later
    private static String[] textureNameDB = {"hardlumps", "smooth", "watery", "lumpy", "softblobs", "cracked", "sticky"};

    private static Integer[] colorImageDB = {R.drawable.brown_poop, R.drawable.green_poop, R.drawable.yellow_poop,
            R.drawable.black_poop, R.drawable.white_poop, R.drawable.red_poop};

    //dont need color names. just use position.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_session);

        // get recorded datetime and duration
        Bundle bundle;
        try {
            bundle = getIntent().getExtras();
            prepopDateTime = bundle.getString("pooDateTime");
            prepopDuration = bundle.getString("pooDuration");
        } catch (Exception e) {
            //nothing for now!
        }

        // Record current time
        newCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("EEE MMM dd yyyy", Locale.US);

        //findViewsById stuff
        findViewsByIds();

        //user input fields
        setDateField();
        setTimeField();
        setColorField();
        setTextureField();
        setAmountField();
    }


    private void findViewsByIds() {


        LinearLayout mainSessionLayout = (LinearLayout) findViewById(R.id.main_session_layout);

        if (prepopDateTime == null) {
            dateET = (EditText) findViewById(R.id.et_date);
            dateET.setInputType(InputType.TYPE_NULL);
            mainSessionLayout.removeView(findViewById(R.id.et_prepop_date));

        } else {
            mainSessionLayout.removeView(findViewById(R.id.et_date));


        }

        dateET = (EditText) findViewById(R.id.et_date);
        dateET.setInputType(InputType.TYPE_NULL);

        dateET.requestFocus();

        timeET = (EditText) findViewById(R.id.et_time);
        timeET.setInputType(InputType.TYPE_NULL);




        durationET = (EditText) findViewById(R.id.et_duration);
        durationET.setInputType(InputType.TYPE_CLASS_TEXT);

        spinnerColor = (Spinner) findViewById(R.id.spinner_color);
        spinnerTexture = (Spinner) findViewById(R.id.spinner_texture);
        spinnerAmount = (Spinner) findViewById(R.id.spinner_amount);

        submitButton = (Button) findViewById(R.id.make_session_submit);
        submitButton.setOnClickListener(this);
    }


    private void setDateField() {
        if (prepopDateTime == null) {
            dateET.setOnClickListener(this);
            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    dateET.setText(dateFormatter.format(newDate.getTime()));
                }
            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        } else {
            dateET.setHint(prepopDateTime);


        }
    }


    private void setTimeField() {
        if (prepopDuration == null) {
            timeET.setOnClickListener(this);
            timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    timeET.setText(hourOfDay + ":" + minute);
                }
            }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), false);
        } else {
            dateET.setHint(prepopDuration);
        }
    }

    private void setColorField() {
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(this, colorImageDB);
        spinnerColor.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                color = 0; //default
            }
        };
        spinnerColor.setOnItemSelectedListener(listener);
    }


    private void setTextureField() {
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(this, textureImageDB);
        spinnerTexture.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                texture = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                texture = 0; //default
            }
        };
        spinnerTexture.setOnItemSelectedListener(listener);
    }


    private void setAmountField() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                amount = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                amount = 0; //default
            }
        };
        spinnerAmount.setOnItemSelectedListener(listener);
    }




    @Override
    public void onClick(View view) {
        if (view == dateET) {
            datePickerDialog.show();
        } else if (view == timeET) {
            timePickerDialog.show();
        } else if (view == submitButton) {

            JSONMaker jsonMaker = new JSONMaker();
            JSONObject json;

            if (prepopDuration == null || prepopDateTime == null) {
                json = jsonMaker.makeJSON(dateET.getText().toString(), timeET.getText().toString(), durationET.getText().toString(),
                        color, texture, amount, this);
                Log.d("myTag", "making prepop json");
            } else {
                json = jsonMaker.makeJSON(prepopDateTime, prepopDuration, durationET.getText().toString(),
                        color, texture, amount, this);
                Log.d("myTag", "making regular json");
            }


            String filename = "poolog.json";
            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(json.toString().getBytes());
                outputStream.close();
                Log.d("myTag", "done writing file");



            } catch (Exception e) {
                Log.d("myTag", "exception in make session activity");
            }

            //and return to main, for now.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);



//            //testing purpsoes:
//            try {
//                FileInputStream is = openFileInput(filename);
//
//                // Read the entire asset into a local byte buffer.
//                byte[] buffer = new byte[is.available()];
//                is.read(buffer);
//                is.close();
//                String text = new String(buffer);
//
//                try {
//                    JSONObject js = new JSONObject(text);
//                    JSONArray jsonArray = js.optJSONArray("sessions");
//
//                    for(int i = 0; i<jsonArray.length(); i++){
//                        JSONObject session = jsonArray.getJSONObject(i);
//                        Log.d("tag", session.optString("dateTime").toString());
//                        Log.d("tag", session.optString("duration").toString());
//                        Log.d("tag", session.optString("color").toString());
//                        Log.d("tag", session.optString("amount").toString());
//                        Log.d("tag", session.optString("duration").toString());
//
//                    }
//                } catch (org.json.JSONException e){
//                    Log.d("tag", "jsonexception here");
//                }
//
//
//            } catch (Exception e) {
//                Log.d("myTag", "file not found");
//            }


        }
    }
}