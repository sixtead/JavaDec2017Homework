package homework3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

class Commands {

    static void cpFile(Path source, Path destination) {
        try {
            if (Files.exists(destination) && Files.isRegularFile(destination)) {
                if (confirm("File " + destination + "already exists. Overwrite?")) {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                Path parent = destination.getParent();
                if (Files.exists(parent) && Files.isDirectory(parent)) {
                    Files.copy(source, destination);
                } else {
                    Files.createDirectories(parent);
                    Files.copy(source, destination);
                }
            }
        } catch(IOException e) {
            System.out.println("IOException occurred: " + e.getClass() + ": " + e.getMessage());
//            e.printStackTrace();
        }
    }

    static void cpDirectory(Path source, Path destination) {
        if(Files.exists(destination) && Files.isDirectory(destination)) {
            if(confirm("Directory " + destination + "already exists. Copy anyway?")) {
                cpRecursively(source, destination);
            }
        } else {
            cpRecursively(source, destination);
        }
    }

    private static void cpRecursively(Path source, Path destination) {
        try (DirectoryStream<Path> content = Files.newDirectoryStream(source)) {
            for(Path path : content) {
                Path deeperDestination = destination.resolve(path.getFileName());
                if(Files.isDirectory(path)) {
                    Files.createDirectories(deeperDestination);
                    cpRecursively(path, deeperDestination);
                } else if(Files.isRegularFile(path)) {
                    Commands.cpFile(path, deeperDestination);
                } else {
                    System.out.println("This shouldn't happen");
                }
            }
        } catch (IOException e) {
            System.out.println("IOException has occurred: " + e.getClass() + ": " + e.getMessage());
//            e.printStackTrace();
        }
    }

    static void pwd(Path currentDir) {
        System.out.println(currentDir.toAbsolutePath().toString());
    }

    static void ls(Path dir) {
        try (DirectoryStream<Path> content = Files.newDirectoryStream(dir)) {
            for (Path path : content) {
                System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            System.out.println("An IOException has occurred");
        }
    }

    static void quit() {
        System.exit(0);
    }

    private static boolean confirm(String question) {
        Scanner ans = new Scanner(System.in);
        System.out.print(question + "(y/n) ");
        String answer = ans.next();
        if(!answer.equals("y") && !answer.equals("n")) {
            confirm(question);
        } else {
            if(answer.equals("y")) return true;
            if(answer.equals("n")) return false;
        }
        return false;
    }

//    static

}