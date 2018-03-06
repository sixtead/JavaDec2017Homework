package homework3;

import java.nio.file.Paths;
import java.util.Scanner;

public class Shell {
    
    public void run() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("$: ");
            parse(sc.nextLine());
        }

        // sc.close();
    }

    void parse(String command) {
        String[] args = command.split(" ");
        
        switch(args[0]) {
            case "help":
                Commands.help();
                break;
            case "pwd":
                Commands.pwd(Paths.get(""));
                break;
            case "ls":
                CommandsParser.parseLs(args);
                break;
            case "cp":
                CommandsParser.parseCp(args);
                break;
            case "quit":
                Commands.quit();
                break;
        }
    }
}