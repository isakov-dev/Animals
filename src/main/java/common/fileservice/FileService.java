package common.fileservice;

import common.uobject.UObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileService {

    private String fileName;
    private UObject data = new UObject();

    public FileService(String fileName) {
        this.fileName = fileName;
    }

    public UObject read() throws  FileServiceException {

        try {
            List<String> lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String[] lineArray =  iterator.next().split(";", -1);
                ArrayList<String> lineList = new ArrayList<String>(Arrays.asList(lineArray));
                data.add(lineList);
            }
        } catch (IOException e) {
            throw new FileServiceException("Error reading file");
        }

        return data;

    }

}
