package storage;

import exception.LessonNotFoundException;
import model.Lesson;

public class LessonStorage {
    private Lesson[] array;
    private int size;

    public LessonStorage() {
        array = new Lesson[10];
        size = 0;
    }

    public void add(Lesson value) {
        if (size == array.length) {
            extend();
        }
        array[size++] = value;
    }

    public void add(int index, Lesson value) {
        if (check(index)) {
            if (size == array.length) {
                extend();
            }
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    public void add(Lesson... students) {
        for (int i = 0; i < students.length; i++) {
            add(students[i]);
        }
    }

    public Lesson getByIndex(int index) throws LessonNotFoundException {
        if (check(index)) {
            return array[index];
        }else {
            throw  new LessonNotFoundException("Lesson with " + index + "index does not exists");
        }
    }

    public Lesson getFirstIndexByValue(Lesson value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return array[i];
            }
        }
        return null;
    }

    public void set(int index, Lesson value) {
        if (check(index)) {
            array[index] = value;
        } else {
            System.out.println("IndexOutOfBoundsException");
        }
    }

    public boolean delete(int index) {
        if (check(index)) {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            size--;
            System.out.println("Lesson was deleted");
            return true;
        }
        System.out.println("IndexOutOfBoundsException");
        System.out.println("Please try again");
        return false;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ". " + array[i] + " ");
            System.out.println();
        }
    }

    private boolean check(int index) {
        if (index >= size || index < 0) {
            return false;
        }
        return true;
    }

    private void extend() {
        Lesson[] temp = new Lesson[array.length + 10];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void delete() {
        array = new Lesson[10];
        size = 0;
    }

    public int getSize() {
        return size;
    }
}