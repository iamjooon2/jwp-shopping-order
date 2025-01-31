package cart.persistence.entity;

public class MemberEntity {

    private final Long id;
    private final String grade;
    private final String email;
    private final String password;

    public MemberEntity(final Long id, final String grade, final String email, final String password) {
        this.id = id;
        this.grade = grade;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getGrade() {
        return grade.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
