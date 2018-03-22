package steel.marnix.eindwerkandroidstreats.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import steel.marnix.eindwerkandroidstreats.R;


public class StartScreenActivity extends AppCompatActivity {

        private static final long DELAY = 3000;
        private boolean scheduled = false;
        private Timer startTimer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start_screen);

           // ImageView iv = findViewById(R.id.iv_startscreen);
           // Picasso.get().load(R.drawable.streat).into(iv);


            startTimer = new Timer();
            startTimer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    steel.marnix.eindwerkandroidstreats.activities.StartScreenActivity.this.finish();
                    startActivity(new Intent(steel.marnix.eindwerkandroidstreats.activities.StartScreenActivity.this, MainActivity.class));
                }
            }, DELAY);
            scheduled = true;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            if(scheduled)
                startTimer.cancel();
            startTimer.purge();
        }
    }



