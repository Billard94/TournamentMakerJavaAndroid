package seg2105.tournamentmaker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.transform.Result;

import seg2105.tournamentmaker.domain_class.Game;
import seg2105.tournamentmaker.domain_class.Round;
import seg2105.tournamentmaker.domain_class.Team;
import seg2105.tournamentmaker.domain_class.Tournament;

public class TournamentDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_display);

        //display tournament name
        TextView name = (TextView) findViewById(R.id.tournamentName_schedule);
        name.setText(Tournament.getInstance().getTournamentName()+" Tournament");

        Tournament t = Tournament.getInstance();
        List<Round> rounds = t.getRoundList();
        Log.e("rounds size", ""+rounds.size());
        for(int i = 0; i<rounds.size(); i++){
            if(!rounds.get(i).getGroups().isEmpty()){
                Log.e("getGroups().isEmpty()", "i: "+i+", isEmpty: "+ rounds.get(i).getGroups().isEmpty());
                TableLayout tl = (TableLayout) findViewById(R.id.schedule_table);
                TableRow tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView roundNum = new TextView(this);
                roundNum.setText("Round "+(i+1));
                tr.addView(roundNum);
                tl.addView(tr);
                for(Game gm : rounds.get(i).getAllGamesInRound()){
                    Log.e("Game "+i, ""+gm.getTeams().get(0));
                    displayGame(gm);
                }
            }
        }
    }

    public void displayGame(Game gm){
        TableLayout tl = (TableLayout) findViewById(R.id.schedule_table);

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        List<Team> teams = gm.getTeams();

        TextView teamA = new TextView(this);
        teamA.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); //font size
        teamA.setText(teams.get(0).getTeamName().substring(0, 3).toUpperCase()+" ");
        teamA.setGravity(Gravity.CENTER);

        Log.e("TEAM A", "" + teams.get(0).getTeamName());

        TextView teamB = new TextView(this);
        teamB.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); //font size
        teamB.setText(" " + teams.get(1).getTeamName().substring(0, 3).toUpperCase());
        teamB.setGravity(Gravity.CENTER);

        Log.e("TEAM B", "" + teams.get(1).getTeamName());


        TextView time = new TextView(this);
        time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); //font size
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy  HH:mm");
        time.setText(dateFormat.format(gm.getStartTime()));
        time.setGravity(Gravity.CENTER);

        Log.e("GAME DATE", "" + dateFormat.format(gm.getStartTime()));

        tr.addView(teamA);
        tr.addView(time);
        tr.addView(teamB);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(10, 0, 0, 10); //text margins for team city
        tr.setLayoutParams(llp);
        tr.setBackgroundColor(Color.LTGRAY);
        tl.addView(tr);
    }



    public void OnResultClick(View view) {
        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
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

    public void OnPlayGameButtonClick(View view){
        Intent intent = new Intent(getApplicationContext(),PlayNextGameActivity.class);
        startActivityForResult(intent, 0);
    }
}
