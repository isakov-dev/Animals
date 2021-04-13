package common.fileservice;

import common.uobject.UObject;
import common.uobject.UObjectException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileServiceTest {

    @Test
    public void readingTest() throws URISyntaxException, FileServiceException, UObjectException {

        UObject expectedObj = new UObject();
        ArrayList<String> line1 = new ArrayList<>();
        line1.add("line1el1");
        line1.add("line1el2");
        line1.add("line1el3");
        ArrayList<String> line2 = new ArrayList<>();
        line2.add("line2el1");
        line2.add("line2el2");
        line2.add("line2el3");
        expectedObj.add(line1);
        expectedObj.add(line2);

        URL resource = getClass().getClassLoader().getResource("testFile.txt");
        File file = Paths.get(resource.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();

        FileService fileService = new FileService(absolutePath);
        UObject actualObj = fileService.read();

        Assert.assertEquals(expectedObj.get(0), actualObj.get(0));

    }

    @Test (expected = FileServiceException.class)
    public void notExistingFileTest() throws FileServiceException {

        FileService fileService = new FileService("notExistingFile.txt");
        fileService.read();

    }

}