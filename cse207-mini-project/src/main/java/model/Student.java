package model;
public class Student {
    private String name;
    private int roll;
    private String course;
    private int totalMarks;
    private double cgpa;

    public Student(String name, int roll, String course, int totalMarks, double cgpa) {
        this.name = name;
        this.roll = roll;
        this.course = course;
        this.totalMarks = totalMarks;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }
    public int getRoll() {
        return roll;
    }
    public String getCourse() {
        return course;
    }
    public int getTotalMarks() {
        return totalMarks;
    }
    public double getCgpa() {
        return cgpa;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setRoll(int roll) {
        this.roll = roll;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
