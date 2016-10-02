package aschantz.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import info.hoang8f.widget.FButton;





public class MainActivity extends AppCompatActivity {

    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_SIZE = "KEY_SIZE";
    private EditText etName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);

        //5x5 game activity
        FButton btnEasy = (FButton) findViewById(R.id.btnEasy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(5);
            }
        });

        //learn to play activity
        FButton btnLearn = (FButton) findViewById(R.id.btnLearn);
        btnLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(0);
            }
        });


    }

    private void startGame(int mode) {
        if (mode == 5) {
            Intent intentStartGame = new Intent();
            intentStartGame.setClass(this, MinesweeperActivity.class);

            intentStartGame.putExtra(KEY_NAME, etName.getText().toString());
            intentStartGame.putExtra(KEY_SIZE, mode);

            startActivity(intentStartGame);

        } else if(mode == 0) {
            Intent intentStartGame = new Intent();
            intentStartGame.setClass(this, LearnActivity.class);
            intentStartGame.putExtra(KEY_NAME, etName.getText().toString());
            intentStartGame.putExtra(KEY_SIZE, mode);
            startActivity(intentStartGame);
        }
    }
}
