package seg2105.tournamentmaker.domain_class;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Tarim (admin) on 2015-12-03.
 */
public class Knockout extends Round {

    public Knockout(int i){
        super(i);
    }

    protected int calculateNumOfGroup(){
        int totalTeams = Tournament.getInstance().getTotalTeams();
        return (totalTeams/((int)(0.3+Math.pow(2,roundNum))));
    }

    public List<Team> getWinningTeams(){
        List<Team> result = new ArrayList<Team>();
        for(Group gr: groups){
            Team t = gr.getGameList().get(0).getWinner();
            result.add(t);
        }
        return result;
    }

//    public List<Team> getNonEliminatedTeams(){
//        for(Team t : Tournament.getInstance().getTeamList()){
//            if(t.isEliminated())
//        }
//    }

    public void createSchedule(){
        Log.e("createSchedule roundNum",""+ roundNum);
        Log.e("createSchedule CurRound",""+ getCurrentRound());
        List<Team> teamList = Tournament.getInstance().getTeamList();
        Log.e("createSchedule","after teamList get "+teamList);
        Calendar firstDate = Calendar.getInstance();
        Log.e("createSchedule","after Calendar.getinstance() "+firstDate);

        if(roundNum != 1) {
            try {
                List<Game> prevRoundGames = Tournament.getInstance().getRoundList().get(roundNum - 2).getAllGamesInRound();
                firstDate.setTime(prevRoundGames.get(prevRoundGames.size() - 1).getEndTime());
            } catch(Exception e){
                Log.e("createSchedule", "exception "+e.getClass().getName());
                e.printStackTrace();
            }

        }

        firstDate.add(Calendar.DATE, 1);
        firstDate.set(Calendar.MINUTE, 0);
        firstDate.set(Calendar.SECOND, 0);
        Log.e("createSchedu groupSize", ""+groups.size());
        for(int i=0; i<groups.size(); i++){
            List<Game> gameList = groups.get(i).getGameList();
            firstDate = (Calendar) firstDate.clone();

            if(i%2 == 0) {
                firstDate.add(Calendar.DATE, 1);
                firstDate.set(Calendar.HOUR_OF_DAY, 12);
            }
            else
                firstDate.set(Calendar.HOUR_OF_DAY, 18);

            Game newGame = new Game(groups.get(i), firstDate.getTime());
            if(roundNum != 1) {
                Log.e("createSche if, num", " "+roundNum);
                teamList = groups.get(i).getGroupTeams();
                newGame.addTeams(teamList.get(0), teamList.get(1));
            }
            else {
                Log.e("createSche else, num", " "+roundNum);
                newGame.addTeams(teamList.get(2 * i), teamList.get(2 * i + 1));
            }
            gameList.add(newGame);

        }
    }
}
