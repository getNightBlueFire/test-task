package service.console.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ProcessRoute {

    static final String Separator = " ";
    static final String GROTTY = "Grotty";
    static final String POSH = "Posh";

    public static void writeToOutputFile(List<Route> list) {
        out(list, POSH);
        System.out.println();
        out(list, GROTTY);
    }

    public static void writeToOutputFile(List<Route> list, String path) {
        try (FileWriter fw = new FileWriter(path)) {
            out(list, POSH, fw);
            fw.write("\r\n");
            out(list, GROTTY, fw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Route> getInputFile(String path) {
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

    public static List<Route> getInputFile(List<String> path) {
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

    public static List<Route> preProcessCollections(List<Route> list) {
        HashMap<LocalTime, Route> timeHashMap = new HashMap<>();
        list.forEach(getRouteConsumer(timeHashMap));
        return sort(timeHashMap);
    }

    private static List<Route> sort(HashMap<LocalTime, Route> map) {
        return map.values().stream().sorted((o1, o2) -> {
            if (o1.getStart().equals(o2.getStart())) {
                if (o1.getFinish().equals(o2.getFinish())) {
                    return 0;
                }
                if (o1.getFinish().isBefore(o2.getFinish())) {
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

    private static void out(List<Route> list, String company, FileWriter fw) {
        listRouteSeparatedByCompanyName(list, company).forEach(
                throwingConsumerWrapper(route -> fw.write(getString(route))));
    }

    private static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private static String getString(Route rou) {
        return String.format("%s %s %s", rou.getBusCompany(), rou.getStart().toString(), rou.getFinish().toString());
    }

    private static void out(List<Route> list, String company) {
        listRouteSeparatedByCompanyName(list, company).forEach(route -> System.out.println(getString(route)));
    }

    private static List<Route> listRouteSeparatedByCompanyName(List<Route> list, String name) {
        List<Route> listOfType = new ArrayList<>();
        for (Route route : list) {
            if (route.getBusCompany().contentEquals(name))
                listOfType.add(route);
        }
        return listOfType;
    }

    private static Consumer<Route> getRouteConsumer(HashMap<LocalTime, Route> timeHashMap) {
        return route -> {
            int hour = route.getStart().getHour();
            int minute = route.getStart().getMinute();
            LocalTime localTime = route.getFinish().minusHours(hour).minusMinutes(minute);
            if (localTime.getHour() == 0 && localTime.getMinute() > 1) {
                if (isNeedInsert(timeHashMap, route, localTime)) {
                    timeHashMap.put(localTime, route);
                }
            }
        };
    }

    private static boolean isNeedInsert(HashMap<LocalTime, Route> timeHashMap, Route route, LocalTime localTime) {
        return isNotExist(timeHashMap, localTime) ||
                existButNotPosh(timeHashMap, localTime) ||
                existButIsEarlierRoute(timeHashMap, localTime, route);
    }

    private static boolean isNotExist(HashMap<LocalTime, Route> timeHashMap, LocalTime localTime) {
        return !timeHashMap.containsKey(localTime);
    }

    private static boolean existButNotPosh(HashMap<LocalTime, Route> timeHashMap, LocalTime localTime) {
        return timeHashMap.containsKey(localTime) && timeHashMap.get(localTime).getBusCompany().equals(GROTTY);
    }

    private static boolean existButIsEarlierRoute(HashMap<LocalTime, Route> timeHashMap, LocalTime localTime,
                                                  Route route) {
        return timeHashMap.containsKey(localTime) && route.getStart().isBefore(timeHashMap.get(localTime).getStart());
    }

}
