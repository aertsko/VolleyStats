package be.aertsko.datavolley.model;

import be.aertsko.datavolley.dvw.DvwFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class GameFactory {

    private static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static Game createGame(DvwFile dvwFile){
        Game game = new Game();
        extractMatchDetails(dvwFile.getMatchDetails(),game);
        extractTeams(dvwFile.getTeams(),game);
        extractResult(dvwFile.getSets(),game);
        return game;
    }

    private static void extractMatchDetails(List<String> matchDetails, Game game) {
        if(matchDetails.isEmpty()) {
            throw new IllegalArgumentException("No matchdetails found");
        }
        String[] line1 = matchDetails.get(0).split(";");
        try {
            game.setGameDate(dateFormat.parse(line1[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void extractTeams(List<String> lines, Game game) {
        if(lines.size() != 2) {
            throw new IllegalArgumentException("Invalid team block");
        }
        game.setHomeTeam(createTeam(lines.get(0)));
        game.setAwayTeam(createTeam(lines.get(1)));
    }

    private static Team createTeam(String teamLine) {
        Team team = new Team();
        String[] teamElements = teamLine.split(";");
        team.setCode(teamElements[0]);
        team.setName(teamElements[1]);
        return team;
    }

    private static void extractResult(List<String> results, Game game) {
        if (results.size() == 0) {
            throw new IllegalArgumentException("No results found");
        }
        GameResult gameResult = new GameResult();
        int i = 0;
        for(String result:results) {
            SetResult setResult = createSetResult(result);
            if(setResult != null) {
                gameResult.addSetResult(setResult,i);
                i++;
            }
        }
        game.setResult(gameResult);
    }

    private static SetResult createSetResult(String input) {
        String[] resultDetails = input.split(";");
        if(resultDetails.length >= 5) {
            SetResult setResult = new SetResult();
            setResult.setSetPart1(resultDetails[1].replaceAll("\\s", ""));
            setResult.setSetPart2(resultDetails[2].replaceAll("\\s",""));
            setResult.setSetPart3(resultDetails[3].replaceAll("\\s",""));
            setResult.setSetResult(resultDetails[4].replaceAll("\\s",""));
            String[] scores = resultDetails[4].split("-");
            setResult.setPointsHome(new Integer(scores[0]));
            setResult.setPointsAway(new Integer(scores[1]));
            setResult.setDuration(new Integer(resultDetails[5]));
            return setResult;
        }
        return null;
    }
}
