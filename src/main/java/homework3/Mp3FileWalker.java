package homework3;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Mp3FileWalker  extends SimpleFileVisitor<Path> {
    String DestinationDir;

    Mp3FileWalker(String destinationDir) {
        this.DestinationDir = destinationDir;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,
            BasicFileAttributes attrs) {
        System.out.println("Entering " + dir.toString());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attrs) {
        if(isMp3(file)) saveAndRename(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
            IOException e) {
        // System.err.println(e);
        return FileVisitResult.CONTINUE;
    }

    public boolean isMp3(Path p) {
        Tika tika = new Tika();

        try {
            String type = tika.detect(p);
            return type.equals("audio/mpeg");
		} catch (IOException e) {
            e.printStackTrace();
            return false;
		}
    }

    public void saveAndRename(Path source) {
        Parser parser = new AutoDetectParser();
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        Path destination;
        
        try (InputStream stream = Files.newInputStream(source)) {
            parser.parse(stream, handler, metadata, new ParseContext());

            String artist = metadata.get(TikaCoreProperties.CREATOR);
            String title = metadata.get(TikaCoreProperties.TITLE);

            mkdir(DestinationDir);

            destination = (artist != null && artist.equals("")
                    && title != null && !title.equals("") )
                ? Paths.get(DestinationDir, artist + " - " + title + ".mp3")
                : Paths.get(DestinationDir, source.getFileName() + ".mp3");

            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
    }

    void mkdir(String dir) {
        Path dirPath = Paths.get(dir);

        if(!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            try {
				Files.createDirectories(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

}