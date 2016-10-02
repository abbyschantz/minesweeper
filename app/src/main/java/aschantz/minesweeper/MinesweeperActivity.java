package aschantz.minesweeper;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.facebook.shimmer.ShimmerFrameLayout;

import aschantz.minesweeper.Model.MinesweeperModel;
import aschantz.minesweeper.View.MinesweeperView;
import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

public class MinesweeperActivity extends AppCompatActivity {

    private LinearLayout layoutContent;
    private MinesweeperView gameView;
    private SmallBang mSmallBang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_minesweeper);

        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        gameView = (MinesweeperView) findViewById(R.id.gameView);

        mSmallBang = SmallBang.attach2Window(this);

        String name = getIntent().getStringExtra(MainActivity.KEY_NAME);

        //add shimmer animation to text
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(
                R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmerAnimation();

        TextView shimmerText = (TextView) findViewById(R.id.shimmer_text);
        shimmerText.setText(getResources().getString(R.string.titleWelcome, name));



        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.flipToggle();
                toggle.getTextOff();
            }
        });


        final ToggleButton numbersSurround = (ToggleButton) findViewById(R.id.numbersSurround);
        numbersSurround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.switchNumberDisplay();
                if (MinesweeperModel.getInstance().getNumberMode() == MinesweeperModel.INDIVIDUAL) {
                    numbersSurround.setChecked(true);
                    numbersSurround.getTextOn();
                } else {
                    numbersSurround.setChecked(false);
                    numbersSurround.getTextOff();
                }
            }
        });

        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.restartGame();
                toggle.getTextOff();
                numbersSurround.setChecked(true);
                numbersSurround.getTextOn();
                smallBangAnimation(view);
            }
        });



        final ToggleButton bombMode = (ToggleButton) findViewById(R.id.showBombs);
        bombMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.switchBombMode();
                bombMode.getTextOff();
            }
        });


    }

    public void showGameOverMessage(String message) {
        Snackbar.make(layoutContent, message, Snackbar.LENGTH_LONG).show();
    }

    public void showNotABombMessage(String message) {
        Snackbar.make(layoutContent, message, Snackbar.LENGTH_LONG).show();
    }

    public void smallBangAnimation(View view) {
        mSmallBang.bang(view, new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {

            }
        });
    }
}
