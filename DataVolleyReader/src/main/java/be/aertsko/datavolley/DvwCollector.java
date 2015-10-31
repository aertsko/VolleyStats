package be.aertsko.datavolley;

import be.aertsko.datavolley.dvw.DvwFile;
import be.aertsko.datavolley.model.Game;
import be.aertsko.datavolley.model.GameFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Koen Aerts
 */
public class DvwCollector {

    public static void main(String[] args) {
        DvwFileFinder finder = new DvwFileFinder();
        try {
            Files.walkFileTree((new File("C:/Dvs_video")).toPath(),finder);
            for(File file:finder.getFiles()) {
                DvwFile dvwFile = new DvwFile(file);
                Game game = GameFactory.createGame(dvwFile);
                System.out.println(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}