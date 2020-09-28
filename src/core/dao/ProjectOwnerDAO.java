package core.dao;

import core.model.ProjectOwner;
import core.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProjectOwnerDAO extends BaseDAO {

    public boolean insert(ProjectOwner owner) {

        Connection conn = null;
        try {
            String sql = "INSERT INTO ProjectOwner (ID,FirstName,Surname,Email,CompanyID,Role)" +
                    " VALUES(?,?,?,?,?,?)";

            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, owner.getId());
            pstmt.setString(2, owner.getfName());
            pstmt.setString(3, owner.getsName());
            pstmt.setString(4, owner.getEmail());
            pstmt.setString(5, owner.getCid());
            pstmt.setString(6, owner.getRole());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(Utils.format("Error while inserting record: {0}",
                    e.getMessage()));
        } finally {
            close(conn);
        }

        return false;
    }

    public boolean isExisted(String id) {
        return isExisted("ProjectOwner", "ID", id);
    }
}
