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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

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


        // COLOR
        setColorField();


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

    private void setColorField() {
        spinnerColor = (Spinner) findViewById(R.id.spinner_color);
//        spinnerColor.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        AdapterView.OnItemSelectedListener colorListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //for testing:
                Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing? ...or set default value.
            }
        };

        spinnerColor.setOnItemSelectedListener(colorListener);

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


    public void submitMakeSession() {

        spinnerColor = (Spinner) findViewById(R.id.spinner_color);
        submitButton = (Button) findViewById(R.id.make_session_submit);

        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
//                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();


            }

        });
    }


}