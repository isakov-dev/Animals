import common.fileservice.FileService;
import common.fileservice.FileServiceException;
import common.uobject.UObject;
import operators.and.AndOperator;
import operators.or.OrOperator;
import rules.equals.Equals;
import rules.notequals.NotEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static HashMap<String, Class> rulesMap = new HashMap<>();
    private static HashMap<String, Class> operatorsMap = new HashMap<>();
    private static String animalsFilePath;
    private static List<String> rulesFilesPath;

    public static void setup() {

        rulesMap.put("=", Equals.class);
        rulesMap.put("!=", NotEquals.class);

        operatorsMap.put("OR", OrOperator.class);
        operatorsMap.put("AND", AndOperator.class);

        animalsFilePath = "src/main/resources/animals.txt";
        rulesFilesPath = Arrays.asList("src/main/resources/conditions1.txt", "src/main/resources/conditions2.txt",
                "src/main/resources/conditions3.txt");

    }

    public static void main(final String[] args) throws FileServiceException {

        setup();

        FileService animalsFileService = new FileService(animalsFilePath);
        UObject animals = animalsFileService.read();

        System.out.println(animals.getAll());

    }

}