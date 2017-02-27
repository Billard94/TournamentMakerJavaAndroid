package seg2105.tournamentmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import seg2105.tournamentmaker.domain_class.Tournament;

public class TournamentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_info);

        //display tournament name
        TextView name = (TextView) findViewById(R.id.tournamentName_info);
        name.setText(Tournament.getInstance().getTournamentName() + " Tournament");
    }

    public void OnResultClick(View view) {
        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnRankClick(View view) {
        Intent intent = new Intent(getApplicationContext(),RankActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnScheduleClick(View view) {
        Intent intent = new Intent(getApplicationContext(),TournamentDisplayActivity.class);
        startActivityForResult(intent, 0);
    }
}
