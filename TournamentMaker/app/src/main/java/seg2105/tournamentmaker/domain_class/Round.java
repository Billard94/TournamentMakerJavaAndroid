package seg2105.tournamentmaker.domain_class;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tarim (admin) on 2015-12-02.
 */
public abstract class Round {
    private static int CURRENT_ROUND = 1;
    //instance variables
    int roundNum;
    //association variables
    protected List<Group> groups;

    public Round(int i) {
        roundNum = i;
        groups = new ArrayList<Group>();
    }

    public static int getCurrentRound() {
        return CURRENT_ROUND;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Game> getAllGamesInRound() {
        List<Game> result = new ArrayList<Game>();
        for (Group gr : groups) {
            Log.e("in Round groups size", "" + groups.size());
            Log.e("in Round games SIZE", "" + gr.getGameList().size());
            for (Game gm : gr.getGameList()) {
                Log.e("in Round game team", "" + gm.getTeams().get(0).getTeamName());
                result.add(gm);
            }
        }
        return result;
    }

    public static  void incrementCurrentRound(){
        CURRENT_ROUND++;
    }

    public void startNextRound(Knockout oR) {
        if (this instanceof Knockout) {
            Knockout thisRound = (Knockout) this;
            List<Team> winningTeams = thisRound.getWinningTeams();
            Log.e("startNext oR", ""+oR.toString());
            Log.e("startNext this.roundNum", "" + thisRound.getRoundNum());
            Log.e("startNext oR.roundNum", ""+oR.getRoundNum());
            Log.e("startNext calcNumGroup", ""+oR.calculateNumOfGroup());
            Log.e("startNext", "winning team size " + winningTeams.size());

            for (int i = 0; i < oR.calculateNumOfGroup(); i++) {
                Group gr = new Group(oR);
                oR.groups.add(gr);
                List<Team> teams = new ArrayList<Team>();
                teams.add(winningTeams.get(2 * i));
                teams.add(winningTeams.get(2 * i + 1));
                gr.setTeams(teams);
                Log.e("startNext", "for loop,"+" i:"+i+" 2i:"+2*i+" 2i+1:"+(2*i+1));

            }
        }
        CURRENT_ROUND++;
        oR.createSchedule();


    }



    public List<Team> getRankedTeams() {
        List<Team> result = new ArrayList<Team>(Tournament.getInstance().getTeamList());
        Team.sortByRank(result);
        return result;
    }

    public void createInitialGroups() {
        for (int i = 0; i < calculateNumOfGroup(); i++) {
            Group gr = new Group(this);
            groups.add(gr);
            gr.initializeTeams();
        }
    }

    public boolean allGamesPlayed(){
        for(Game gm : getAllGamesInRound()){
            if(!gm.gamePlayed())
                return false;
        }
        return true;
    }

    //abstract methods, implementation depending on subclass
    protected abstract int calculateNumOfGroup();

    protected abstract List<Team> getWinningTeams();

    public abstract void createSchedule();
}
