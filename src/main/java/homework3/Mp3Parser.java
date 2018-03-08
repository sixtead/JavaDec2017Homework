package homework3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Mp3Parser {

    public static void main(String[] args) {
        Path homePath = Paths.get(System.getProperty("user.home"), "AppData\\Local\\Mozilla\\Firefox");
        String dPath = "c:\\mp3";

        System.out.println(System.getProperty("user.home"));

        Mp3FileWalker walker = new Mp3FileWalker(dPath);
        try {
			Files.walkFileTree(homePath, walker);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}