package seg2105.tournamentmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import seg2105.tournamentmaker.domain_class.Tournament;

public class MainActivity extends AppCompatActivity {
    Tournament tournament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewTournament(View view) {
        Intent intent = new Intent(getApplicationContext(),InfoCollectionActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnFormatInfoClick(View view){
        Intent intent = new Intent(getApplicationContext(),FormatInformationActivity.class);
        startActivityForResult(intent, 0);
    }
}
