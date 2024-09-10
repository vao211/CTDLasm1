
public class Subject {

    private String sName;
    private int credit;
    private double grade;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Subject(String sName, int credit, double grade) {
        this.sName = sName;
        // this.sCode = sCode;
        this.credit = credit;
        this.grade = grade;
    }
}
