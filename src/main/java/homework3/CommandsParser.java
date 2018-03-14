package homework3;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CommandsParser {
    
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

    static void parseMkdir(String[] args) {
        if(args.length == 2) {
            Path destination = Paths.get(args[1]);
            if(Files.exists(destination) && Files.isDirectory(destination)) {
                System.out.println("Directory already exists");
            } else {
                Commands.mkdir(destination);
            }
        } else {
            syntaxError("mkdir");
        }
    }

    static void parseMv(String[] args) {
        if(args.length == 3) {
            Path source = Paths.get(args[1]);
            Path destination = Paths.get(args[2]);

            if(!Files.exists(source)) {
                System.out.println("Source file or directory does not exist");
            } else {
                if(Files.isDirectory(source)) {
                    Commands.mvDirectory(source, destination);
                } else {
                    Commands.mvFile(source, destination);
                }
            }
        } else {
            syntaxError("mv");
        }
    }

    static void parseRm(String[] args) {
        if(args.length == 2) {
            Path destination = Paths.get(args[1]);

            if(!Files.exists(destination)) {
                System.out.println("File or directory does not exist");
            } else {
                if(Files.isDirectory(destination)) {
                    Commands.rmDirectory(destination);
                } else {
                    Commands.rmFile(destination);
                }
            }
        } else {
            syntaxError("rm");
        }
    }

    static void parseTouch(String[] args) {
        if(args.length == 2) {
            Path destination = Paths.get(args[1]);

            Commands.touch(destination);
        } else {
            syntaxError("touch");
        }
    }

    static void parseZip(String[] args) {
        if(args.length == 2 || args .length == 3) {
            Path source = Paths.get(args[1]);
            
            if(!Files.exists(source)) {
                System.out.println("File or directory does not exist");
            } else {
                Path destination = (args.length == 3)
                    ? Paths.get(args[2])
                    : source.resolveSibling(source.getFileName() + ".zip");
    
                Commands.zip(source, destination);
            }
        } else {
            syntaxError("zip");
        }
    }

    static void parseUnzip(String[] args) {
        if(args.length == 2 || args .length == 3) {
            Path source = Paths.get(args[1]);
            
            if(!Files.exists(source)) {
                System.out.println("File does not exist");
            } else {
                Path destination = (args.length == 3)
                    ? Paths.get(args[2])
                    : source.resolveSibling(source.getFileName().toString().replace(".zip", ""));
    
                Commands.unzip(source, destination);
            }
        } else {
            syntaxError("unzip");
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
            System.out.println("cp, ls, mkdir, mv, rm, touch, zip, unzip");
            return;
        }

        switch (command) {
            case "cp":
                message = "Command used to copy files\\directories\n" +
                        "usage: cp source destination";
                break;
            case "ls":
                message = "Command used to list directory content\n" +
                        "usage: ls [path]";
            case "mkdir":
                message = "Command used to create directory\n" +
                        "usage: mkdir destination";
                break;
            case "mv":
                message = "Command used to move file or directory\n" +
                        "usage: mv source destination";
                break;
            case "rm":
                message = "Command used to remove file or directory\n" +
                        "usage: rm destination";
                break;
            case "touch":
                message = "Command used to create new file or modify last access time of existing file\n" +
                        "usage: touch file";
                break;
            case "zip":
                message = "Command used to zip files\\directories\n" +
                        "usage: zip source [destination]";
                break;
            case "unzip":
                message = "COmmand used to unzip a zip archive\n" +
                        "usage: unzip source [destination]";
            default:
                message = "";
                break;
        }

        System.out.println(message);
    }
}