package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Student;
import model.StudentBST;

public class MainController {

    // Top controls
    @FXML private ComboBox<String> comboCourse;
    @FXML private Label statuslbl;

    // Table
    @FXML private TableView<Student> tblView;
    @FXML private TableColumn<Student, Integer> rollColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> courseColumn;
    @FXML private TableColumn<Student, Double> cgpaColumn;
    @FXML private TableColumn<Student, Integer> marksColumn;

    // Form
    @FXML private TextField txtName;
    @FXML private TextField txtRoll;
    @FXML private ComboBox<String> txtCourse;
    @FXML private TextField txtMarks;

    // Buttons
    @FXML private Button add;
    @FXML private Button update;
    @FXML private Button delete;
    @FXML private Button search;

    private final StudentBST bst = new StudentBST();
    private Integer selectedRoll = null;

    private final ObservableList<String> courses =
            FXCollections.observableArrayList("CSE106", "CSE110", "CSE103", "CSE207", "CSE302");

    @FXML
    public void initialize() {

        rollColumn.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        cgpaColumn.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("totalMarks"));

        comboCourse.getItems().add("All");
        comboCourse.getItems().addAll(courses);
        comboCourse.setValue("All");

        txtCourse.setItems(courses);

        comboCourse.setOnAction(e -> {
            if (comboCourse.getValue().equals("All")) {
                showAllStudents();
                statuslbl.setText("Showing all students");
            } else {
                filterByCourse(comboCourse.getValue());
                statuslbl.setText("Filtered by " + comboCourse.getValue());
            }
        });

        showAllStudents();
    }

    @FXML
    void handleAddAction() {
        try {
            int roll = Integer.parseInt(txtRoll.getText().trim());
            int marks = Integer.parseInt(txtMarks.getText().trim());

            if (marks < 1 || marks > 100) {
                statuslbl.setText("Marks must be between 1 and 100");
                return;
            }

            Student s = new Student(
                    txtName.getText().trim(),
                    roll,
                    txtCourse.getValue(),
                    marks,
                    calculateCGPA(marks)
            );

            if (!bst.insert(s)) {
                statuslbl.setText("Roll already exists");
                return;
            }

            statuslbl.setText("Student added");
            clearForm();
            showAllStudents();

        } catch (Exception e) {
            statuslbl.setText("Invalid input");
        }
    }

    @FXML
    void handleUpdateAction() {
        if (selectedRoll == null) {
            statuslbl.setText("Select a row first");
            return;
        }

        try {
            int marks = Integer.parseInt(txtMarks.getText().trim());

            if (marks < 1 || marks > 100) {
                statuslbl.setText("Marks must be between 1 and 100");
                return;
            }

            Student s = bst.search(selectedRoll);
            if (s == null) return;

            s.setName(txtName.getText().trim());
            s.setCourse(txtCourse.getValue());
            s.setTotalMarks(marks);
            s.setCgpa(calculateCGPA(marks));

            statuslbl.setText("Updated successfully");
            clearForm();
            showAllStudents();
            tblView.refresh();

        } catch (Exception e) {
            statuslbl.setText("Update failed");
        }
    }

    @FXML
    void handleDeleteAction() {
        Student s = tblView.getSelectionModel().getSelectedItem();
        if (s == null) {
            statuslbl.setText("Select a row");
            return;
        }

        bst.delete(s.getRoll());
        statuslbl.setText("Deleted");
        clearForm();
        showAllStudents();
    }

    @FXML
    void handleSearchAction() {
        String key = txtName.getText().trim().toLowerCase();
        ObservableList<Student> result = FXCollections.observableArrayList();

        for (Student s : bst.getAllStudents()) {
            if (s.getName().toLowerCase().contains(key)) {
                result.add(s);
            }
        }

        tblView.setItems(result);
        statuslbl.setText(result.size() + " found");
    }

    @FXML
    void handleViewAction() {
        comboCourse.setValue("All");
        showAllStudents();
        statuslbl.setText("Showing all students");
    }

    @FXML
    void handleTableClick(MouseEvent e) {
        Student s = tblView.getSelectionModel().getSelectedItem();
        if (s == null) return;

        txtRoll.setText(String.valueOf(s.getRoll()));
        txtName.setText(s.getName());
        txtCourse.setValue(s.getCourse());
        txtMarks.setText(String.valueOf(s.getTotalMarks()));

        selectedRoll = s.getRoll();
        txtRoll.setEditable(false);
    }

    @FXML
    void handleClearAction() {
        clearForm();
        tblView.getSelectionModel().clearSelection();
        statuslbl.setText("Cleared");
    }

    private void clearForm() {
        txtName.clear();
        txtRoll.clear();
        txtMarks.clear();
        txtCourse.getSelectionModel().clearSelection();
        txtRoll.setEditable(true);
        selectedRoll = null;
    }

    private void showAllStudents() {
        tblView.setItems(FXCollections.observableArrayList(bst.getAllStudents()));
    }

    private void filterByCourse(String course) {
        ObservableList<Student> list = FXCollections.observableArrayList();
        for (Student s : bst.getAllStudents()) {
            if (s.getCourse().equals(course)) list.add(s);
        }
        tblView.setItems(list);
    }

    private double calculateCGPA(int marks) {
        if (marks >= 80) return 4.00;
        if (marks >= 75) return 3.75;
        if (marks >= 70) return 3.50;
        if (marks >= 65) return 3.25;
        if (marks >= 60) return 3.00;
        if (marks >= 55) return 2.75;
        if (marks >= 50) return 2.50;
        if (marks >= 45) return 2.25;
        if (marks >= 40) return 2.00;
        return 0.00;
    }
}
