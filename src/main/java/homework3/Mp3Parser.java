package homework3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Mp3Parser {

    public static void main(String[] args) {
        Path firefoxPath = Paths.get(System.getProperty("user.home"), "AppData\\Local\\Mozilla\\Firefox");
        Path chromePath = Paths.get(System.getProperty("user.home"), 
                "AppData\\Local\\Google\\Chrome\\User Data\\Default\\Media Cache\\");
        Path dPath;

        if(args.length == 1) {
            dPath = FileSystems.getDefault().getPath(args[0]);
            if(!Files.exists(dPath)) {
                try {
					Files.createDirectories(dPath);
				} catch (IOException e) {
                    System.out.println("Unable to create '" + dPath + "'");
                    dPath = Paths.get(System.getProperty("user.home"), "Music\\parsed");
					// e.printStackTrace();
				}
            }
        } else {
            dPath = Paths.get(System.getProperty("user.home"), "Music\\parsed");
        }

        System.out.println("Saving to: " + dPath);

        FirefoxMp3Parser firefoxParser = new FirefoxMp3Parser(dPath);
        ChromeMp3Parser chromeParser = new ChromeMp3Parser(chromePath, dPath);

        try {
            Files.walkFileTree(firefoxPath, firefoxParser);
            chromeParser.parseMp3();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

}