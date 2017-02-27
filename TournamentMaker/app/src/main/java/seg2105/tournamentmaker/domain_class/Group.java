package seg2105.tournamentmaker.domain_class;

import java.util.ArrayList;
import java.util.List;

import seg2105.tournamentmaker.domain_class.Game;
import seg2105.tournamentmaker.domain_class.Round;
import seg2105.tournamentmaker.domain_class.RoundRobin;
import seg2105.tournamentmaker.domain_class.Team;
import seg2105.tournamentmaker.domain_class.Tournament;

/**
 * Created by Tarim (admin) on 2015-12-02.
 */
public class Group {
    //static variable
    private static int TOTAL_GROUPS = 0;
    //instance variables
    int groupNum;
    //association variable
    Round round;
    List<Team> groupTeams;
    List<Game> gameList;


    public Group(Round r){
        round = r;
        groupNum = TOTAL_GROUPS++;
        groupTeams = new ArrayList<Team>();
        gameList = new ArrayList<Game>();
    }

    public List<Game> getGameList(){
        return gameList;
    }


    public List<Team> getGroupTeams(){
        return groupTeams;
    }

    public Round getRound(){ return round; }

    public List<Team> getRankedTeams(){
        List<Team> result = new ArrayList<Team>(groupTeams);
        Team.sortByRank(result);
        return result;
    }

    public void setTeams(List<Team> teamList){
        groupTeams = teamList;
    }

    public void initializeTeams(){
        if(round instanceof RoundRobin){
            for(Team tm : Tournament.getInstance().getTeamList()){
                groupTeams.add(tm);
            }
        } else{
            groupTeams.add(Tournament.getInstance().getTeamList().get(2*groupNum));
            groupTeams.add(Tournament.getInstance().getTeamList().get(2*groupNum+1));
        }
    }


}
