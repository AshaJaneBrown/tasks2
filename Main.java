package ua.edu.sumdu.j2se.Myropolska.Anna.tasks;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        LinkedTaskList l = new LinkedTaskList();
        int interval = 3600;
        Date start = new Date();
        start.setDate(25);
        start.setMonth(8);
        start.setHours(14);
        start.setMinutes(22);
        start.setSeconds(0);

        Date end = new Date();
        end.setDate(26);
        end.setMonth(11);
        end.setHours(14);
        end.setMinutes(22);
        end.setSeconds(0);

        Date current2 = new Date();
        current2.setDate(25);
        current2.setMonth(11);
        current2.setHours(15);
        current2.setMinutes(22);
        current2.setSeconds(0);

        Date current3 = new Date();
        current3.setDate(25);
        current3.setMonth(9);
        current3.setHours(15);
        current3.setMinutes(22);
        current3.setSeconds(0);

        Date current = new Date();
        current.setDate(25);
        current.setMonth(10);
        current.setHours(15);
        current.setMinutes(22);
        current.setSeconds(0);




        //System.out.println("start = " + start);
        //System.out.println("end = " + end);
        //System.out.println("current = " + current);
        //System.out.println("interval = " + interval);
        //System.out.println();

        Task a = new Task("A", start);
        Task b = new Task("B", current2, end, 3500);
        Task c = new Task("C", current3, end, 90000);
        //Task b = new Task("B", 1);
        //Task c = new Task("C", 1);
        //Task d = new Task("D", 1);
        //Task e = new Task("E", 1);
        //a.setActive(true);
        //b.setActive(true);
        c.setActive(true);

        //System.out.println("Next time after = " + a.nextTimeAfter(current));
        //System.out.println();

        ArrayTaskList first = new ArrayTaskList();
        first.add(a);
        first.add(b);
        first.add(c);

        ArrayTaskList second = new ArrayTaskList();
        second.add(a);
        second.add(b);
        second.add(c);

        Iterable<Task> iter = first;

        //try {
        //    SortedMap<Date, Set<Task>> s = Tasks.calendar(iter, start, end);
        //    System.out.println(s);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        FileWriter f = new FileWriter("tmp.txt");
        TaskIO.write(second, f);
        //FileReader r = new FileReader("tmp.txt");
        File fi = new File("tmp.txt");
        TaskIO.readText(second, fi);
        System.out.println(second);







        /*Task[] ts = {a};
        for (Task t : ts)
            l.add(t);

        System.out.println("Array size " + l.size());
        l.getTask(4);
        System.out.println(l.node);
        System.out.println();

        l.remove(a);


        System.out.println("Array size " + l.size());
        System.out.println();
        System.out.println(l.getTask(0));
        System.out.println(l.getTask(1));
        System.out.println(l.getTask(2));
        System.out.println(l.getTask(3));
        //l.remove(e);

        System.out.println();
        System.out.println(l.node);
        Iterator it = l.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());

    }
}
*/
    }
}

/*    public static String formatInterval(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = "[";
        time += days > 0 ? days + (days > 1 ? " days " : " day ") : "";
        time += hours > 0 ? hours + (hours > 1 ? " hours " : " hour ") : "";
        time += minutes > 0 ? minutes + (minutes > 1 ? " minutes " : " minute ") : "";
        time += seconds > 0 ? seconds + (seconds > 1 ? " seconds" : " second") : "";
        time += "]";
        return time;
    }
              /*         String interval = s.substring(s.indexOf("[", endIndex2), s.indexOf("]", endIndex2) + 1);
                    int daysIndex = interval.indexOf("day");
                    int hoursIndex = interval.indexOf("hour");
                    int minutesIndex = interval.indexOf("min");
                    int secondsIndex = interval.indexOf("sec");
                    int daysMs = 0;
                    int hoursMs = 0;
                    int minutesMs = 0;
                    int secondsMs = 0;

                    if (daysIndex != -1) {
                        int days = Integer.parseInt(interval.substring(interval.indexOf("[", 1), daysIndex - 1));
                        daysMs = days * 24 * 60 * 60 * 1000;

                    } else {
                        if (hoursIndex != -1) {
                            int hours = Integer.parseInt(interval.substring(interval.indexOf("[", 1), hoursIndex - 1));
                            hoursMs = hours * 60 * 60 * 1000;
                        } else {
                            if (minutesIndex != -1) {
                                int minutes = Integer.parseInt(interval.substring(interval.indexOf("[", 1), minutesIndex - 1));
                                minutesMs = minutes * 60 * 1000;
                            } else {
                                if (secondsIndex != -1) {
                                    int seconds = Integer.parseInt(interval.substring(interval.indexOf("[", 1), secondsIndex - 1));
                                    secondsMs = seconds * 60 * 1000;
                                }
                            }

                        }
                    }

                    int intervalMs = (int) (daysMs + hoursMs + minutesMs + secondsMs);

                    */





