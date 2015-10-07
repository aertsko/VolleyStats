package be.aertsko.volleystats.rest;

import be.aertsko.datavolley.DvwFileFinder;
import be.aertsko.datavolley.model.Game;
import be.aertsko.datavolley.model.GameFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Koen Aerts
 */
@RestController
@RequestMapping("dvw")
@EnableAutoConfiguration
public class DvwFileRestService {

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    Collection<Game> getDvwFiles() {
        DvwFileFinder finder = new DvwFileFinder();
        try {
            Files.walkFileTree((new File("C:/Dvs_video")).toPath(), finder);
            List<Game> games = new ArrayList<Game>();
            for(File file:finder.getFiles()) {
                games.add(GameFactory.createGame(file));
            }
            return games;
        } catch (IOException e) {
            return new ArrayList<Game>();
        }
    }



}
