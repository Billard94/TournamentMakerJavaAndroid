package seg2105.tournamentmaker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import seg2105.tournamentmaker.domain_class.Game;
import seg2105.tournamentmaker.domain_class.Round;
import seg2105.tournamentmaker.domain_class.Team;
import seg2105.tournamentmaker.domain_class.Tournament;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //display tournament name
        TextView name = (TextView) findViewById(R.id.tournamentName_result);
        name.setText(Tournament.getInstance().getTournamentName() + " Tournament");

        List<Round> roundList = Tournament.getInstance().getRoundList();
        for(Round r : roundList) {
            List<Game> gameList = r.getAllGamesInRound();
            for (Game gm : gameList) {
                if (gm.gamePlayed()) {
                    displayGameResult(gm);
                }
            }
        }
    }

    private void displayGameResult(Game gm){
        LinearLayout ll = (LinearLayout) findViewById(R.id.game_result_layout);

        TableLayout tl = new TableLayout(this);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        List<Team> teams = gm.getTeams();

        TextView teamA = new TextView(this);
        teamA.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); //font size
        teamA.setText(teams.get(0).getTeamName().substring(0, 3).toUpperCase()+"\n"+gm.getTeamAScore());
        teamA.setGravity(Gravity.CENTER);

        Log.e("TEAM A", "" + teams.get(0).getTeamName());

        TextView teamB = new TextView(this);
        teamB.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); //font size
        teamB.setText(" " + teams.get(1).getTeamName().substring(0, 3).toUpperCase()+"\n"+gm.getTeamBScore());
        teamB.setGravity(Gravity.CENTER);

        Log.e("TEAM B", "" + teams.get(1).getTeamName());


        TextView time = new TextView(this);
        time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); //font size
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy  HH:mm");
        time.setText(dateFormat.format(gm.getStartTime())+" to\n"+dateFormat.format(gm.getEndTime()));
        time.setGravity(Gravity.CENTER);

        Log.e("GAME DATE", "" + dateFormat.format(gm.getStartTime()));

        tr.addView(teamA);
        tr.addView(time);
        tr.addView(teamB);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(10, 0, 0, 10); //text margins for team city
        tl.setLayoutParams(llp);
        tl.setBackgroundColor(Color.LTGRAY);
        tl.addView(tr);

        tl.setStretchAllColumns(true);
        ll.addView(tl);
    }

    public void OnScheduleClick(View view) {
        Intent intent = new Intent(getApplicationContext(),TournamentDisplayActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnRankClick(View view) {
        Intent intent = new Intent(getApplicationContext(),RankActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnInfoClick(View view) {
        Intent intent = new Intent(getApplicationContext(),TournamentInfoActivity.class);
        startActivityForResult(intent, 0);
    }

}
