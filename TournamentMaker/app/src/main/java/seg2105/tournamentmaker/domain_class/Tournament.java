package seg2105.tournamentmaker.domain_class;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seg2105.tournamentmaker.PlayNextGameActivity;
import seg2105.tournamentmaker.TournamentDisplayActivity;

public class Tournament{
    //singleton instance
    private static Tournament tournament = new Tournament();

    //instance variables
    String tournamentName;
    int totalTeams;
    int gameLength;
    String tournamentFormat;
    Boolean canStart;

    //associations
    List<Round> rounds;
    List<Team> teams;

    //get singleton Tournament instance
    public static Tournament getInstance(){
        return tournament;
    }

    //constructor
    private Tournament(){
        canStart = false;
        rounds = new ArrayList<Round>();
        teams = new ArrayList<Team>();
    }

    //setter
    public void setData(String name, int totalTeams, int gameLength, String tournamentFormat){
        this.tournamentName = name;
        this.totalTeams = totalTeams;
        this.gameLength = gameLength;
        this.tournamentFormat = tournamentFormat;

        rounds = new ArrayList<Round>();
        teams = new ArrayList<Team>();
    }

    //getters
    public String getTournamentName(){ return  tournamentName; }
    public int getTotalTeams(){ return totalTeams; }
    public int getCurrentNumTeam(){ return teams.size(); }
    public String getFormat(){ return tournamentFormat; }
    public int getTotalRounds(){ return rounds.size(); }
    public int getGameLength(){ return gameLength; }
    public List<Team> getTeamList(){ return teams; }
    public List<Round> getRoundList(){ return rounds; }

    //other methods

    //returns true if the tournament is ready to play
    public boolean isPlayable(){
        if (getCurrentNumTeam() == totalTeams && tournamentFormat!=null && gameLength!=0){
            canStart = true;
        }
        return canStart;
    }

    //calculates the number of rounds needed to be played for given total teams and format
    private int calculateNumOfRound(){
        if (tournamentFormat.equals("Knockout")){
            return (int)(0.4+Math.log(getTotalTeams())/Math.log(2));
        }
        else if (tournamentFormat.equals("Round Robin")){
            return 1;
        }
        else{
            if((int)(0.4+Math.log(getTotalTeams()/Math.log(2)))==1)
                return 1;
            return 1 + (int)(0.4+Math.log(getTotalTeams())/Math.log(2));
        }
    }

    //creates the rounds
    public void createRounds(){
        for(int i=0; i<calculateNumOfRound(); i++){
            if(!tournamentFormat.equals("Knockout") && i==0)
                rounds.add(new RoundRobin(i+1));
            else{
                rounds.add(new Knockout(i+1));
            }
        }
        Log.e("createRounds", "numTeams" + getTotalTeams());
        Log.e("CalculateRounds NUM", "" + calculateNumOfRound());
        Log.e("ROund NUM", "" + Tournament.getInstance().getTotalRounds());
    }


    public Team getHighestRankingTeam(){
        List<Team> lt = new ArrayList<Team>(teams);
        Team.sortByRank(lt);
        return lt.get(0);
    }

    public void startTournament(){
        Collections.shuffle(getTeamList());
        createRounds();
        getRoundList().get(0).createInitialGroups();
        getRoundList().get(0).createSchedule();
    }

}

