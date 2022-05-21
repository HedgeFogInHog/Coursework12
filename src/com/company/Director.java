package com.company;

import java.sql.*;
import java.util.Scanner;

public class Director {
    static Connection connection;
    static void director() {


        System.out.println("""
                ===== Action =====
                1.	Show disciplines list
                2.	Show Teachers list
                3.	Add Teacher
                4.	Delete Teacher
                5.	Add Student
                6.	Delete Student
                7.	Exit""");
        Scanner student = new Scanner(System.in);
        int choose = student.nextInt();
        if (choose == 1) {
            allSubject();
            director();
        }
        if (choose == 2) {
            checkTeacher();
            director();
        }
        if (choose == 3) {
            registerTeachers();
            director();
        }
        if (choose == 4) {
            deleteTeachers();
            director();
        }
        if (choose == 5){
            registerStudents();
            director();
        }if (choose == 6){
            deleteStudent();
            director();
        }
        if (choose == 7) {
            Main.enter_users();
        }
    }

    public static void allSubject(){
        System.out.println("""
                1.Math2
                2.English
                3.SoftwareEngineering
                4.Logic
                5.German
                """);
    }
    public static void checkTeacher() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT name FROM users WHERE role = 2")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String teacher = rs.getString("name");
                        System.out.println(teacher);
                    }
                    if (rs.next()){
                        isUserExist = true;
                    }
                }
            }
            if (isUserExist){
                System.out.println();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void registerTeachers() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name :");
            String name = scanner.nextLine();
            System.out.println("Enter password :");
            String password = scanner.nextLine();
            String query = "INSERT INTO users (name, password,role) VALUES('" + name + "','" + password + "',2);";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Teacher added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteTeachers() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            String query = "DELETE  FROM users WHERE name = '"+name+"'AND role = 2";
            String query1 = "DELETE  FROM items WHERE name = '"+name+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(query1);
            System.out.println("Teacher deleted ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void registerStudents() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name :");
            String name = scanner.nextLine();
            System.out.println("Enter password :");
            String password = scanner.nextLine();
            String query = "INSERT INTO users (name, password,role) VALUES('" + name + "','" + password + "',1);";
            String query1 = "INSERT INTO items(students) VALUES('"+name+"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(query1);
            System.out.println("Student added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteStudent() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            String query = "DELETE  FROM users WHERE name = '"+name+" 'AND role = 1";
            String query1 = "DELETE  FROM items WHERE name = '"+name+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(query1);
            System.out.println("Student deleted ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
