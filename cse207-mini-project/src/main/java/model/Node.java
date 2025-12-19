package model;

public class Node {
    public Student student;
    public Node left, right;

    public Node(Student student) {
        this.student = student;
        left = right = null;
    }
}
