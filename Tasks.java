package ua.edu.sumdu.j2se.Myropolska.Anna.tasks;

import java.util.*;

public class Tasks {

        public static Iterable <Task> incoming(Iterable <Task> tasks, Date from, Date to) throws Exception {
            ArrayTaskList incoming = new ArrayTaskList();

            for (Task task : tasks)
                try
                {   if (task.nextTimeAfter(from) != null)
                {
                    if ((task.nextTimeAfter(from).before(to) || task.nextTimeAfter(from).equals(to)))
                        incoming.add(task);
                }
                }
                catch (NullPointerException e) {
                }
            return incoming;
        }
        public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) throws Exception {
            TreeMap<Date, Set<Task>> calendar = new TreeMap<>();
            Iterable <Task> tasksForCalendar = Tasks.incoming(tasks, start, end);
            System.out.println("incoming " + tasksForCalendar);
            for (Task task : tasksForCalendar) {
                System.out.println("Calendar " + task.getTitle());


                Date time = task.nextTimeAfter(start);

                while(time != null && (time.before(end) || time.equals(end))) {
                    if (calendar.keySet().contains(time)) {

                        Set <Task> existingSet = calendar.get(time);
                        existingSet.add(task);

                        calendar.put(time, existingSet);

                    } else {

                        Set <Task> newSet = new HashSet <>();
                        if (time != null) {
                            newSet.add(task);
                        }
                        calendar.put(time, newSet);

                    }
                    time = task.nextTimeAfter(time);
                }

            }


            return calendar;
        }

    }


