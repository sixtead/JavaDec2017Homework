package homework3;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CommandsParser {
    static void parseLs(String[] args) {
        if(args.length > 2) {
            syntaxError("ls");
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
            Path source = Paths.get(args[1]);
            Path destination = (args[2].endsWith(File.separator))
                    ? Paths.get(args[2]).resolve(source.getFileName())
                    : Paths.get(args[2]);

            if(!Files.exists(source)) {
                System.out.println("Source file or directory does not exist");
            } else {
                if(Files.isDirectory(source)) {
                    Commands.cpDirectory(source, destination);
                } else {
                    Commands.cpFile(source, destination);
                }
            }
        } else {
            syntaxError("cp");
        }
    }

    private static void syntaxError(String command) {
        System.out.println("Wrong command syntax. type 'help " + command + "' for help.");
    }

    static void help(String[] args) {
        String command;
        String message;

        try {
            command = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Use 'help command'. Available commands are: ");
            System.out.println("cp");
            return;
        }

        switch (command) {
            case "cp":
                message = "Command used to copy files\\directories\n" +
                        "usage: cp source destination";
                break;
            default:
                message = "";
                break;
        }

        System.out.println(message);
    }
}