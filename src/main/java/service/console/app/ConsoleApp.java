package service.console.app;

import static service.console.app.ProcessRoute.getInputFile;
import static service.console.app.ProcessRoute.processCollections;
import static service.console.app.ProcessRoute.writeToOutputFile;

import java.util.ArrayList;
import java.util.List;

public class ConsoleApp {

    public static void main(String[] args) {

        if (isTest(args)) {
            writeToOutputFile(processCollections(getInputFile(createTestDate())));
        } else {
            writeToOutputFile(processCollections(getInputFile(args[0])), args[1]);
        }

    }

    private static boolean isTest(String[] args) {
        return args.length == 0;
    }

    private static List<String> createTestDate() {
        List<String> list = new ArrayList<>();
        list.add("Posh 10:15 11:10");
        list.add("Posh 10:21 11:10");
        list.add("Posh 10:10 11:00");
        list.add("Posh 10:19 11:00");
        list.add("Posh 10:19 10:53");
        list.add("Grotty 10:10 11:00");
        list.add("Grotty 16:30 18:45");
        list.add("Posh 12:05 12:30");
        list.add("Grotty 12:30 13:25");
        list.add("Grotty 12:45 13:25");
        list.add("Posh 17:25 18:01");
        list.add("Posh 23:40 00:18");
        list.add("Posh 22:40 23:18");

        return list;
    }

}
