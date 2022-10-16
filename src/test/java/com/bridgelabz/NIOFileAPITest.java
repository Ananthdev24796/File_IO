package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_With_NIO="TempPlayGround";
    @Test
    public void givenPathWhenCheckedThenConfirm()throws IOException {
        // checking file Exists
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        // Delete file and check file not exitst
        Path playPath = Paths.get(HOME+"/"+PLAY_With_NIO);
        if (Files.exists(playPath)) FileUtils.deleteFolder(playPath.toFile());
        Assert.assertTrue(Files.notExists(playPath));

        // create Directory
        Files.createDirectories(playPath);
        Assert.assertTrue(Files.exists(playPath));

        // create file
        IntStream.range(1,10).forEach(cntr->{
            Path tempFile  = Paths.get(playPath+"/temp"+cntr);
            Assert.assertTrue(Files.notExists(tempFile));
            try{
                Files.createFile(tempFile);
            }
            catch(IOException e){}
            Assert.assertTrue(Files.exists(tempFile));
        });

        //List files , Directory as well as Files with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath,path-> path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);

    }
}
