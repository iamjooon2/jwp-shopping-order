package cart.application.domain;

public class Member {

    private final Long id;
    private final String email;
    private final String password;
    private final Grade grade;

    public Member(Long id, String grade, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = Grade.from(grade);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isNotSamePassword(String password) {
        return !this.password.equals(password);
    }

    public Grade getGrade() {
        return grade;
    }

    public String getGradeName() {
        return grade.getName();
    }
}
