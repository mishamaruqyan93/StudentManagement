package comands;

public interface Commands {

    int LOGOUT = 0;
    int ADD_STUDENT = 1;
    int PRINT_ALL_STUDENT = 2;
    int PRINT_STUDENTS_COUNT = 3;
    int DELETE_STUDENT_BY_INDEX = 4;
    int PRINT_STUDENT_BY_LESSON = 5;
    int CHANGE_STUDENT_LESSON = 6;
    int PRINT_STUDENT_BY_AGE = 7;
    int CHANGE_PHONE_NUMBER = 8;
    int ADD_LESSON = 9;
    int PRINT_ALL_LESSONS = 10;

    int EXIT = 0;
    int LOGIN =1;
    int REGISTER = 2;

    static void printCommands() {
        System.out.println("Please input" + LOGOUT + " for logout");
        System.out.println("Please input" + ADD_STUDENT + "for add student");
        System.out.println("Please input" + PRINT_ALL_STUDENT + "for print all student");
        System.out.println("Please input" + PRINT_STUDENTS_COUNT + " for see student count");
        System.out.println("Please input" + DELETE_STUDENT_BY_INDEX + " for delete student by index");
        System.out.println("Please input" + PRINT_STUDENT_BY_LESSON + " for print student by lessonName");
        System.out.println("Please input" + CHANGE_STUDENT_LESSON + " for change student's lesson");
        System.out.println("Please input" + PRINT_STUDENT_BY_AGE + " for print student whose age is equal and high of inputted value");
        System.out.println("Please input" + CHANGE_PHONE_NUMBER + " for change for phoneNumber");
        System.out.println("please input" + ADD_LESSON + " for Add lesson");
        System.out.println("please input" + PRINT_ALL_LESSONS + " for  print All lesson");
    }

    static void printLoginCommands() {
        System.out.println("Please input" + EXIT + " for exit");
        System.out.println("Please input" + LOGIN + " for Login");
        System.out.println("Please input" + REGISTER + " for Register");

    }

    static void printUserCommands() {
        System.out.println("Please input" + LOGOUT + " for logout");
        System.out.println("Please input" + ADD_STUDENT + "for add student");
        System.out.println("Please input" + PRINT_ALL_STUDENT + "for print all student");
        System.out.println("Please input" + PRINT_STUDENTS_COUNT + " for see student count");
        System.out.println("Please input" + PRINT_STUDENT_BY_LESSON + " for print student by lessonName");
        System.out.println("please input" + PRINT_ALL_LESSONS + "for  print All lesson");
    }
}
