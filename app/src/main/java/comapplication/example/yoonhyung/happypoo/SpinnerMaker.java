package comapplication.example.yoonhyung.happypoo;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Yoonhyung on 4/2/16.
 */
public class SpinnerMaker  {

    public void makeImageSpinner(Spinner spinner, Integer[] imageDB, Context context){

        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(context, imageDB);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
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
        spinner.setOnItemSelectedListener(listener);
    }


    public void makeTextSpinner(Spinner spinner, Context context) {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
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
        spinner.setOnItemSelectedListener(listener);
    }
}
