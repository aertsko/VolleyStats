package be.aertsko.datavolley.dvw;

import com.google.common.io.Files;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Data
public class DvwFile {

    private List<String> fileHeader = new ArrayList<String>();
    private List<String> matchDetails = new ArrayList<String>();
    private List<String> teams = new ArrayList<String>();
    private List<String> more = new ArrayList<String>();
    private List<String> comments = new ArrayList<String>();
    private List<String> sets = new ArrayList<String>();
    private List<String> homeTeam = new ArrayList<String>();
    private List<String> awayTeam = new ArrayList<String>();
    private List<String> attackCombinations = new ArrayList<String>();
    private List<String> setterCalls = new ArrayList<String>();
    private List<String> winningSymbols = new ArrayList<String>();
    private List<String> reserve = new ArrayList<String>();
    private List<String> scout = new ArrayList<String>();

    public DvwFile(File file) throws IllegalArgumentException {
        try {
            List<String> lines = Files.readLines(file, Charset.defaultCharset());
            readFile(lines);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read DVW file");
        }
    }

    private void readFile(List<String> input) {
        List<String> stringList = null;
        for(String inputLine:input) {
            if(isCaption(inputLine)) {
                stringList = getStringList(inputLine);
            } else if(stringList != null){
                stringList.add(inputLine);
            }
        }
    }

    private boolean isCaption(String candidate) {
        return candidate.startsWith("[3");
    }

    /**
     * Get the list for the given caption
     *
     * @param   caption
     *          The caption in the DVW file
     * @return  The list corresponding with the given caption
     * @throws  IllegalArgumentException
     *          If the given caption is unknown
     */
    private List<String> getStringList(String caption) {
        if(caption.equalsIgnoreCase("[3DATAVOLLEYSCOUT]")) {
            return this.fileHeader;
        } else if(caption.equalsIgnoreCase("[3MATCH]")) {
            return this.matchDetails;
        } else if(caption.equalsIgnoreCase("[3TEAMS]")) {
            return this.teams;
        } else if(caption.equalsIgnoreCase("[3MORE]")) {
            return this.more;
        } else if(caption.equalsIgnoreCase("[3COMMENTS]")) {
            return this.comments;
        } else if(caption.equalsIgnoreCase("[3SET]")) {
            return this.sets;
        } else if(caption.equalsIgnoreCase("[3PLAYERS-H]")) {
            return this.homeTeam;
        } else if(caption.equalsIgnoreCase("[3PLAYERS-V]")) {
            return this.awayTeam;
        } else if(caption.equalsIgnoreCase("[3ATTACKCOMBINATION]")) {
            return this.attackCombinations;
        } else if(caption.equalsIgnoreCase("[3SETTERCALL]")) {
            return this.setterCalls;
        } else if(caption.equalsIgnoreCase("[3WINNINGSYMBOLS]")) {
            return this.winningSymbols;
        } else if(caption.equalsIgnoreCase("[3RESERVE]")) {
            return this.reserve;
        } else if(caption.equalsIgnoreCase("[3SCOUT]")) {
            return this.scout;
        }
        throw new IllegalArgumentException("Unknown caption: " + caption);
    }
}
