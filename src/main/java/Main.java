import common.fileservice.FileService;
import common.fileservice.FileServiceException;
import common.uobject.UObject;
import counter.Counter;
import operators.and.AndOperator;
import operators.or.OrOperator;
import operatorsresolver.OperatorsResolver;
import operatorsresolver.OperatorsResolverException;
import rules.equals.Equals;
import rules.notequals.NotEquals;
import rulesgroup.RulesGroup;
import rulesgroup.RulesGroupException;
import rulesresolver.RulesResolver;
import rulesresolver.RulesResolverException;

import java.util.*;

public class Main {

    private static HashMap<String, Class> rulesMap = new HashMap<>();
    private static HashMap<String, Class> operatorsMap = new HashMap<>();
    private static String animalsFilePath;
    private static List<String> rulesAndOperatorsFilesPath;

    public static void setup() {

        rulesMap.put("=", Equals.class);
        rulesMap.put("!=", NotEquals.class);

        operatorsMap.put("OR", OrOperator.class);
        operatorsMap.put("AND", AndOperator.class);

        animalsFilePath = "src/main/resources/animals.txt";
        rulesAndOperatorsFilesPath = Arrays.asList("src/main/resources/conditions1.txt",
                "src/main/resources/conditions2.txt", "src/main/resources/conditions3.txt");

    }

    public static void main(final String[] args) throws FileServiceException, RulesResolverException, OperatorsResolverException, RulesGroupException {

        setup();

        FileService animalsFileService = new FileService(animalsFilePath);
        UObject animals = animalsFileService.read();

        Iterator<String> rulesAndOperatorsFilesPathIterator = rulesAndOperatorsFilesPath.iterator();
        while (rulesAndOperatorsFilesPathIterator.hasNext()) {

            FileService rulesAndOperatorsFileService = new FileService(rulesAndOperatorsFilesPathIterator.next());
            UObject rulesAndOperatorsArray = rulesAndOperatorsFileService.read();

            RulesResolver rulesResolver = new RulesResolver(rulesMap, rulesAndOperatorsArray);
            OperatorsResolver operatorsResolver = new OperatorsResolver(operatorsMap, rulesAndOperatorsArray);

            RulesGroup rulesGroup = new RulesGroup(rulesResolver.resolve(), operatorsResolver.resolve());

            Counter counter = new Counter(animals, rulesGroup);

            System.out.println(counter.count());

        }

    }

}