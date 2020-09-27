package core.dao;

import core.constant.Constants;
import core.exception.CustomException;
import core.model.StudentPreference;
import core.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class StudentPreferenceDAO extends BaseDAO {


    public boolean insert(StudentPreference preference) {

        boolean allInserted = true;
        Map<String, Integer> projectPreferences = preference.getPreferences();
        for (Map.Entry<String, Integer> entry : projectPreferences.entrySet()) {
            Connection conn = null;
            String studentID = preference.getsId();
            String projectID = entry.getKey();
            Integer scorePreference = entry.getValue();
            System.out.println(Utils.format("Inserting {0}, {1}, {2}",
                    studentID, projectID, scorePreference.toString()));
            try {
                String sql = "INSERT INTO StudentPreference (StudentID, ProjectID, PreferenceScore, Active)" +
                        " VALUES(?,?,?, ?)";
                conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, studentID);
                pstmt.setString(2, projectID);
                pstmt.setInt(3, scorePreference);
                pstmt.setInt(4, Constants.ACTIVE);
                pstmt.executeUpdate();
                System.out.println("Inserted project preference successfully!");

            } catch (Exception e) {
                System.out.println(Utils.format("Error while inserting record: {0}",
                        e.getMessage()));
                allInserted = false;
            } finally {
                close(conn);
            }
        }

        return allInserted;
    }

    public List<StudentPreference> findAllActive() {

        Connection conn = null;
        List<StudentPreference> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM StudentPreference WHERE Active = ?";
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Constants.ACTIVE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new StudentPreference(
                        rs.getString("StudentID"),
                        rs.getString("ProjectID"),
                        rs.getInt("PreferenceScore")
                ));
            }
        } catch (Exception e) {
            System.out.println(Utils.format("Error while selecting record: {0}",
                    e.getMessage()));
        } finally {
            close(conn);
        }

        return results;
    }

    public int countActive(String studentID) {

        Connection conn = null;
        try {
            if (Utils.isBlank(studentID)) {
                throw new CustomException("Invalid statement");
            }

            String totalKey = "total";
            String sql = Utils.format("SELECT COUNT(*) as {0} FROM StudentPreference" +
                    " WHERE StudentID = ? AND Active = ?", totalKey);
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentID);
            stmt.setInt(2, Constants.ACTIVE);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(totalKey);
        } catch (Exception e) {
            System.out.println(
                    Utils.format("Error while counting active rows for student ID {0}: {2}",
                            studentID, e.getMessage()));
        } finally {
            close(conn);
        }

        return Constants.INVALID;
    }

    public boolean deactiveRows(String studentID) {
        return deactiveRows("StudentPreference",
                "StudentID", studentID);
    }
}
