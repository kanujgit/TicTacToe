package sathya.com.tictactoe;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private  boolean disableSound=false;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tic-tac-toe");
        setTitleColor(R.color.colorWhite);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            disableSound=bundle.getBoolean("disableSound");
        }

//        View continueButton  = findViewById(R.id.continue_button);
  //      continueButton.setOnClickListener(this);

        View newgameButton = findViewById(R.id.new_game_button);
        newgameButton.setOnClickListener(this);

        View newgameButtonAndroid=findViewById(R.id.new_game_button_android);
        newgameButtonAndroid.setOnClickListener(this);

        View helpButton = findViewById(R.id.help_button);
        helpButton.setOnClickListener(this);

        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);

        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tic_tac_toe, menu);
        MenuItem toggleSoundItemMenu = menu.findItem(R.id.toogle_sound_menu);
        if(disableSound)
            toggleSoundItemMenu.setTitle(R.string.toggle_sound_off_label);
        else
            toggleSoundItemMenu.setTitle(R.string.toggle_sound_on_label);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.action_settings_menu:
                return true;
            case R.id.toogle_sound_menu:
                if(disableSound){
                    disableSound = false;
                    AlertDialog alert = new AlertDialog.Builder(this).create();
                    alert.setTitle("Sound Toggle Notification");
                    alert.setMessage("Sound is now Enabled");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    alert.show();
                    item.setTitle(R.string.toggle_sound_on_label);
                }
                else{
                    disableSound = true;
                    AlertDialog alert = new AlertDialog.Builder(this).create();
                    alert.setTitle("Sound Toggle Notification");
                    alert.setMessage("Sound is now Disabled");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    alert.show();
                    item.setTitle(R.string.toggle_sound_off_label);
                }
                return true;
            case R.id.about_menu:
                finish();
                Intent aboutActivity = new Intent(this,About.class);
                startActivity(aboutActivity);
                return true;
            case R.id.help_menu:
                finish();
                Intent helpActivity = new Intent(this,Help.class);
                startActivity(helpActivity);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }






    @Override
    public void onClick(View v)
    {
        if(mp!=null){
            mp.release();
        }
        if(!disableSound){
            mp=MediaPlayer.create(this,R.raw.keypress_standard);
            mp.start();
        }


        switch (v.getId())
        {
    //        case R.id.continue_button:
      //          System.out.print("Continue button is pressed");
        //        break;
            case R.id.new_game_button:
                Intent newGameActivity=new Intent(this,GamePlay.class);
                newGameActivity.putExtra("disableSound",disableSound);
                finish();
                startActivity(newGameActivity);
                break;

            case R.id.new_game_button_android:
                Intent newGameAndroid=new Intent(this,AndroidGamePlay.class);
                newGameAndroid.putExtra("disableSound",disableSound);
                finish();
                startActivity(newGameAndroid);
                finish();
                break;
            case R.id.help_button:
                finish();
                Intent helpActivity = new Intent(this,Help.class);
                startActivity(helpActivity);
                break;
            case R.id.about_button:
                finish();
                Intent aboutActivity = new Intent(this,About.class);
                startActivity(aboutActivity);
                break;

            case R.id.exit_button:

                android.support.v7.app.AlertDialog.Builder ad = new android.support.v7.app.AlertDialog.Builder(this);
                ad.setTitle("Exit");
                ad.setMessage("Do You really want to Exit");
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        System.exit(0);
                        finish();
                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                ad.create().show();
        }
    }
}
