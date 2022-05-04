package service.console.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleApp {
    static final String Separator = " ";
    static final String GROTTY = "Grotty";
    static final String POSH = "Posh";

    public static void main(String[] args) {
        String input;
        String output = null;
        List<Route> list;
        boolean isTest = args.length == 0;
        if (isTest) {
            List<String> test = new ArrayList<>();
            createTestDate(test);
            list = getInputFile(test);
        } else {
            input = args[0];
            list = getInputFile(input);
            output = input.concat("_output.txt");
        }

        List<Route> sort = sort(preProcessCollections(list));

        if (isTest) {
            writeToOutputFile(sort);
        } else {
            writeToOutputFile(sort, output);
        }

    }

    private static List<Route> sort(HashMap<LocalTime, Route> map) {
        return map.values().stream().sorted((o1, o2) -> {
            if (o1.getStart().equals(o2.getStart())) {
                if (o1.getFinish().equals(o2.getFinish())) {
                    return 0;
                }
                if (o2.getFinish().isBefore(o2.getFinish())) {
                    return -1;
                } else {
                    return 1;
                }
            }
            if (o1.getStart().isBefore(o2.getStart())) {
                return -1;
            } else {
                return 1;
            }
        }).collect(Collectors.toList());
    }

    private static void createTestDate(List<String> list) {
        list.add("Posh 10:15 11:10");
        list.add("Posh 10:10 11:00");
        list.add("Grotty 10:10 11:00");
        list.add("Grotty 16:30 18:45");
        list.add("Posh 12:05 12:30");
        list.add("Grotty 12:30 13:25");
        list.add("Grotty 12:45 13:25");
        list.add("Posh 17:25 18:01");
        //list.add("Posh 23:40 00:18");
    }

    private static List<Route> getInputFile(String path) {
        List<Route> list = new ArrayList<>();
        try (BufferedReader b = new BufferedReader(new FileReader(path))) {
            String readLine;
            while ((readLine = b.readLine()) != null) {
                String company = readLine.split(Separator)[0];
                Route ro = new Route();
                ro.setBusCompany(company);
                ro.setStart(LocalTime.parse(readLine.split(Separator)[1]));
                ro.setFinish(LocalTime.parse(readLine.split(Separator)[2]));
                list.add(ro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<Route> getInputFile(List<String> path) {
        List<Route> list = new ArrayList<>();
        for (String str : path
        ) {
            Route route = new Route();
            route.setBusCompany(str.split(Separator)[0]);
            route.setStart(LocalTime.parse(str.split(Separator)[1]));
            route.setFinish(LocalTime.parse(str.split(Separator)[2]));
            list.add(route);
        }
        return list;
    }

    private static HashMap<LocalTime, Route> preProcessCollections(List<Route> list) {
        HashMap<LocalTime, Route> timeHashMap = new HashMap<>();
        for (Route route : list) {
            int hour = route.getStart().getHour();
            int minute = route.getStart().getMinute();
            LocalTime localTime = route.getFinish().minusHours(hour).minusMinutes(minute);
            if (localTime.getHour() == 0 && localTime.getMinute() > 1) {
                if (!timeHashMap.containsKey(localTime) || (timeHashMap.containsKey(localTime) && timeHashMap.get(
                        localTime).getBusCompany().equals(GROTTY)) )
                    timeHashMap.put(localTime, route);
            }
        }
        return timeHashMap;
    }

    private static void writeToOutputFile(List<Route> list) {
        out(list, POSH);
        System.out.println();
        out(list, GROTTY);
    }

    private static void writeToOutputFile(List<Route> list, String path) {
        try (FileWriter fw = new FileWriter(path)) {
            out(list, POSH, fw);
            fw.write("\r\n");
            out(list, GROTTY, fw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void out(List<Route> list, String company, FileWriter fw) throws IOException {
        for (Route valueRoute : separateByCompanyName(list, company)) {
            String template = getString(valueRoute);
            fw.write(template);
        }
    }
    private static String getString(Route rou) {
        return rou.getBusCompany() + " " + rou.getStart().toString() + " " + rou.getFinish().toString();
    }

    private static void out(List<Route> list, String company) {
        for (Route valueRoute : separateByCompanyName(list, company)) {
            String template = getString(valueRoute);
            System.out.println(template);
        }
    }

    private static List<Route> separateByCompanyName(List<Route> list, String name) {
        List<Route> listOfType = new ArrayList<>();
        for (Route route : list) {
            if (route.getBusCompany().contentEquals(name))
                listOfType.add(route);
        }
        return listOfType;
    }
}
