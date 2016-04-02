package comapplication.example.yoonhyung.happypoo;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by catzhangy1 on 4/2/16.
 */
public class SessionDetailActivity extends AppCompatActivity {
    private String dateTime;
    private String duration;
    private String color;
    private String texture;
    private String amount;

//    private Integer[] mThumbIds = {
//            R.drawable.song_poop1, R.drawable.song_poop2,
//            R.drawable.song_poop3, R.drawable.song_poop4,
//            R.drawable.song_poop5, R.drawable.song_poop6,
//            R.drawable.song_poop7, R.drawable.song_poop8,
//            R.drawable.song_poop9
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        dateTime = myIntent.getStringExtra("dateTime");
        duration = myIntent.getStringExtra("duration");
        color = myIntent.getStringExtra("color");
        texture = myIntent.getStringExtra("texture");
        amount = myIntent.getStringExtra("amount");

        setContentView(R.layout.activity_session_detail);

        ImageView image = (ImageView) findViewById(R.id.imageView);
//        image.setImageResource(mThumbIds[Integer.parseInt(color)]);
    }

}