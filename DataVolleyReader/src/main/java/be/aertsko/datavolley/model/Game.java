package be.aertsko.datavolley.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Koen Aerts
 */
@Data
public class Game {

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date gameDate;

    private Team homeTeam;

    private Team awayTeam;

    private GameResult result;

    public String toString() {
        return gameDate + ": " + homeTeam.getName() + " " + result.getSetsHome() + " - " + result.getSetsAway() + " " + awayTeam.getName();
    }


}
