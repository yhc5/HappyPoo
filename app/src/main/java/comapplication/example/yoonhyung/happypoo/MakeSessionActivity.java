package comapplication.example.yoonhyung.happypoo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MakeSessionActivity extends Activity implements OnClickListener {

    private EditText dateET;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;

    static final int DATE_DIALOG_ID = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("myTag", "make session starting");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_session);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        //findViewsById stuff
        dateET = (EditText) findViewById(R.id.et_date);
        dateET.setInputType(InputType.TYPE_NULL);
        dateET.requestFocus();

        setDateTimeField();

    }


    private void setDateTimeField() {
        dateET.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateET.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu); //wtfisthis
        Log.d("myTag", "ONCREATEOPTIONS-------------------");
        return true;
    }

    @Override
    public void onClick(View view) {
        Log.d("myTag", "ONCLICKKKKKK-------------------");

        datePickerDialog.show();
//        if(view == dateET) {
//            datePickerDialog.show();
//        }
    }




}
