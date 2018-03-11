package homework3;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
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

public class FirefoxMp3Parser extends SimpleFileVisitor<Path> {
    Path DestinationDir;

    FirefoxMp3Parser(Path destinationDir) {
        this.DestinationDir = destinationDir;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,
            BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attrs) {
        // for firefox
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
            System.out.println(e.getMessage());
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
                ? DestinationDir.resolve(artist + " - " + title + ".mp3")
                : DestinationDir.resolve(source.getFileName() + ".mp3");

            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException | SAXException | TikaException e) {
            System.out.println(e.getMessage());
        }
    }

    void mkdir(Path dir) {
        if(!Files.exists(dir) || !Files.isDirectory(dir)) {
            try {
				Files.createDirectories(dir);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
        }
    }

}