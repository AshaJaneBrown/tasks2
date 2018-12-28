package ua.edu.sumdu.j2se.Myropolska.Anna.tasks;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedTaskList extends TaskList {
    public Node node = null;

    private class Node {
        Task task;
        Node previous;
        Node next;
        int nodeIndex = 0;

        Node(Task task) {
            this.task = task;

        }
        Node (Task task, Node previous) {
            this.task = task;
            this.previous = previous;
        }
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            if (node == null) {
                node = new Node(task);
                node.previous = null;
                node.next = null;
                node.nodeIndex = size++;


            } else {

                Node newNode = new Node(task);
                newNode.previous = node;
                newNode.next = null;
                newNode.nodeIndex = size++;
                node.next = newNode;
                node = newNode;

            }
        } else throw new NullPointerException("Task cannot have null value");
    }

    @Override
    public boolean remove(Task task) {
        Node helper = node;
        boolean removed = false;
        for (int i = 0; i < size(); i++) {
            if (helper.task == task) {
                if (helper.previous != null) {
                    helper.previous.next = helper.next;
                }
                if (helper.next != null) {
                    helper.next.previous = helper.previous;
                    for (int j = helper.nodeIndex; j < size() - 1; j++){
                        helper.next.nodeIndex = j;
                        helper = helper.next;
                    }
                }
                else node = helper.previous;
                removed = true;
                size--;
                break;
            } else {
                helper = helper.previous;
            }
        }

        return removed;
    }

    @Override
    public Task getTask(int index) {
        Node helper = node;
        Task found = null;
        for (int i = 0; i < size(); i++) {
            if (helper.nodeIndex == index) {
                found = helper.task;
                break;
            } else {
                helper = helper.previous;

            }
        }
        return found;
    }

    @Override
    /*public TaskList createList(Iterable<Task> tasks, Date from, Date to) {
        TaskList incoming = (TaskList) tasks;
        for (int i = 0; i < size(); i++) {
            if (getTask(i).nextTimeAfter(from) != null) {
                if (to.after(getTask(i).nextTimeAfter(from)))
                    incoming.add(getTask(i));


            }
        }

        return incoming;

    }
    */
    public Iterator<Task> iterator() {
        return new Iterator <Task>() {
            public Node currentNode = new Node(null, node);
            boolean nextIsCalled = false;



            @Override
            public boolean hasNext() {
                if (currentNode.previous != null)
                    return true;
                else
                    return false;

            }

            @Override
            public Task next() {
                nextIsCalled = true;
                if (hasNext()) {
                    currentNode = currentNode.previous;
                    return currentNode.task;

                }
                else
                    throw new NoSuchElementException();
            }

                @Override
                public void remove() {
                if (nextIsCalled){
                    if (currentNode != null) {
                        LinkedTaskList.this.remove(currentNode.task);
                        if (currentNode.next != null)
                                currentNode = currentNode.next;

                        nextIsCalled = false;
                    }
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
        Iterator<Task> iterator1 = ((LinkedTaskList) o).iterator();
        Iterator<Task> iterator2 = this.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()){
            if(!iterator1.next().equals(iterator2.next()))
                isEqual = false;
        }
        return isEqual;
    }

    @Override
    public int hashCode() {

        return (int) (node.nodeIndex * node.task.getTime().getHours());
    }

    @Override
    public String toString() {
        return "LinkedTaskList {" + "size = " + size +
                '}';
    }
    @Override
    protected LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList cloned = new LinkedTaskList();
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext())
            cloned.add(iterator.next());
        return cloned;
    }
	
	
    /* public Iterator <Task> iterator() {
       return new Iterator <Task>() {
           public int current = -1;
           boolean nextIsCalled = false;

           @Override
           public boolean hasNext() {
               if (LinkedTaskList.this.size() - 1 > current)
                   return true;
               else
                   return false;
           }

           @Override
           public Task next() {
               nextIsCalled = true;
               if (hasNext()) {
                   return getTask(++current);
               }

               else
                   return null;

           }
           @Override
           public void remove() {
               if (nextIsCalled) {
                   LinkedTaskList.this.remove(getTask(current--));
                   nextIsCalled = false;
               }

               else
                   throw new IllegalStateException();
           }

       };
   }
   */

}

