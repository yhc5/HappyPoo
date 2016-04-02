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
import android.widget.DatePicker;
import android.widget.EditText;
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

    static final int DATE_DIALOG_ID = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("myTag", "make session starting");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_session);


        newCalendar = Calendar.getInstance();

        // DATE
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        //findViewsById stuff
        dateET = (EditText) findViewById(R.id.et_date);
        dateET.setInputType(InputType.TYPE_NULL);
        dateET.requestFocus();

        setDateField();


        // TIME
        timeET = (EditText) findViewById(R.id.et_time);
        timeET.setInputType(InputType.TYPE_NULL);

        setTimeField();


    }

    private void setDateField() {
        dateET.setOnClickListener(this);

        //Calendar newCalendar = Calendar.getInstance();
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
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                timeET.setText(hourOfDay + ":" + minute);
            }
        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), false);
    }


    @Override
    public void onClick(View view) {
        Log.d("myTag", "ONCLICKKKKKK-------------------");
        if (view == dateET) {
            datePickerDialog.show();
        } else if (view == timeET) {
            timePickerDialog.show();
        }
    }
    
}
