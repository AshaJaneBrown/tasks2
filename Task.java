package ua.edu.sumdu.j2se.Myropolska.Anna.tasks;



import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
    /**
     * Task class contains information about tasks to be implemented
     * and is the basis for task manager application
     */
    private String title;
    //private int time;
    private boolean isActive;
    //private int start;
    //private int end;
    private int interval;
    private boolean isRepeated;
    private Date start;
    private Date end;
    private Date time;



    public Task(String title, Date time) {
        /**
         * Task constructor with the following parameters (String, int)
         */
        this.title = title;
        this.time = time;
        this.isActive = false;
        this.isRepeated = false;
    }

    public Task(String title, Date start, Date end, int interval) {
        /**
         * Task constructor with the following parameters (int, int, int)
         */
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isActive = false;
        this.isRepeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public Date getTime() {
        if (this.isRepeated())
            return start;
        else
            return time;
    }

    public void setTime(Date time) {
                this.time = time;
                if (this.isRepeated())
                    this.isRepeated = false;

    }

    public Date getStartTime() {
        if (this.isRepeated())
            return start;
        else
            return time;
    }

    public Date getEndTime() {
        if (this.isRepeated())
            return end;
        else
            return time;
    }

    public int getRepeatInterval() {
        if (this.isRepeated())
            return interval;
        else
            return 0;
    }

    public void setTime(Date start, Date end, int interval) {

                this.start = start;
                this.end = end;
                this.interval = interval;
                if (!this.isRepeated())
                    this.isRepeated = true;

    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public Date nextTimeAfter(Date current) {
        Date result = null;
        /**
         * A method to define the next time when the task will be implemented
         */
        if (!this.isActive()) {
            return null;
        } else {
            if (!isRepeated()) {
                if (time.after(current)) {
                    System.out.println("1");
                    return time;
                } else {
                    System.out.println("2");
                    return null;
                }

            } else {
                if (start.after(current)) {
                    System.out.println("3");
                    return start;
                } else {
                    if (current.after(end)) {
                        System.out.println(current);
                        System.out.println(end);
                        System.out.println("4");
                        return null;
                    } else {
                        System.out.println("5");
                        long msPeriod = end.getTime() - start.getTime();
                        System.out.println(msPeriod);
                        long generalNumberOfIntervals = (msPeriod / (interval * 1000));
                        System.out.println(generalNumberOfIntervals);
                        /**
                         * The general number of intervals of a repeated task
                         * that fits into indicated period
                         */
                        int numberOfIntervalsBeforeCurrent = (int) ((current.getTime() - start.getTime())
                                / (interval * 1000));
                       System.out.println(numberOfIntervalsBeforeCurrent);
                        /**
                         * Tne number of time intervals that has passed
                         * before the current time
                         */
                        if (current.getTime() >= (generalNumberOfIntervals * interval * 1000
                                + start.getTime())) {
                            System.out.println("6");
                            return null;
                            /**
                             * Checking whether all the time intervals possible
                             * have already passed
                             */
                        } else {
                            result = new Date(((numberOfIntervalsBeforeCurrent + 1)
                                    * interval * 1000) + start.getTime());
                            return result;
                        }
                        /**
                         * Defing and returning the start time
                         * of the next interval
                         */
                    }

                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        if (this.title != that.title)
            return false;
        if (this.time.compareTo(that.time) != 0)
            return false;
        if (this.isActive != that.isActive)
            return false;
        if (this.isRepeated != that.isRepeated)
            return false;
        //if (this.start.compareTo(that.start) != 0)
           // return false;
        //if (this.end.compareTo(that.end) != 0)
           // return false;
        //if (this.interval != that.interval)
            //return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = (int) (31 * result);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (isRepeated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task {" +
                "title = '" + title + '\'' +
                ", time = " + time +
                ", isActive = " + isActive +
                ", start = " + start +
                ", end = " + end +
                ", interval = " + interval +
                ", isRepeated = " + isRepeated +
                '}';
    }

    @Override
    protected Task clone() throws CloneNotSupportedException {
        Task cloned = new Task(this.title, this.time);
        cloned.isRepeated = this.isRepeated;
        cloned.isActive = this.isActive;
        return cloned;
    }
}
