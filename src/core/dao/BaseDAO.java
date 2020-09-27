package core.dao;

import core.constant.Constants;
import core.exception.CustomException;
import core.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseDAO {

    public Connection connect() {
        try {
            String url = Utils.getTextProp(Constants.CONNECTION_KEY);
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(Utils.format("Error establishing connection {0}",
                    e.getMessage()));
        }
        return null;
    }

    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // logging
            }
        }
    }

    public int countAll(String table) {

        Connection conn = null;
        try {
            if (Utils.isBlank(table)) {
                throw new CustomException("Invalid statement");
            }

            String totalKey = "total";
            String sql = Utils.format("SELECT COUNT(*) AS {0} FROM {1}",
                    totalKey, table);
            conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getInt(totalKey);
        } catch (Exception e) {
            System.out.println(Utils.format("Error while counting table {0} - {1}",
                    table, e.getMessage()));
            e.printStackTrace();
        } finally {
            close(conn);
        }

        return Constants.INVALID;
    }

    public int countByIn(String table, String key, List<?> values) {

        Connection conn = null;
        try {
            if (Utils.isBlank(table) || Utils.isBlank(key) || Utils.isEmpty(values)) {
                throw new CustomException("Invalid statement");
            }

            String totalKey = "total";
            String tmp = values.stream().map(x -> Constants.QUESTION_MARK)
                    .collect(Collectors.joining(Constants.COMMA));
            String sql = Utils.format("SELECT COUNT(*) AS {0} FROM {1} WHERE {2} IN ( {3} )",
                    totalKey, table, key, tmp);

            conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++) {
                Object v = values.get(i);
                if (v instanceof Integer) {
                    stmt.setInt(i+1, (Integer) v);
                } else if (v instanceof String) {
                    stmt.setString(i+1, (String) v);
                }
            }
            ResultSet rs = stmt.executeQuery();
            int result = rs.getInt(totalKey);
            return result;
        } catch (Exception e) {
            System.out.println(Utils.format("Error while counting table {0} - {1}",
                    table, e.getMessage()));
        } finally {
            close(conn);
        }

        return Constants.INVALID;
    }

    public int countBy(String table, String key, Object value) {
        return countByIn(table, key, new ArrayList<Object>(){{
            add(value);
        }});
    }

    public boolean deactiveRowsIn(String table, String searchColumn,
                                  List<?> searchValues) {

        Connection conn = null;
        try {
            if (Utils.isBlank(table) || Utils.isBlank(searchColumn)
                    || Utils.isEmpty(searchValues)) {
                throw new CustomException("Invalid statement");
            }

            String tmp = searchValues.stream().map(x -> Constants.QUESTION_MARK)
                    .collect(Collectors.joining(Constants.COMMA));
            String sql = Utils.format("UPDATE {0} SET Active = ? WHERE {1} IN ( {2} )",
                    table, searchColumn, tmp);
            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Constants.DEACTIVATED);
            for (int i = 0; i < searchValues.size(); i++) {
                Object v = searchValues.get(i);
                if (v instanceof Integer) {
                    pstmt.setInt(i+2, (Integer) v);
                } else if (v instanceof String) {
                    pstmt.setString(i+2, (String) v);
                }
            }
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(
                    Utils.format("Errors while deactivating the rows: {0}",
                            e.getMessage()));
        } finally {
            close(conn);
        }

        return false;
    }

    public boolean deactiveRows(String table, String searchColumn, Object searchValue) {
        return deactiveRowsIn(table, searchColumn,
                new ArrayList<Object>(){{
            add(searchValue);
        }});
    }

    public boolean isExisted(String table, String key, Object value) {
        return countByIn(table, key, new ArrayList<Object>() {{
            add(value);
        }}) > 0;
    }

    public boolean isExistedIn(String table, String key, List<?> values) {
        return countByIn(table, key, values) > 0;
    }

}
