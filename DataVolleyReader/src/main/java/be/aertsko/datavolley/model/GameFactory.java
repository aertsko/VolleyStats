package be.aertsko.datavolley.model;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Koen Aerts
 */
public class GameFactory {

    public static Game createGame(File file){
        Game game = new Game();
        try {
            List<String> lines = Files.readLines(file, Charset.defaultCharset());
            extractTeams(lines,game);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return game;
    }

    private static void extractTeams(List<String> lines, Game game) {
        boolean inTeamBlock = false;
        boolean homeTeamRead = false;
        for(String line:lines){
            if(inTeamBlock && !homeTeamRead) {
                game.setHomeTeam(createTeam(line));
                homeTeamRead = true;
            }
            if(inTeamBlock && homeTeamRead) {
                game.setAwayTeam(createTeam(line));
                break;
            }
            if(line.equalsIgnoreCase("[3TEAMS]")) {
                inTeamBlock = true;
            }
        }
    }

    private static Team createTeam(String teamLine) {
        Team team = new Team();
        String[] teamElements = teamLine.split(";");
        team.setCode(teamElements[0]);
        team.setName(teamElements[1]);
        return team;
    }
}
