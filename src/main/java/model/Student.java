package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private String city;
    private Lesson lesson;
    private User registerUser;
    private Date registerDate;

}
