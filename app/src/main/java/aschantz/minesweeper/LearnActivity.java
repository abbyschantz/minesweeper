package aschantz.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        TextView learnWelcome = (TextView) findViewById(R.id.learnWelcome);

        String name = getIntent().getStringExtra(MainActivity.KEY_NAME);
        int size = getIntent().getIntExtra(MainActivity.KEY_SIZE, 0);

        learnWelcome.setText(getResources().getString(R.string.learnTitle, name));

        new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 90, 80)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90)
                .emit(findViewById(R.id.emiter_top_right), 8);

        new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.000017f, 90)
                .emit(findViewById(R.id.emiter_top_left), 8);


    }
}
