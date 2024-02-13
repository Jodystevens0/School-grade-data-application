public class Grade {
    private Modules module;
    private int grade;

    public Grade(Modules module, int grade) {
        this.module = module;
        this.grade = grade;
    }

    public Modules getModule() {
        return module;
    }

    public int getGrade() {
        return grade;
    }
}
