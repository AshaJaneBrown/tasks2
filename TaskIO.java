package ua.edu.sumdu.j2se.myropolska.anna.tasks;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskIO implements Serializable{

    public static DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS]");

    public static void write(TaskList tasks, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        try {
            dataOutputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                dataOutputStream.writeInt(task.getTitle().length());
                dataOutputStream.writeChars(task.getTitle());
                dataOutputStream.writeBoolean(task.isActive());
                dataOutputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    dataOutputStream.writeLong(task.getStartTime().getTime());
                    dataOutputStream.writeLong(task.getEndTime().getTime());
                } else
                    dataOutputStream.writeLong(task.getTime().getTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    public static void read(TaskList tasks, InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        try {
            int listSize = dataInputStream.readInt();
            for (int i = 0; i < listSize; i++) {
                Task task;
                int titleLength = dataInputStream.readInt();
                char[] titleChar = new char[titleLength];
                for (int a = 0; a < titleLength; a++) {
                    titleChar[a] = dataInputStream.readChar();
                }
                String title = String.valueOf(titleChar);
                boolean isActive = dataInputStream.readBoolean();
                int interval = dataInputStream.readInt();
                if (interval != 0) {
                    Date start = new Date(dataInputStream.readLong());
                    Date end = new Date(dataInputStream.readLong());
                    task = new Task(title, start, end, interval);
                } else {
                    Date time = new Date(dataInputStream.readLong());
                    task = new Task(title, time);
                }
                task.setActive(isActive);
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataInputStream.close();
        }
    }
    public static void writeBinary(TaskList tasks, File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(tasks, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    public static void readBinary(TaskList tasks, File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            read(tasks, fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    public static void write(TaskList tasks, Writer out) throws IOException {
        BufferedWriter outStream = new BufferedWriter(out);
        int counter = 0;
        try {
            for (Task t : tasks) {
                counter++;
                outStream.write("\"" + t.getTitle() + "\"");

                if (!t.isRepeated()) {
                    outStream.write(" at ");
                    outStream.write(dateFormat.format(t.getTime()));
                    if (!t.isActive())
                        outStream.write(" inactive");
                }
                else {
                    outStream.write(" from ");
                    outStream.write(dateFormat.format(t.getStartTime()));
                    outStream.write(" to ");
                    outStream.write(dateFormat.format(t.getEndTime()));
                    outStream.write(" every " + formatInterval(t.getRepeatInterval()) );
                    if (!t.isActive())
                        outStream.write(" inactive");
                }
                outStream.write( ((tasks.size() == counter) ? "." : ";") );
                outStream.newLine();
            }
        } finally {
            outStream.flush();
            outStream.close();
        }
    }

    public static String formatInterval(long milliseconds) {
        long milliseconds1 = milliseconds;
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        hours %= 24;
        minutes %= 60;
        seconds %= 60;
        milliseconds1 %= 1000;
        String time = "[";
        time += days > 0 ? days + (days > 1 ? " days " : " day ") : "";
        time += hours > 0 ? hours + (hours > 1 ? " hours " : " hour ") : "";
        time += minutes > 0 ? minutes + (minutes > 1 ? " minutes " : " minute ") : "";
        time += seconds > 0 ? seconds + (seconds > 1 ? " seconds " : " second ") : "";
        time += milliseconds1 > 0 ? milliseconds1 + (milliseconds1 > 1 ? " ms " : " ms ") : "";
        time += "]";
        return time;
    }

    public static void read(TaskList tasks, Reader in) throws IOException, ParseException {
       Scanner inStream = new Scanner(in);
        String s;
        try {
            while (inStream.hasNextLine()) {
                s = inStream.nextLine();
                String title = s.substring(1, s.indexOf("\"", 1));
                Task task;
                int repeated = s.indexOf("at");
                if (repeated != -1) {
                    String readTime = s.substring(s.indexOf("[",1), s.indexOf("]") + 1);
                    Date time = dateFormat.parse(readTime);
                    int inactive = s.indexOf("inactive");
                    task = new Task(title, time);
                    if (inactive == -1) {
                        task.setActive(true);
                    }
                } else {
                    String readStart = s.substring(s.indexOf("[", 1), s.indexOf("]") + 1);
                    Date start = dateFormat.parse(readStart);
                    int point1 = s.indexOf("to");
                    String readEnd = s.substring(s.indexOf("[", point1), s.indexOf("]", point1) + 1);
                    Date end = dateFormat.parse(readEnd);
                    String intervalString = s.substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]"));
                    int intervalMs = (int)formatInterval(intervalString);
                    task = new Task(title, start, end, intervalMs);
                }
                int inactive = s.indexOf("inactive");
                if (inactive == -1) {
                    task.setActive(true);
                }
                tasks.add(task);
            }
        }
        finally {
            inStream.close();
        }
    }
    public static long formatInterval(String s) throws ParseException, IllegalArgumentException {
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int millisecond = 0;

        String[] parts = s.split(" ");

        for (int i = 1; i < parts.length; i = i + 2) {
            if (parts[i].contains("day")) {
                day = Integer.parseInt(parts[i - 1]);
                continue;
            }
            if (parts[i].contains("hour")) {
                hour = Integer.parseInt(parts[i - 1]);
                continue;
            }
            if (parts[i].contains("minute")) {
                minute = Integer.parseInt(parts[i - 1]);
                continue;
            }
            if (parts[i].contains("second")) {
                second = Integer.parseInt(parts[i - 1]);
            }
            if (parts[i].contains("ms")) {
                millisecond = Integer.parseInt(parts[i - 1]);
            }
        }
        return ((day * 60 * 60 * 24 * 1000) + (hour * 60 * 60 * 1000) + (minute * 60 * 1000) + (second * 1000) + millisecond);
    }

    public static void writeText(TaskList tasks, File file) throws IOException {
        FileWriter outputFile = new FileWriter(file);
        try {
            write(tasks, outputFile);
            }
        catch (Exception e) {
             }
    }

    public static void readText(TaskList tasks, File file) throws IOException, ParseException  {
        FileReader inputFile = new FileReader(file);
        try {
            read(tasks, inputFile);
            }
        catch (Exception e) {
        }
    }
}


