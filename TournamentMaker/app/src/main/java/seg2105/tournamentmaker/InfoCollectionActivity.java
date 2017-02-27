package seg2105.tournamentmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import seg2105.tournamentmaker.domain_class.Tournament;


public class InfoCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_collection);

        //drop-down for Tournament Type
        Spinner tournamentTypeSpinner = (Spinner) findViewById(R.id.tournament_type_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.tournament_type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        tournamentTypeSpinner.setAdapter(adapter1);

//drop-down for Team Num
        Spinner teamNumSpinner = (Spinner) findViewById(R.id.team_num_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.team_num, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        teamNumSpinner.setAdapter(adapter2);

//drop-down for Game Length
        Spinner gameLengthSpinner = (Spinner) findViewById(R.id.game_length_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.game_length, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        gameLengthSpinner.setAdapter(adapter3);
    }

    public void OnSubmitClick(View view){
        //get data from spinners
        String tournamentName = ((EditText)findViewById(R.id.tournamentNameField)).getText().toString();
        Spinner tournamentTypeSpinner =(Spinner) findViewById(R.id.tournament_type_spinner);
        String tournamentFormat = tournamentTypeSpinner.getSelectedItem().toString();
        Spinner teamNumSpinner =(Spinner) findViewById(R.id.team_num_spinner);
        String teamNum = teamNumSpinner.getSelectedItem().toString();
        Spinner gameLengthSpinner =(Spinner) findViewById(R.id.game_length_spinner);
        String gameLength = gameLengthSpinner.getSelectedItem().toString();

        Tournament tournament = Tournament.getInstance();
        tournament.setData(tournamentName, Integer.parseInt(teamNum), Integer.parseInt(gameLength.substring(0,2)), tournamentFormat);
        Intent intent = new Intent(getApplicationContext(),TeamAddingActivity.class);
        startActivityForResult(intent, 0);
    }
}
