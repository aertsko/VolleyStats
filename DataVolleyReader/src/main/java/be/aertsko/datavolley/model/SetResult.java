package be.aertsko.datavolley.model;

import lombok.Data;

@Data
public class SetResult {

    private String setPart1;
    private String setPart2;
    private String setPart3;
    private String setResult;
    private int duration;
    private int pointsHome;
    private int pointsAway;

}
