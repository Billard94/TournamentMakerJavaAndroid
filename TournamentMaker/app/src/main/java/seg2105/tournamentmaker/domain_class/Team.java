package seg2105.tournamentmaker.domain_class;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tarim (admin) on 2015-12-02.
 */

public class Team
{
    //variables
    String numMembers;
    String teamName;
    String teamCity;
    int wins;
    int losses;
    int goals;
    int goalsAgainst;
    Boolean eliminated;
    int goalDiff;
    int points;

    // constructor
    public Team(String teamName, String teamCity, String numMembers){
        this.teamName = teamName;
        this.teamCity = teamCity;
        this.numMembers = numMembers;
        this.eliminated = false;
    }

    //getters
    public String getNumMembers(){
        return numMembers;
    }
    public String getTeamName(){
        return teamName;
    }
    public String getTeamCity(){
        return teamCity;
    }
    public int getWins(){
        return wins;
    }
    public int getLosses(){
        return losses;
    }
    public int getPoints(){
        return points;
    }
    public int getGoals(){
        return goals;
    }
    public int getGoalsAgainst(){
        return goalsAgainst;
    }
    public int getGoalDiff(){
        return goalDiff;
    }
    public Boolean isEliminated(){
        return eliminated;
    }
    // end of getters

    // increment
    public void incrementWins(){
        wins++;
    }
    public void incrementLosses(){
        losses++;
    }

    //setters
    public void setEliminated(boolean eliminated){
        this.eliminated = eliminated;
    }
    public void updateGoals(int goals) {
        this.goals += goals;
    }
    public void updateGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst += goalsAgainst;
    }
    public void updateGoalDiff() {
        this.goalDiff = goals-goalsAgainst;
    }
    public void updatePoints(){
        points = (getWins()*2) - (getLosses()*1);
    }

    public static void sortByRank(List<Team> list) {
        //sorting the list of teams
        Comparator<Team> comparator = new Comparator<Team>() {
            public int compare(Team c1, Team c2) {
                int result = c1.getPoints() - c2.getPoints();
                if (result == 0)
                    result = c1.getGoalDiff() - c2.getGoalDiff();
                return result;
            }
        };
        Collections.sort(list, comparator);
        Collections.reverse(list);
    }

}


