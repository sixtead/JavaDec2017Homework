package homework3;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

class CommandsParser {
    static void parseLs(String[] args) {
        if(args.length > 2) {
            System.out.println("Wrong command syntax. Type `help ls` for help.");
        } else if(args.length > 1){
            Path absolutePath = Paths.get(args[1]).toAbsolutePath();
            
            if(Files.exists(absolutePath)) {
                Commands.ls(absolutePath);
            } else {
                System.out.println("No such directory");
            }
        } else {
            Commands.ls(Paths.get(""));
        }
    }

    static void parseCp(String[] args) {
        if(args.length == 3) {
            Path source = Paths.get(args[1]).toAbsolutePath();
            Path destination = Paths.get(args[2]).toAbsolutePath();

            if(Files.exists(destination)) {
                System.out.println("File or directory: " + destination.toString()
                        + " already exists. Use cp -f source destination");
            } else {
                try {
                    Files.copy(source, destination);
                    System.out.println("Files copied");
                } catch (IOException e) {
                    System.out.println(e.getClass().getCanonicalName());
                    System.out.println(e.getMessage());
                }
            }
        } else if(args.length == 4) {
            Path source = Paths.get(args[2]).toAbsolutePath();
            Path destination = Paths.get(args[3]).toAbsolutePath();
            String option = args[1];

            switch (option) {
                case "-f":
                    try {
                        Files.copy(source, destination, REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println(e.getClass().getCanonicalName());
                        System.out.println(e.getMessage());
                    }
                    break;
                case "-r":

            }

        }
    }
}