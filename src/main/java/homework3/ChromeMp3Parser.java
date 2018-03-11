package homework3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.apache.tika.Tika;

public class ChromeMp3Parser {
    Path sourceDir;
    Path destinationDir;

    ChromeMp3Parser(Path sourceDir, Path destinationDir) {
        this.sourceDir = sourceDir;
        this.destinationDir = destinationDir;
    }

    void parseMp3() throws IOException {
        Path output = null;
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDir);

        for (Path path : directoryStream) {
            if(isMp3(path) && output == null) {
                output = Paths.get(path.getFileName() + "_");
                byte[] b = Files.readAllBytes(path);
                Files.write(output, b, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else if(isMp3(path) && output != null) {
                Path savePath = destinationDir.resolve(output.getFileName() + ".mp3");
                Files.copy(output, savePath, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(output);
                output = Paths.get(path.getFileName() + "_");
                byte[] b = Files.readAllBytes(path);
                Files.write(output, b, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else if(path.getFileName().toString().equals("index")) {
                Path savePath = destinationDir.resolve(output.getFileName() + ".mp3");
                Files.copy(output, savePath, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(output);
                output = null;
            } else if(!isMp3(path) && output != null) {
                byte[] b = Files.readAllBytes(path);
                Files.write(output, b, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        }
    }

    public boolean isMp3(Path p) {
        Tika tika = new Tika();

        try {
            String type = tika.detect(p);
            return type.equals("audio/mpeg");
		} catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
		}
    }
}