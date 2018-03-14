package homework3;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class Commands {

    static void cpFile(Path source, Path destination) {
        try {
            if (Files.exists(destination) && Files.isRegularFile(destination)) {
                if (confirm("File " + destination + " already exists. Overwrite?")) {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                Path parent = destination.getParent();
                if (Files.exists(parent) && Files.isDirectory(parent)) {
                    Files.copy(source, destination);
                } else {
                    Files.createDirectories(parent);
                    Files.copy(source, destination);
                }
            }
        } catch(IOException e) {
            System.out.println("IOException occurred: " + e.getClass() + ": " + e.getMessage());
        }
    }

    static void cpDirectory(Path source, Path destination) {
        if(Files.exists(destination) && Files.isDirectory(destination)) {
            if(confirm("Directory " + destination + " already exists. Copy anyway?")) {
                cpRecursively(source, destination);
            }
        } else {
            cpRecursively(source, destination);
        }
    }

    private static void cpRecursively(Path source, Path destination) {
        try (DirectoryStream<Path> content = Files.newDirectoryStream(source)) {
            for(Path path : content) {
                Path deeperDestination = destination.resolve(path.getFileName());
                if(Files.isDirectory(path)) {
                    Files.createDirectories(deeperDestination);
                    cpRecursively(path, deeperDestination);
                } else if(Files.isRegularFile(path)) {
                    Commands.cpFile(path, deeperDestination);
                } else {
                    System.out.println("This shouldn't happen");
                }
            }
        } catch (IOException e) {
            error(e);
        }
    }

    static void ls(Path dir) {
        try (DirectoryStream<Path> content = Files.newDirectoryStream(dir)) {
            for (Path path : content) {
                System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            error(e);
        }
    }

    static void mkdir(Path destination) {
        try {
            Files.createDirectories(destination);
        } catch (IOException e) {
            error(e);
        }
    }

    static void mvFile(Path source, Path destination) {
        cpFile(source, destination);
        rmFile(source);
    }

    static void mvDirectory(Path source, Path destination) {
        cpDirectory(source, destination);
        rmDirectory(source);
    }

    static void rmFile(Path destination) {
        try {
            Files.delete(destination);
        } catch (IOException e) {
            error(e);
        }
    }

    static void rmDirectory(Path destination) {
        try (DirectoryStream<Path> content = Files.newDirectoryStream(destination)) {
            for(Path path : content) {
                Path deeperDestination = destination.resolve(path.getFileName());
                if(Files.isDirectory(path)) {
                    rmDirectory(deeperDestination);
                } else if(Files.isRegularFile(path)) {
                    rmFile(deeperDestination);
                } else {
                    System.out.println("This shouldn't happen");
                }
            }
            rmFile(destination);
        } catch (IOException e) {
            error(e);
        }
    }

    static void touch(Path destination) {
        try {
            if(Files.exists(destination)) {
                FileTime now = FileTime.fromMillis(System.currentTimeMillis());
                Files.setLastModifiedTime(destination, now);
            } else {
                Files.createDirectories(destination.getParent());
                Files.createFile(destination);
            }
        } catch (IOException e) {
            error(e);
        }
    }

    static void zip(Path source, Path destination) {
        try {
            if(!Files.exists(destination.getParent())) Files.createDirectories(destination.getParent());
            FileOutputStream fos = new FileOutputStream(destination.toFile());
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.setLevel(Deflater.DEFAULT_COMPRESSION);

            if(Files.isDirectory(source)) {
                addDirectoryToZip(source, source, zos);
            } else {
                addFileToZip(source, source.getFileName(), zos);
            }

            zos.close();

        } catch (IOException e) {
            error(e);
        }
    }

    private static void addFileToZip(Path source, Path zipEntry, ZipOutputStream zos) {
        try {
            FileInputStream fis = new FileInputStream(source.toString());
            ZipEntry ze = new ZipEntry(zipEntry.toString());
            zos.putNextEntry(ze);
            byte[] buffer = new byte[2048];
            int length;

            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
            fis.close();
        } catch (IOException e) {
            error(e);
        }
    }

    private static void addDirectoryToZip(Path source, Path baseDir, ZipOutputStream zos) {

        try (DirectoryStream<Path> content = Files.newDirectoryStream(source)) {
            for(Path path : content) {
                Path deeperSource = source.resolve(path.getFileName());
                if (Files.isDirectory(path)) {
                    addDirectoryToZip(deeperSource, baseDir, zos);
                } else if (Files.isRegularFile(path)) {
//                    addFileToZip(deeperSource, fromBaseDirToDeeperSource, zos);
                    addFileToZip(deeperSource, baseDir.relativize(deeperSource), zos);

                } else {
                    System.out.println("This shouldn't happen");
                }
            }

        } catch (IOException e) {
            error(e);
        }

//        try (DirectoryStream<Path> content = Files.newDirectoryStream(source)) {
//            for(Path path : content) {
//                Path deeperSource = source.resolve(path.getFileName());
//                if(Files.isDirectory(path)) {
//                    zipDirectory(deeperSource, destination);
//                } else if(Files.isRegularFile(path)) {
//                    zipFile(deeperSource, destination);
//                } else {
//                    System.out.println("This shouldn't happen");
//                }
//            }
//        } catch (IOException e) {
//            error(e);
//        }
    }

    static void pwd(Path currentDir) {
        System.out.println(currentDir.toAbsolutePath().toString());
    }


    static void quit() {
        System.exit(0);
    }

    private static boolean confirm(String question) {
        Scanner ans = new Scanner(System.in);
        System.out.print(question + "(y/n) ");
        String answer = ans.next();
        ans.close();
        if(!answer.equals("y") && !answer.equals("n")) {
            confirm(question);
        } else {
            if(answer.equals("y")) return true;
            if(answer.equals("n")) return false;
        }
        return false;
    }

    private static void error(IOException e) {
        System.out.println("IOException has occurred: " + e.getClass() + ": " + e.getMessage());
    }

}