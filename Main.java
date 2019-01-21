package ua.edu.sumdu.j2se.myropolska.anna.tasks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

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

        Task a = new Task("A", start);
        Task b = new Task("B", current2, end, 3500);
        Task c = new Task("C", current3, end, 90000);

        c.setActive(true);

        ArrayTaskList first = new ArrayTaskList();
        ArrayTaskList second = new ArrayTaskList();
        second.add(a);
        second.add(b);
        second.add(c);

        File f = new File("tmp.txt");
        TaskIO.writeText(second, f);
        TaskIO.readText(first, f);
        //System.out.println(first);

    }
}
