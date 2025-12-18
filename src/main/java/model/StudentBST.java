package model;
import java.util.ArrayList;
import java.util.List;

public class StudentBST {
    private Node root;

    public boolean insert(Student s) {
        if (root == null) {
            root = new Node(s);
            return true;
        }
        return insertRec(root, s);
    }

    private boolean insertRec(Node current, Student s) {
        if (s.getRoll() == current.student.getRoll()) {
            return false;
        }
        if (s.getRoll() < current.student.getRoll()) {
            if (current.left == null) {
                current.left = new Node(s);
                return true;
            }
            return insertRec(current.left, s);
        } else {
            if (current.right == null) {
                current.right = new Node(s);
                return true;
            }
            return insertRec(current.right, s);
        }
    }

    public Student search(int roll) {
        return searchRec(root, roll);
    }

    private Student searchRec(Node current, int roll) {
        if (current == null) return null;
        if (roll == current.student.getRoll()) return current.student;
        return roll < current.student.getRoll()
                ? searchRec(current.left, roll)
                : searchRec(current.right, roll);
    }

    public void delete(int roll) {
        root = deleteRec(root, roll);
    }

    private Node deleteRec(Node root, int roll) {
        if (root == null)
            return root;
        if (roll < root.student.getRoll())
            root.left = deleteRec(root.left, roll);
        else if (roll > root.student.getRoll())
            root.right = deleteRec(root.right, roll);
        else {

            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            Node minRight = min(root.right);
            root.student = minRight.student;
            root.right = deleteRec(root.right, minRight.student.getRoll());
        }
        return root;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
            return node;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private void inOrder(Node node, List<Student> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.student);
            inOrder(node.right, list);
        }
    }
}
