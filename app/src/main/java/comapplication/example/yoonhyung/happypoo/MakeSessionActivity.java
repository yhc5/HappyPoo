package comapplication.example.yoonhyung.happypoo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MakeSessionActivity extends Activity implements OnClickListener {

    private EditText dateET;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    private EditText timeET;
    private TimePickerDialog timePickerDialog;
    private Calendar newCalendar;
    private Spinner spinnerColor;
    private Button submitButton;
    private Spinner spinnerTexture;
    private Spinner spinnerAmount;

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

        // Record current time
        newCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        //findViewsById stuff
        findViewsByIds();

        //user input fields
        setDateField();
        setTimeField();
        spinnerMaker.makeImageSpinner(spinnerColor, colorImageDB, this);
        spinnerMaker.makeImageSpinner(spinnerTexture, textureImageDB, this);
        spinnerMaker.makeTextSpinner(spinnerAmount, this);
    }


    private void findViewsByIds() {
        dateET = (EditText) findViewById(R.id.et_date);
        dateET.setInputType(InputType.TYPE_NULL);
        dateET.requestFocus();

        timeET = (EditText) findViewById(R.id.et_time);
        timeET.setInputType(InputType.TYPE_NULL);

        spinnerColor = (Spinner) findViewById(R.id.spinner_color);
        spinnerTexture = (Spinner) findViewById(R.id.spinner_texture);
        spinnerAmount = (Spinner) findViewById(R.id.spinner_amount);

        submitButton = (Button) findViewById(R.id.make_session_submit);
        submitButton.setOnClickListener(this);
    }


    private void setDateField() {
        dateET.setOnClickListener(this);
        datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateET.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    private void setTimeField() {
        timeET.setOnClickListener(this);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeET.setText(hourOfDay + ":" + minute);
            }
        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), false);
    }


    @Override
    public void onClick(View view) {
        if (view == dateET) {
            datePickerDialog.show();
        } else if (view == timeET) {
            timePickerDialog.show();
        } else if (view == submitButton) {
            Log.d("myTag", "JSON dateTime: ");
            Log.d("myTag", "JSON duration: ");
            Log.d("myTag", "JSON color: ");
            Log.d("myTag", "JSON texture: ");
            Log.d("myTag", "JSON amount: ");
        }
    }
}