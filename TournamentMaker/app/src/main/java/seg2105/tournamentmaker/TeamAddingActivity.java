package seg2105.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;

import seg2105.tournamentmaker.domain_class.Round;
import seg2105.tournamentmaker.domain_class.Team;
import seg2105.tournamentmaker.domain_class.Tournament;

public class TeamAddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_adding);
        listTeams();
    }

    private void listTeams(){
        //get linear layout and list of teams
        LinearLayout teamListLayout = (LinearLayout) findViewById(R.id.teamListLayout);
        List<Team> teams = Tournament.getInstance().getTeamList();
        //For each team add to the linear layout with formatting
        for(Team t : teams){
            //create TextView for teamName and teamCity and format them
            TextView teamName = new TextView(getApplicationContext());
            teamName.setText("Team " + t.getTeamName());
            teamName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25); //font size
            teamName.setTextColor(Color.BLACK); //text colour
            TextView teamCity = new TextView(getApplicationContext());
            teamCity.setText(t.getTeamCity());
            teamCity.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); //text size
            teamCity.setTextColor(Color.BLACK); //text colour
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0,0,0,50); //text margins for team city
            teamCity.setLayoutParams(llp);
            teamListLayout.addView(teamName, -1); //add to bottom of layout
            teamListLayout.addView(teamCity, -1); //add to borrom of layout
        }
    }

    public void OnAddTeamClick(View view){
        if(Tournament.getInstance().getCurrentNumTeam()==Tournament.getInstance().getTotalTeams()){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("You already have enough teams!");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(),TeamInfoCollecting.class);
        startActivityForResult(intent, 0);
    }

    public void OnStartTournamentClick(View view) {
        Tournament tournament = Tournament.getInstance();
        if(tournament.isPlayable()){ //if playable start
            tournament.startTournament();
            Intent intent = new Intent(getApplicationContext(),TournamentDisplayActivity.class);
            startActivityForResult(intent, 0);

        } else { //else tell user
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Not enough teams!");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

}
