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

    void parse(String args) {
        
        switch(args.split(" ")[0]) {
            case "help":
                Commands.help();
                break;
            case "pwd":
                Commands.pwd(Paths.get(""));
                break;
            case "ls":
                Commands.ls(Paths.get(""));
                break;
            case "quit":
                Commands.quit();
                break;
        }
    }
}