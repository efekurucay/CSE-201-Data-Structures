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
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    
    public double getGrade() {
        return grade;
    }

    
    public String getName() {
        return name;
    }

}

class StudentGrades  {
    private Student[] students;
    private int currentSize;

    public StudentGrades(int maxSize) {
        this.students = new Student[maxSize];
        this.currentSize = 0; 
    }

    public boolean addStudent(Student student) {
        if (currentSize >= students.length) {
            return false; 
        }

        int i = currentSize - 1;
        
        while (i >= 0 && students[i].getGrade() < student.getGrade()) {
            students[i + 1] = students[i]; 
            i--;
        }
        
        students[i + 1] = student;
        currentSize++;
        
        return true;
    }

    public boolean removeStudent(int index) {
        if (index < 0 || index >= currentSize) {
            return false; 
        }

        for (int i = index; i < currentSize - 1; i++) {
            students[i] = students[i + 1]; 
        }
        students[currentSize - 1] = null; 
        currentSize--;
        
        return true;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public Student[] getStudents() {
        return students;
    }
}