package core.dao;

import core.model.Student;
import core.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class StudentDAO extends BaseDAO {

    private final String TABLE_NAME = "Student";
    private final String TABLE_KEY = "ID";

    public boolean insert(Student student) {

        Connection conn = null;
        try {
            String sql = "INSERT INTO Student (ID,GradeP,GradeN,GradeA,GradeW,Personality," +
                    "ConflictedStudent1,ConflictedStudent2) VALUES(?,?,?,?,?,?,?,?)";

            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getId());
            pstmt.setInt(2, student.getGradeP());
            pstmt.setInt(3, student.getGradeN());
            pstmt.setInt(4, student.getGradeA());
            pstmt.setInt(5, student.getGradeW());
            pstmt.setString(6, student.getPersonality());
            pstmt.setString(7, student.getcStudent1());
            pstmt.setString(8, student.getcStudent2());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(Utils.format("Error while inserting record: {0}",
                    e.getMessage()));
        } finally {
            close(conn);
        }

        return false;
    }

    public boolean update(Student student) {
        Connection conn = null;
        try {
            String sql = "UPDATE Student SET Personality = ?, ConflictedStudent1 = ?, ConflictedStudent2 =? "
                    + " WHERE ID = ?";
            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getPersonality());
            pstmt.setString(2, student.getcStudent1());
            pstmt.setString(3, student.getcStudent2());
            pstmt.setString(4, student.getId());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(Utils.format("Error while updating student record {0}: {1}",
                    student.getId(), e.getMessage()));
        } finally {
            close(conn);
        }

        return false;
    }

    public int countByPersonality(String personality) {
        return countBy(TABLE_NAME, "Personality", personality);
    }

    public int countAll() {
        return countAll(TABLE_NAME);
    }

    public boolean isExisted(String id) {
        return isExisted(TABLE_NAME, TABLE_KEY, id);
    }

    public boolean isExisted(List<String> ids) {
        return isExistedIn(TABLE_NAME, TABLE_KEY, ids);
    }

}
