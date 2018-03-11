package homework3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

class Commands {
    static void help() {
        System.out.println("Available commands are: help, pwd, ls, quit");
    }

    static void pwd(Path currentDir) {
        System.out.println(currentDir.toAbsolutePath().toString());
    }

    static void ls(Path dir) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            for (Path path : directoryStream) {
                System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            System.out.println("An IOException has occured");
        }
    }

    static void quit() {
        System.exit(0);
    }

}