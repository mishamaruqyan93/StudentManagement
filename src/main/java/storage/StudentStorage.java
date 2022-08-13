package storage;

import model.Student;

public class StudentStorage {
    private Student[] array;
    private int size;

    public StudentStorage() {
        array = new Student[10];
        size = 0;
    }

    public void add(Student value) {
        if (size == array.length) {
            extend();
        }
        array[size++] = value;
    }

    public void add(int index, Student value) {
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

    public void add(Student... students) {
        for (int i = 0; i < students.length; i++) {
            add(students[i]);
        }
    }

    public Student getByIndex(int index) {
        if (check(index)) {
            return array[index];
        }
        return null;
    }

    public Student getFirstIndexByValue(Student value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return array[i];
            }
        }
        return null;
    }

    public void set(int index, Student value) {
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
            System.out.println("Student was deleted");
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
        Student[] temp = new Student[array.length + 10];
        System.arraycopy(array,0,temp,0,array.length);
        array = temp;
    }

    public void delete() {
        array = new Student[10];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void printStudentByLesson(String lesson) {
        for (int i = 0; i < size; i++) {
            if (array[i].getLesson().getName().equals(lesson)) {
                System.out.println(array[i]);
            }
        }
    }

    public void printByAge(int age) {
        for (int i = 0; i < size; i++) {
            if (array[i].getAge() >= age) {
                System.out.println(array[i]);
            }
        }
    }

    public boolean changePhoneNumber(int index, String phoneNumber) {
        Student student = getByIndex(index);
        if (student != null) {
            student.setPhoneNumber(phoneNumber);
            System.out.println("PhoneNumber was changed");
            return true;
        }
        System.out.println("IndexOutOfBoundsException");
        System.out.println("Please try again");
        return false;
    }
}