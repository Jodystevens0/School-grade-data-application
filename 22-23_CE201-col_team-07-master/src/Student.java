import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int regNo;
    private String course;
    private List<Grade> grades;

    public Student(int regNo, String course) {
        this.regNo = regNo;
        this.course = course;
        grades = new ArrayList<>();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public int getRegNo() {
        return regNo;
    }

    public String getCourse() {
        return course;
    }

    public static List<Student> loadStudents(String fileName) {
        ArrayList<Student> students = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            line = br.readLine();
            if (line == null)
                return students;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                int regNo = -1;
                String course = "";
                ArrayList<Grade> grades = new ArrayList<>();
                for (int i = 0; i < parts.length; i++) {
                    if (i == 0) {
                        regNo = Integer.parseInt(parts[i]);
                    }
                    else if (i == 1) {
                        course = parts[i];
                    }
                    else {
                        try {
                            grades.add(new Grade(Modules.fromIndex(i), Integer.parseInt(parts[i])));
                        } catch (NumberFormatException ex) {}
                    }
                }

                if (regNo != -1 && course != "") {
                    Student s = new Student(regNo, course);
                    s.setGrades(grades);

                    students.add(s);
                }
                else {
                    throw new IOException("Invalid student data format");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return students;
    }
}
