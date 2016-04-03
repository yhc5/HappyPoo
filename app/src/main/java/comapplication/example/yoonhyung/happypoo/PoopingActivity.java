package comapplication.example.yoonhyung.happypoo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;


public class PoopingActivity extends Activity {

    MediaPlayer mp;
    boolean zero_playing = false;
    boolean one_playing = false;
    boolean two_playing = false;
    boolean three_playing = false;
    boolean four_playing = false;
    boolean five_playing = false;
    boolean six_playing = false;
    boolean seven_playing = false;
    boolean eight_playing = false;
    boolean default_playing = false;

    String pooDateTime;
    String pooDuration;
    Timer pooTimer;

    long startTime = 0L;
    long endTime = 0L;
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    //long timeSwapBuff = 0L;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);

        //set date and time after starting.
        Calendar newDate = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
        pooDateTime = dateFormatter.format(newDate.getTime());
        startTime = SystemClock.uptimeMillis();



        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));


        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch(position){
                    case 0: if (zero_playing){
                                zero_playing = false;
                                stopPlaying();
                            }
                            else {
                                stopPlaying();
                                mp = MediaPlayer.create(PoopingActivity.this, R.raw.hotlinebling);
                                mp.start();
                                zero_playing = true;
                            }
                            break;
                    case 1: if (one_playing){
                                one_playing = false;
                                stopPlaying();
                            }
                            else {
                                stopPlaying();
                                mp = MediaPlayer.create(PoopingActivity.this, R.raw.cafe1930);
                                mp.start();
                                one_playing = true;
                            }
                            break;
                    case 2:
                        if (two_playing) {
                            two_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.lavalsedamelie);
                            mp.start();
                            two_playing = true;
                        }
                        break;
                    case 3:
                        if (three_playing) {
                            three_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.meditation);
                            mp.start();
                            three_playing = true;
                        }
                        break;
                    case 4:
                        if (four_playing) {
                            four_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.yanntiersen);
                            mp.start();
                            four_playing = true;
                        }
                        break;
                    case 5:
                        if (five_playing) {
                            five_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.letitgo);
                            mp.start();
                            five_playing = true;
                        }
                        break;
                    case 6:
                        if (six_playing) {
                            six_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.pushit);
                            mp.start();
                            six_playing = true;
                        }
                        break;
                    case 7:
                        if (seven_playing) {
                            seven_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.dropitlikeitshot);
                            mp.start();
                            seven_playing = true;
                        }
                        break;
                    case 8:
                        if (eight_playing) {
                            eight_playing = false;
                            stopPlaying();
                        } else {
                            stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.eyeofthetiger);
                            mp.start();
                            eight_playing = true;
                        }
                        break;
                }

            }
        });
    }

    private void stopPlaying() {
        zero_playing = false;
        one_playing = false;
        two_playing = false;
        three_playing = false;
        four_playing = false;
        five_playing = false;
        six_playing = false;
        seven_playing = false;
        eight_playing = false;
        default_playing = false;

        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pooping, menu);
        return true;
    }

    public boolean goToPooping (){
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openMakeSession(View view){
        stopPlaying();
        Log.d("myTag", "open make session clicked");
        endTime = SystemClock.uptimeMillis();

        timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
        int secs = (int) (timeInMilliseconds / 1000);
        int mins = secs / 60;
        secs = secs % 60;

        pooDuration = String.valueOf("00:")+ String.valueOf(mins) + ":" + String.valueOf(secs);
        //settings hours to 00 for now

        Intent intent = new Intent(this, MakeSessionActivity.class);
        intent.putExtra("sessionDate", pooDateTime);
        intent.putExtra("sessionDuration", pooDuration);
        startActivity(intent);
    }
}
