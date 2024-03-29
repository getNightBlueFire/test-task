package service.console.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConsoleApp {

	public static void main(String[] args) {

		System.out.println("Всем хороших выходных!!!!");


		System.out.println();
		System.out.println("QWERTYU");// от ильи*


	}

	private static void sortList(List<Route> list) {
		list.sort(new Comparator<Route>() {

			@Override
			public int compare(Route o1, Route o2) {
				// TODO Auto-generated method stub

				if (o1.getStart().getHour() > o2.getStart().getHour()
				|| o1.getStart().getMinutes() > o2.getStart().getMinutes()
				|| o1.getFinish().getMinutes() < o2.getFinish().getMinutes()
				|| o1.getRouteTime() > o2.getRouteTime()) {
					return 1;
				}
				if (o1.getStart().getHour() < o2.getStart().getHour()
				|| o1.getStart().getMinutes() < o2.getStart().getMinutes()
				|| o1.getFinish().getMinutes() > o2.getFinish().getMinutes()
				|| o1.getRouteTime() < o2.getRouteTime()) {
					return -1;
				}
				return 0;
			}

		});
	}
     // Hello world!
	private static List<Route> getInputFile(String path) {
		List<Route> list = new ArrayList<Route>();
		try (BufferedReader b = new BufferedReader(new FileReader(new File(path)));) {
			String readLine = "";
			while ((readLine = b.readLine()) != null) {
				String company = readLine.split(" ")[0];
				int hs = Integer.valueOf(readLine.split(" ")[1].split(":")[0]);
				int ms = Integer.valueOf(readLine.split(" ")[1].split(":")[1]);
				int hf = Integer.valueOf(readLine.split(" ")[2].split(":")[0]);
				int mf = Integer.valueOf(readLine.split(" ")[2].split(":")[1]);
				Route ro = new Route(company, new BusTime(hs, ms), new BusTime(hf, mf));
				list.add(ro);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static void postProcessCollections(List<Route> list) {

		for (Route route : list) {
			if (route.getRouteTime() > 60) {
				list.remove(route);
			}
		}
		for (int i = 0; i < list.size() - 2; ++i) {
			Route t = list.get(i);
			for (int j = i + 1; j < list.size() - 1; ++j) {
				if (t.getRouteTime() == list.get(j).getRouteTime()
						&& t.getStart().getBusTime().equals(list.get(j).getStart().getBusTime())
						&& t.getFinish().getBusTime().equals(list.get(j).getFinish().getBusTime())) {
					list.remove(j);
				}
				if (t.getStart().getBusTime().equals(list.get(j).getStart().getBusTime())) {
					list.remove(j);
				}
				if (t.getFinish().getBusTime().equals(list.get(j).getFinish().getBusTime())) {
					list.remove(j - 1);
				}
			}
		}
	}

	private static void writeToOutputFile(List<Route> list, String path) {
		try (FileWriter fw = new FileWriter(path)) {
			for (Route route : separateByCompanyName(list, "Posh")) {
				fw.write(route.toOutput());
			}
			fw.write("\r\n");
			for (Route route : separateByCompanyName(list, "Grotty")) {
				fw.write(route.toOutput());
			}

		} catch (IOException e) {
			e.printStackTrace();
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
