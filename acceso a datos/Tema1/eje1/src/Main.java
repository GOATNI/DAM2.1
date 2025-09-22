import modelo.Student;
import modelo.Teacher;
import modelo.TeacherNameComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(4,"j");
        Student student2 = new Student(2,"a");
        Student student3 = new Student(1,"e");
        Student student4 = new Student(3,"d");

        List<Student> Students = new ArrayList<>();
        Students.add(student1);
        Students.add(student2);
        Students.add(student3);
        Students.add(student4);
        Collections.sort(Students);

        System.out.println(Students);


        Teacher teacher1 = new Teacher(1,"Juan");
        Teacher teacher2 = new Teacher(2,"diego");
        Teacher teacher3 = new Teacher(3,"fico");
        Teacher teacher4 = new Teacher(4,"tren");
        List<Teacher> teachers;
        teachers = new ArrayList<>(List.of(teacher1,teacher2,teacher3,teacher4));

        Collections.sort(teachers,new TeacherNameComparator());
        System.out.println(teachers);

    }
}
