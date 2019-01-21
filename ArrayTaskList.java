package ua.edu.sumdu.j2se.myropolska.anna.tasks;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayTaskList extends TaskList {
    public Task[] tasks = new Task[5];

    @Override
    public void add(Task task) {
        try {
            if (task != null) {
                boolean freePlace = false;
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        tasks[i] = task;
                        freePlace = true;
                        break;
                    }
                }
                if (!freePlace) {
                    Task[] newTasks = new Task[tasks.length * 2];
                    for (int i = 0; i < tasks.length; i++) {
                        newTasks[i] = tasks[i];
                    }
                    newTasks[tasks.length] = task;
                    tasks = newTasks;
                }
                size++;
            } else throw new NullPointerException("Task cannot have null value");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean remove(Task task) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == task) {
                while (i < tasks.length - 1) {
                    tasks[i] = tasks[++i];
                }
                tasks[i] = null;
                size--;
                return true;

            }
        }
        return false;
    }

    @Override
    public Task getTask(int index) {
        try {
            if (tasks[index] != null)
                return tasks[index];
            else {
                throw new NullPointerException("Task does not exist");

            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task does not exist");
        }
        return null;
    }

   public Iterator<Task> iterator() {
        return new Iterator <Task>() {
            public int current = -1;
            boolean nextIsCalled = false;

            @Override
            public boolean hasNext() {
                if (ArrayTaskList.this.size() - 1 > current)
                    return true;
                else
                    return false;
            }

            @Override
            public Task next() {
                nextIsCalled = true;
                if (hasNext()) {
                    return tasks[++current];
                }

                else
                    return null;

            }
            @Override
            public void remove() {
                if (nextIsCalled) {
                    ArrayTaskList.this.remove(tasks[current--]);
                    nextIsCalled = false;
                    }

                else
                    throw new IllegalStateException();
            }

            };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        boolean isEqual = true;
        Iterator<Task> iterator1 = ((ArrayTaskList) o).iterator();
        Iterator<Task> iterator2 = this.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()){
            if(!iterator1.next().equals(iterator2.next()))
                isEqual = false;
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tasks);
    }

    @Override
    public String toString() {
        return "ArrayTaskList {" +
                "tasks = " + Arrays.toString(tasks) +
                ", size = " + size +
                '}';
    }

    @Override
    protected ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList cloned = new ArrayTaskList();
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext())
            cloned.add(iterator.next());
        return cloned;
    }
}






