package core.dao;

import core.constant.Constants;
import core.model.Project;
import core.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends BaseDAO {

    private final String TABLE = "Project";
    private final String KEY = "ID";


    public ProjectDAO() {
        super();
    }

    public boolean insert(Project project) {

        Connection conn = null;
        try {
            String sql = "INSERT INTO Project (ID,Title,Description,OwnerID,SkillRankP," +
                    "SkillRankN,SkillRankA,SkillRankW,Active) VALUES(?,?,?,?,?,?,?,?,?)";

            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, project.getId());
            pstmt.setString(2, project.getTitle());
            pstmt.setString(3, project.getDescription());
            pstmt.setString(4, project.getOwnerId());
            pstmt.setInt(5, project.getSkillRankP());
            pstmt.setInt(6, project.getSkillRankN());
            pstmt.setInt(7, project.getSkillRankA());
            pstmt.setInt(8, project.getSkillRankW());
            pstmt.setInt(9, Constants.ACTIVE);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(Utils.format("Error while inserting record: {0}",
                    e.getMessage()));
            e.printStackTrace();
        } finally {
            close(conn);
        }

        return false;
    }

    public List<Project> findAllActive() {

        Connection conn = null;
        List<Project> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Project WHERE Active = ?";
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Constants.ACTIVE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Project(
                        rs.getString("ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("OwnerID"),
                        rs.getInt("SkillRankP"),
                        rs.getInt("SkillRankN"),
                        rs.getInt("SkillRankA"),
                        rs.getInt("SkillRankW")
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

    public boolean deactiveRows(List<String> projectIDs) {
        return deactiveRowsIn(TABLE, KEY, projectIDs);
    }

    public boolean isExisted(String id) {
        return isExisted(TABLE, KEY, id);
    }
}
