package seg2105.tournamentmaker.domain_class;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Tarim (admin) on 2015-12-03.
 */
public class RoundRobin extends Round {


    public RoundRobin(int i){
        super(i);
    }

    protected int calculateNumOfGroup(){
        return 1;
    }

    protected List<Team> getWinningTeams(){
        return new ArrayList<Team>();
    }


    public void createSchedule(){
        Tournament t = Tournament.getInstance();
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(Calendar.MINUTE, 0);
        firstDate.set(Calendar.SECOND, 0);

        List<Game> gameList = groups.get(0).getGameList();
        int numTeams = t.getTotalTeams();
        int totalNumGames = (numTeams-1)*(numTeams)/2;
        int numGames = 0;
        for(int i=0; i<numTeams-1; i++){
            for(int j=i+1; j<numTeams; j++){
                firstDate = ((Calendar)firstDate.clone());
                if(numGames%2 == 0){
                    firstDate.add(Calendar.DATE, 1);
                    firstDate.set(Calendar.HOUR_OF_DAY, 12);
                }
                else {
                    firstDate.set(Calendar.HOUR_OF_DAY, 18);
                }
                Game gm = new Game(groups.get(0), firstDate.getTime());
                gm.addTeams(t.getTeamList().get(i), t.getTeamList().get(j));
                gameList.add(gm);
                numGames++;
            }

        }
    }

}
