package seg2105.tournamentmaker.domain_class;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tarim (admin) on 2015-12-02.
 */
public class Game {
    //association variables
    Team teamA;
    Team teamB;
    Group group;
    //other instance variables
    Date startTime;
    Date endTime;
    int teamAScore, teamBScore;
    boolean isPlayed;


    //methods
    public Game(Group gr, Date startTime){
        group = gr;
        this.startTime = startTime;
        this.endTime = new Date(startTime.getTime()+(Tournament.getInstance().getGameLength()*60000));
        teamAScore = teamBScore = 0;
        isPlayed = false;
    }

    public void addTeams(List<Team> teams){
        teamA = teams.get(0);
        teamB = teams.get(1);
    }

    public void addTeams(Team a, Team b){
        teamA = a;
        teamB = b;
    }

    //getters
    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public boolean gamePlayed(){
        return isPlayed;
    }
    public List<Team> getTeams(){
        List<Team> result = new ArrayList<Team>();
        result.add(teamA);
        result.add(teamB);
        return result;
    }

    //setters
    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public void setDate(Date start){
        startTime = start;
        endTime = new Date(startTime.getTime()+(Tournament.getInstance().getGameLength()*60000));
    }

    //other methods
    // Method that return the winning team. Returns null if draw.
    public Team getWinner(){
        if(teamAScore > teamBScore) {
            return teamA;
        }
        if (teamAScore < teamBScore){
            return teamB;
        }
        return null;
    }

    // Method that return the losing team. Returns null if draw.
    public Team getLoser(){
        if(teamAScore > teamBScore) {
            return teamB;
        }
        if (teamAScore < teamBScore){
            return teamA;
        }
        return null;
    }

    //adds given amount of minutes to game
    public void addMinutes(int minutes){
        endTime = new Date(endTime.getTime()+(minutes*60000));
    }

    public void playGame(){
        isPlayed = true;
        Team winner = getWinner();
        Team loser = getLoser();


        winner.incrementWins();
        winner.updateGoals((teamAScore >= teamBScore ? teamAScore : teamBScore));
        winner.updateGoalsAgainst((teamAScore < teamBScore ? teamAScore : teamBScore));
        winner.updateGoalDiff();
        winner.updatePoints();

        loser.incrementLosses();
        loser.updateGoals((teamAScore < teamBScore ? teamAScore : teamBScore));
        loser.updateGoalsAgainst((teamAScore >= teamBScore ? teamAScore : teamBScore));
        loser.updateGoalDiff();
        loser.updatePoints();
        if(group.getGameList().size()==1)
            loser.setEliminated(true);

        try {
            Round r = group.getRound();
            if (r.allGamesPlayed()) {
                Log.e("Game.playNext", "ALL PLAYED, currentRound: "+Round.getCurrentRound()+" true");
                Log.e("Game.playNext", "" + "NUM "+ Tournament.getInstance().getTotalRounds());
                int indexOfR = Tournament.getInstance().getRoundList().indexOf(r);
                Log.e("Game.playNext", "indexOfR " + indexOfR);
                Log.e("Game.playNext", "getRoundList.get(" + (indexOfR + 1) + ")  " + Tournament.getInstance().getRoundList().get(indexOfR + 1));
                r.startNextRound((Knockout) Tournament.getInstance().getRoundList().get(indexOfR+1));
            }
        }catch(Exception e){
            Log.e("Game.playNext EXC", "" + e.getClass().getName().toString());
            e.printStackTrace();

            Round.incrementCurrentRound();
            return;
        }

    }







}
