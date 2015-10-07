package be.aertsko.datavolley;

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
                System.out.println(file);
                GameFactory.createGame(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}