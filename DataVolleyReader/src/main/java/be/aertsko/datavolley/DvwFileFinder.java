package be.aertsko.datavolley;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Koen Aerts
 */
public class DvwFileFinder implements FileVisitor<Path> {

    private List<File> files = new ArrayList<File>();

    final String DVW_FILE_EXTENSION = "DVW";

    public List<File> getFiles() {
        return files;
    }

    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(Files.getFileExtension(file.getFileName().toString()).equalsIgnoreCase(DVW_FILE_EXTENSION)) {
            files.add(file.toFile());
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

}
