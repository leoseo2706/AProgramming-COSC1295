package core.model;

import java.util.Objects;

public class FormedMember {
    private Student student;
    private String studentId;
    private int index;

    public FormedMember() {
    }

    public FormedMember(Student student, String studentId, int index) {
        this.student = student;
        this.studentId = studentId;
        this.index = index;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Student getStudent(){
        return student;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof FormedMember))
            return false;
        if (obj == this)
            return true;
        return this.getStudentId() == ((FormedMember) obj).getStudentId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId);
    }

    @Override
    public String toString() {
        return "FormedMember{" +
                "studentId='" + studentId + '\'' +
                ", index=" + index +
                '}';
    }
}
