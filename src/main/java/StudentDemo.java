import comands.Commands;
import exception.LessonNotFoundException;
import model.Lesson;
import model.Role;
import model.Student;
import model.User;
import storage.LessonStorage;
import storage.StudentStorage;
import storage.UserStorage;

import java.util.Date;
import java.util.Scanner;

import static util.DateUtil.stringToDate;

public class StudentDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentStorage studentStorage = new StudentStorage();
    private static LessonStorage lessonStorage = new LessonStorage();
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;

    public static void main(String[] args) {
        initData();

        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case 0:
                    run = false;
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Invalid command: Please try again");
            }
        }
    }

    private static void initData() {
        User admin = new User("admin", "admin", "admin@mail.com", "admin", Role.ADMIN);
        userStorage.add(admin);
        Lesson java = new Lesson("java", "teacher name", 33, 55, stringToDate("03/06/2021"));
        lessonStorage.add(java);
        Lesson msql = new Lesson("msql", "teacher name2", 43, 65, stringToDate("14/06/2021"));
        lessonStorage.add(msql);
        Lesson js = new Lesson("js", "teacher name3", 53, 65, stringToDate("15/07/2022"));
        lessonStorage.add(js);

        studentStorage.add(new Student("poxos", "simonyan", 25, "25252552", "gyumri", java, admin, new Date()));
        studentStorage.add(new Student("simon", "poposyan", 28, "252999552", "erevan", msql, admin, new Date()));
        studentStorage.add(new Student("valod", "meschyan", 35, "257874552", "gavar", js, admin, new Date()));
    }

    private static void login() {
        System.out.println("Please input email,password");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        User user = userStorage.getUserEmail(emailPassword[0].trim());
        if (user == null) {
            System.out.println("user with" + emailPassword[0] + "does not exists");
        } else {
            if (user.getPassword().equals(emailPassword[1].trim())) {
                currentUser = user;
                if (user.getRole() == Role.ADMIN) {
                    adminLogin();
                } else if (user.getRole() == Role.USER) {
                    userLogin();
                }
            } else {
                System.out.println("Password is wrong");
            }
        }
    }

    private static void register() {
        System.out.println("Please input name, surname, email,password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct user data");
        } else {
            if (userStorage.getUserEmail(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("user register");
            } else {
                System.out.println("User with" + userData[2] + "already exists");
            }
        }
    }

    private static void userLogin() {
        System.out.println("Welcome" + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENT:
                    printAll();
                    break;
                case PRINT_STUDENTS_COUNT:
                    printCount();
                    break;
                case PRINT_STUDENT_BY_LESSON:
                    printStudentByLesson();
                    break;
                case PRINT_STUDENT_BY_AGE:
                    printStudentByAge();
                    break;
                case PRINT_ALL_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("invalid command: Please try again");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("Welcome" + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENT:
                    printAll();
                    break;
                case PRINT_STUDENTS_COUNT:
                    printCount();
                    break;
                case DELETE_STUDENT_BY_INDEX:
                    deleteByIndex();
                    break;
                case PRINT_STUDENT_BY_LESSON:
                    printStudentByLesson();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeStudentLesson();
                    break;
                case PRINT_STUDENT_BY_AGE:
                    printStudentByAge();
                    break;
                case CHANGE_PHONE_NUMBER:
                    changePhoneNumber();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_ALL_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("invalid command: Please try again");
            }
        }
    }

    private static void addLesson() {
        System.out.println("Please input lesson name");
        String name = scanner.nextLine();
        System.out.println("Please input lesson price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please input duration by mount");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.println("Please input teacher name");
        String teacherName = scanner.nextLine();
        System.out.println("Please input lesson startDate (14/04/2021)");
        String strData = scanner.nextLine();
        Lesson lesson = new Lesson(name, teacherName, duration, price, stringToDate(strData));
        lessonStorage.add(lesson);
        System.out.println("Lesson created!");
    }

    private static void changePhoneNumber() {
        printAll();
        System.out.println("Please choose index. If was wrong inputted command please input -1 for return");
        int index = Integer.parseInt(scanner.nextLine());
        if (index == -1) {
            return;
        }
        System.out.println("Please input phoneNumber");
        String phoneNumber = scanner.nextLine();
        if (!studentStorage.changePhoneNumber(index, phoneNumber)) {
            changePhoneNumber();
        }
    }


    private static void printStudentByAge() {
        System.out.println("Please input student age");
        int age = Integer.parseInt(scanner.nextLine());
        studentStorage.printByAge(age);
    }

    private static void addStudent() {
        if (lessonStorage.isEmpty()) {
            System.out.println("Please input lesson");
            addLesson();
        } else {
            lessonStorage.print();
            System.out.println("pLease choose lesson index");
            int lessonIndex = Integer.parseInt(scanner.nextLine());
            try {
                Lesson lesson = lessonStorage.getByIndex(lessonIndex);
                System.out.println("Please input student name");
                String name = scanner.nextLine();
                System.out.println("Please input student surname");
                String surname = scanner.nextLine();
                System.out.println("Please input student age");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Please input student phoneNumber");
                String phoneNumber = scanner.nextLine();
                System.out.println("Please input student city");
                String city = scanner.nextLine();

                Student student = new Student(name, surname, age, phoneNumber, city, lesson, currentUser, new Date());
                studentStorage.add(student);
                System.out.println("Student was added");
            } catch (LessonNotFoundException e) {
                System.out.println(e.getMessage());
                addStudent();
            }
        }
    }

    private static void printAll() {
        studentStorage.print();
    }

    private static void printCount() {
        System.out.println(studentStorage.getSize());
    }

    private static void deleteByIndex() {
        printAll();
        System.out.println("Please choose index for delete. If was wrong inputted command please input -1 for return");
        int index = Integer.parseInt(scanner.nextLine());
        if (index == -1) {
            return;
        }
        if (!studentStorage.delete(index)) {
            deleteByIndex();
        }
    }

    private static void printStudentByLesson() {
        System.out.println("Please input lesson name");
        String lesson = scanner.nextLine();
        studentStorage.printStudentByLesson(lesson);
    }

    private static void changeStudentLesson() {
        printAll();
        System.out.println("Please input student's index");
        int index = Integer.parseInt(scanner.nextLine());
        Student student = studentStorage.getByIndex(index);
        if (student != null) {
            lessonStorage.print();
            System.out.println("Please input lesson index");
            int lessonIndex = Integer.parseInt(scanner.nextLine());
            try {
                Lesson lesson = lessonStorage.getByIndex(lessonIndex);
                student.setLesson(lesson);
                System.out.println("Lesson changed");
            } catch (LessonNotFoundException e) {
                System.out.println("please input correct index");
                changeStudentLesson();
            }

        } else {
            System.out.println("Invalid index.Please try again!");
            changeStudentLesson();
        }
    }
}