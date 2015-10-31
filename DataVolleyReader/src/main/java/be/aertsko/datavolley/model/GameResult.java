package be.aertsko.datavolley.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameResult {

    private List<SetResult> sets = new ArrayList<SetResult>(5);

    private int setsHome = 0;
    private int setsAway = 0;

    public void addSetResult(SetResult setResult, int set) {
        sets.add(set,setResult);
        if(setResult.getPointsHome() > setResult.getPointsAway()) {
            setsHome++;
        } else {
            setsAway++;
        }
    }

}
