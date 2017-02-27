package seg2105.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import seg2105.tournamentmaker.domain_class.Game;
import seg2105.tournamentmaker.domain_class.Round;
import seg2105.tournamentmaker.domain_class.Tournament;

public class PlayNextGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_next_game);

        Round currentRound = Tournament.getInstance().getRoundList().get(Round.getCurrentRound()-1);
        List<Game> games = currentRound.getAllGamesInRound();
        Game nextGame = null;
        for(int i = 0; i<games.size(); i++){
            if(!games.get(i).gamePlayed()){
                nextGame = games.get(i);
                break;
            }
        }
        if(nextGame!=null) {
            TextView gameDate = (TextView) findViewById(R.id.game_date);
            gameDate.setText("Date: " + nextGame.getStartTime());
            TextView teamALabel = (TextView) findViewById(R.id.teamA_label);
            teamALabel.setText("Enter " + nextGame.getTeams().get(0).getTeamName() + " score");
            TextView teamBLabel = (TextView) findViewById(R.id.teamB_label);
            teamBLabel.setText("Enter " + nextGame.getTeams().get(1).getTeamName() + " score");
        }

    }

    public void OnPlayNextGameClick(View view){
        Round currentRound = Tournament.getInstance().getRoundList().get(Round.getCurrentRound()-1);
        List<Game> games = currentRound.getAllGamesInRound();
        Game nextGame = null;
        for(int i = 0; i<games.size(); i++){
            if(!games.get(i).gamePlayed()){
                nextGame = games.get(i);
                break;
            }
        }
        if(nextGame != null) {
            String teamAScore = ((TextView) findViewById(R.id.teamA_score)).getText().toString();
            String teamBScore = ((TextView) findViewById(R.id.teamB_score)).getText().toString();
            nextGame.setTeamAScore(Integer.parseInt(teamAScore));
            nextGame.setTeamBScore(Integer.parseInt(teamBScore));
            if(teamAScore.equals(teamBScore)){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("There was a tie! 10 mins were added to play time.\nEnter scores again.");
                nextGame.addMinutes(10);
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
            nextGame.playGame();
        }
        Log.e("playNext", "currentRound " + Round.getCurrentRound());
        Log.e("playNext", "totalRounds " + Tournament.getInstance().getTotalRounds());
        if (Round.getCurrentRound() > Tournament.getInstance().getTotalRounds()) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Tournament has finished. The winner was " + Tournament.getInstance().getHighestRankingTeam().getTeamName() + "!");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(),RankActivity.class);
                            startActivityForResult(intent, 0);
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return;
        }else {
            Intent intent = new Intent(getApplicationContext(), TournamentDisplayActivity.class);
            startActivityForResult(intent, 0);
        }
    }


}
