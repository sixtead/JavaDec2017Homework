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
            case "help":
                CommandsParser.help(args);
                break;
            case "pwd":
                Commands.pwd(Paths.get(""));
                break;
            case "ls":
                CommandsParser.parseLs(args);
                break;
            case "quit":
                Commands.quit();
                break;
        }
    }
}