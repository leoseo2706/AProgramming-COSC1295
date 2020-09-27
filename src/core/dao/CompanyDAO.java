package core.dao;

import core.model.Company;
import core.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CompanyDAO extends BaseDAO {

    public boolean insert(Company company) {

        Connection conn = null;
        try {
            String sql = "INSERT INTO Company (ID,Name,ABN,URL,Address) VALUES(?,?,?,?,?)";
            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, company.getId());
            pstmt.setString(2, company.getName());
            pstmt.setString(3, company.getAbn());
            pstmt.setString(4, company.getUrl());
            pstmt.setString(5, company.getAddress());
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
        return isExisted("Company", "ID", id);
    }

}
