package com.company;

import java.sql.*;
import java.util.Scanner;

public class Teacher {
    static Connection connection;
    static void teacher() {


        System.out.println("""
                ===== Action =====
                1.	Show disciplines list
                2.	Show student’s grades list
                3.	Show tasks
                4.	Add exams list
                5.	Add offsets list
                6.  Show offsets
                7.  Give marks
                8.	Exit""");
        Scanner student = new Scanner(System.in);
        int choose = student.nextInt();
        if (choose == 1) {
            select();
            teacher();
        }
        if (choose == 2) {
            selectAverage();
            teacher();
        }
        if (choose == 3) {
            task();
            teacher();
        }
        if (choose == 4) {
            add_exam();
            teacher();
        }
        if (choose == 5){
            addScore();
            teacher();
        }
        if (choose == 6) {
            scoreCheck();
            teacher();
        }
        if (choose == 7){
            marks();
            teacher();
        }
        if (choose == 8) {
            Main.enter_users();
        }
    }
    public static void select() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT students, Math2, English2, SoftwareEngineering, Logic, German FROM items ")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,Math2,German;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        String name1 = rs.getString("students");
                        System.out.println(name1 + "\n" + "Math2: " + Math2 + "\n" + "English: " + English2 + "\n" +
                                "SoftwareEngineering: " + SoftwareEngineering + "\n" +  "Logic: " + Logic + "\n" +
                                "German: " + German +"\n");

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

            System.out.println("Extension"+e);
        }
    }

    public static void selectAverage() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT students, Math2, English2, " +
                    "SoftwareEngineering, Logic, German FROM items")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        double Math2,English2,SoftwareEngineering,Logic,German;
                        Math2 = getAverage(rs.getString("Math2"));
                        English2 = getAverage(rs.getString("English2"));
                        SoftwareEngineering = getAverage(rs.getString("SoftwareEngineering"));
                        Logic = getAverage(rs.getString("Logic"));
                        German = getAverage(rs.getString("German"));
                        String name1 = rs.getString("students");
                        System.out.println(name1 + "\n" + "Math1: " + String.format("%.1f",Math2) + "\n" + "English: " +
                                String.format("%.1f",English2) + "\n" + "SoftwareEngineering: "
                                + String.format("%.1f",SoftwareEngineering) + "\n" +  "Logic: " + String.format("%.1f",Logic)
                                + "\n" + "German: " + String.format("%.1f",German)+"\n");
                    }
                    if (rs.next()){
                        isUserExist = true;
                    }
                }
            }
            if (isUserExist){
                System.out.println("problem");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void task() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner check = new Scanner(System.in);
            System.out.println("Enter date: ");
            int date = check.nextInt();
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT date, Math2, English2," +
                    " SoftwareEngineering, Logic, German FROM task WHERE date = " + date + "")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,Math2,German;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        int date1 = rs.getInt("date");
                        System.out.println("Tasks for "+ date1 +" date "  + "\n" + "Math2: " + Math2 + "\n" +
                                "English: " + English2 + "\n" + "SoftwareEngineering: " + SoftwareEngineering + "\n" +
                                "Logic: " + Logic + "\n" + "German: " + German);
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

    public static void add_exam() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter subject :");
            String subject = scanner.nextLine();
            System.out.println("Enter date :");
            String date = scanner.nextLine();
            System.out.println("Enter time");
            String time = scanner.nextLine();
            String query = "INSERT INTO exam ("+subject+", date) VALUES('" + time + "'," + date + ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Exam added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void addScore() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter subject :");
            String subject = scanner.nextLine();
            System.out.println("Enter date :");
            String date = scanner.nextLine();
            System.out.println("Enter time");
            String time = scanner.nextLine();
            String query = "INSERT INTO score ("+subject+", date) VALUES('" + time + "'," + date + ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Mark added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void scoreCheck() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT date, Math2, English2," +
                    " SoftwareEngineering, Logic, German FROM score ")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,German,Math2;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        int date1 = rs.getInt("date");
                        System.out.println("Offsets timesheet for "+ date1 +" date"  + "\n" + "Math2: " + Math2 +
                                "\n" + "English: " + English2 + "\n" + "SoftwareEngineering: " + SoftwareEngineering +
                                "\n" +  "Logic: " + Logic + "\n" + "German: " + German +  "\n" );
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

    public static void marks() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            String name,marks,subject;
            System.out.print("Enter students name: ");
            name = scanner.nextLine();
            System.out.println("""
                    Enter subject
                    1.Math2
                    2.English2
                    3.SoftwareEngineering
                    4.Logic
                    5.German""");
            subject = scanner.nextLine();
            System.out.print("Enter marks: ");
            marks = scanner.nextLine();
            String query = "UPDATE items SET "+ subject +" = "+ subject +" || '," + marks + "' WHERE students = '" +
                    name + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    static double getAverage(String marksStr) {

        String[] marks = marksStr.split(",");
        double average = 0;
        for (String mark : marks) {
            average += Double.parseDouble(mark);
        }
        return average / marks.length;
    }
}
