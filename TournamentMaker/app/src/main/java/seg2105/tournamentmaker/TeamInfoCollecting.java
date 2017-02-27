package seg2105.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import seg2105.tournamentmaker.domain_class.Team;
import seg2105.tournamentmaker.domain_class.Tournament;

public class TeamInfoCollecting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info_collecting);
    }

    public void OnCreateTeamClick(View view){
        //getting the data
        EditText teamNameField = (EditText) findViewById(R.id.teamNameField);
        String teamName = teamNameField.getText().toString();
        EditText teamCityField = (EditText) findViewById(R.id.teamCityField);
        String teamCity = teamCityField.getText().toString();
        EditText numTeamMemberField = (EditText) findViewById(R.id.numTeamMemberField);
        String numTeamMember = teamCityField.getText().toString();

        if(teamName.length() < 3){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Team name must at least 3 characters!");
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
        //add a new team
        Tournament.getInstance().getTeamList().add(new Team(teamName, teamCity, numTeamMember));

        //return to adding team screen
        Intent intent = new Intent(getApplicationContext(),TeamAddingActivity.class);
        startActivityForResult(intent, 0);
    }
}
