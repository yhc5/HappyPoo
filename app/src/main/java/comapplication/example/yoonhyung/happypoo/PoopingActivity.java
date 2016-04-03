package comapplication.example.yoonhyung.happypoo;

import android.os.Bundle;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        //final MediaPlayer hotline = MediaPlayer.create(this, R.raw.hotlinebling);


        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch(position){
                    case 0: stopPlaying();
                            if(!zero_playing) {
                                mp = MediaPlayer.create(PoopingActivity.this, R.raw.hotlinebling);
                                mp.start();
                                zero_playing = true;
                            }
                            break;
                    case 1: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.cafe1930);
                            mp.start();
                            break;
                    case 3: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.lavalsedamelie);
                            mp.start();
                            break;
                    case 4: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.meditation);
                            mp.start();
                            break;
                    case 5: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.yanntiersen);
                            mp.start();
                            break;
                    case 6: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.pushit);
                            mp.start();
                            break;
                    case 7: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.letitgo);
                            mp.start();
                            break;
                    case 8: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.dropitlikeitshot);
                            mp.start();
                            break;
                    default: stopPlaying();
                            mp = MediaPlayer.create(PoopingActivity.this, R.raw.eyeofthetiger);
                            mp.start();
                            break;
                }

            }
        });
    }

    private void stopPlaying() {
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
}
