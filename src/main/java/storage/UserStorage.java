package storage;

import model.User;

public class UserStorage {
    private User[] array;
    private int size;

    public UserStorage() {
        array = new User[10];
        size = 0;
    }

    public void add(User user) {
        if (size == array.length) {
            extend();
        }
        array[size++] = user;
    }

    public void add(int index, User user) {
        if (check(index)) {
            if (size == array.length) {
                extend();
            }
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = user;
        size++;
    }

    public void add(User... users) {
        for (int i = 0; i < users.length; i++) {
            add(users[i]);
        }
    }

    public User getUserEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }

    public User getFirstIndexByValue(User user) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == user) {
                return array[i];
            }
        }
        return null;
    }

    public void set(int index, User user) {
        if (check(index)) {
            array[index] = user;
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
            System.out.println("User was deleted");
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
        User[] temp = new User[array.length + 10];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }

    public void delete() {
        array = new User[10];
        size = 0;
    }

    public int getSize() {
        return size;
    }
}