package seg2105.tournamentmaker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import seg2105.tournamentmaker.domain_class.Team;
import seg2105.tournamentmaker.domain_class.Tournament;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        //display tournament name
        TextView name = (TextView) findViewById(R.id.tournamentName_rank);
        name.setText(Tournament.getInstance().getTournamentName() + " Tournament");

        Tournament t = Tournament.getInstance();
        List<Team> teamList = new ArrayList<Team>(t.getTeamList());
        TableLayout tl = (TableLayout) findViewById(R.id.rank_table);
        Team.sortByRank(teamList);
        
        //create header
        TableRow trh = new TableRow(this);
        trh.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        TextView rank = new TextView(this);
        rank.setText("Rank");
        rank.setGravity(Gravity.CENTER);
        trh.addView(rank);
        TextView teamName = new TextView(this);
        teamName.setText("Team");
        teamName.setGravity(Gravity.CENTER);
        trh.addView(teamName);
        TextView wins = new TextView(this);
        wins.setText("W");
        wins.setGravity(Gravity.CENTER);
        trh.addView(wins);
        TextView losses = new TextView(this);
        losses.setText("L");
        losses.setGravity(Gravity.CENTER);
        trh.addView(losses);
        TextView points = new TextView(this);
        points.setText("P");
        points.setGravity(Gravity.CENTER);
        trh.addView(points);
        TextView ga = new TextView(this);
        ga.setText("GA");
        ga.setGravity(Gravity.CENTER);
        trh.addView(ga);
        TextView gf = new TextView(this);
        gf.setText("GF");
        gf.setGravity(Gravity.CENTER);
        trh.addView(gf);
        TextView gd = new TextView(this);
        gd.setText("GD");
        gd.setGravity(Gravity.CENTER);
        trh.addView(gd);

        trh.setBackgroundColor(Color.GRAY);
        tl.addView(trh);

        for(int i = 0; i <teamList.size(); i++){
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            rank = new TextView(this);
            rank.setText("" + (i+1));
            rank.setGravity(Gravity.CENTER);
            tr.addView(rank);
            teamName = new TextView(this);
            teamName.setText("" + teamList.get(i).getTeamName().substring(0,3).toUpperCase());
            teamName.setGravity(Gravity.CENTER);
            tr.addView(teamName);
            wins = new TextView(this);
            wins.setText(""+teamList.get(i).getWins());
            wins.setGravity(Gravity.CENTER);
            tr.addView(wins);
            losses = new TextView(this);
            losses.setText(""+teamList.get(i).getLosses());
            losses.setGravity(Gravity.CENTER);
            tr.addView(losses);
            points = new TextView(this);
            points.setText(""+teamList.get(i).getPoints());
            points.setGravity(Gravity.CENTER);
            tr.addView(points);
            ga = new TextView(this);
            ga.setText("" + teamList.get(i).getGoalsAgainst());
            ga.setGravity(Gravity.CENTER);
            tr.addView(ga);
            gf = new TextView(this);
            gf.setText(""+teamList.get(i).getGoals());
            gf.setGravity(Gravity.CENTER);
            tr.addView(gf);
            gd = new TextView(this);
            gd.setText(""+teamList.get(i).getGoalDiff());
            gd.setGravity(Gravity.CENTER);
            tr.addView(gd);

            tl.addView(tr);


        }
    }




    public void OnResultClick(View view) {
        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnScheduleClick(View view) {
        Intent intent = new Intent(getApplicationContext(),TournamentDisplayActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnInfoClick(View view) {
        Intent intent = new Intent(getApplicationContext(),TournamentInfoActivity.class);
        startActivityForResult(intent, 0);
    }
}
