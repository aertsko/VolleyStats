package be.aertsko.datavolley.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Koen Aerts
 */
@Data
public class Game {

    private Date gameDate;

    private Team homeTeam;

    private Team awayTeam;




}
