public class Lab01 {
        public static void main(String[] args) {

    }
}
interface IStudent {
    double getGrade();
    String getName();
}

interface IStudentGrades {
    boolean addStudent(IStudent student); // method should insert given student into its sorted order.
    boolean removeStudent(int index); // method should remove the given index, students should stay sorted, shift if necessary
    int getCurrentSize(); // changes according to remaining students
    IStudent[] getStudents();
}

// •	Implement given interfaces on corresponding Student and StudentGrades classes.
// Should not remove student if no student exists in given index
// Should not add if StudentGrades is full
// StudentGrades should take int size as paramter in its constructor
class Student {

}

class StudentGrades {

}