package homework3;

import java.nio.file.Paths;
import java.util.Scanner;

public class Shell {
    
    public void run() {
        Scanner sc;
         sc = new Scanner(System.in);

        while(true) {
//            sc = new Scanner(System.in);
            System.out.print("$: ");
            parse(sc.nextLine());
//            sc.close();
        }

        // sc.close();
    }

    private void parse(String command) {
        String[] args = command.split(" ");
        
        switch(args[0]) {
            case "cp":
                CommandsParser.parseCp(args);
                break;
            case "ls":
                CommandsParser.parseLs(args);
                break;
            case "mkdir":
                CommandsParser.parseMkdir(args);
                break;
            case "mv":
                CommandsParser.parseMv(args);
                break;
            case "rm":
                CommandsParser.parseRm(args);
                break;
            case "touch":
                CommandsParser.parseTouch(args);
                break;
            case "pwd":
                Commands.pwd(Paths.get(""));
                break;
            case "zip":
                CommandsParser.parseZip(args);
                break;
            case "unzip":
                CommandsParser.parseUnzip(args);
                break;
            case "help":
                CommandsParser.help(args);
                break;
            case "quit":
                Commands.quit();
                break;
            default:
                System.out.println("Unrecognized command");
                CommandsParser.help(args);
                break;
        }
    }
}