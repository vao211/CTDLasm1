
import java.util.List;

public class Student {

    private String name;
    private String code;
    private String mark;
    private double point;
    static int count = 0;
    private List<Subject> subjectList;

    public Student(String code, String name, String mark, List<Subject> subjectList) {
        this.name = name;
        this.code = code;
        this.mark = mark;
        this.subjectList = subjectList;
        calculatePoint();
    }

    private void calculatePoint() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < subjectList.size(); i++) {
            Subject subject = subjectList.get(i);
            totalPoints += subject.getGrade() * subject.getCredit();
            totalCredits += subject.getCredit();
        }
        if (totalCredits > 0) {
            point = totalPoints / totalCredits;
        } else {
            point = 0; // 0 nếu 0 tín
        }
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        if (point < 5) {
            mark = "F";
        } else if (point >= 5 && point < 8) {
            mark = "M";
        } else {
            mark = "D";
        }
        return mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjectList;
    }

    public double getPoint() {
        return point;
    }

    public String StudentToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nStudent code: ").append(code).append("\n")
                .append("Student name: ").append(name).append("\n")
                .append("Point: ").append(point).append("\n")
                .append("Student mark: ").append(getMark()).append("\n")
                .append("Subjects: \n");

        for (Subject subject : subjectList) {
            sb.append(" - ").append(subject.getsName())
                    .append(", Credit: ").append(subject.getCredit())
                    .append(", Grade: ").append(subject.getGrade())
                    .append("\n");
        }
        return sb.toString();
    }
}
