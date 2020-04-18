package ua.nure.hrynko.walletservice.utils;

import ua.nure.hrynko.walletservice.entities.SonarIssue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static String SQL_LITE_PATH = "jdbc:sqlite:../gitExplorer/DB/technicalDebtDataset.db";

    public static synchronized List<String> getComponentsOfCommit(String commitHash) {
        List<String> components = new ArrayList<>();

        String sql = "SELECT DISTINCT component FROM COMMENTS_DISTINCT " +
                "WHERE addedCommitHash = \'" + commitHash + "\'";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(SQL_LITE_PATH);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String component = resultSet.getString("component");

                components.add(component);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
               // LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        return components;
    }

    public static synchronized void saveSonarIssues(List<SonarIssue> issues) {
        if (issues.size() > 0) {
            String sql = "INSERT INTO SONAR_ISSUES_PARSED (creationDate, updateDate, discoveredCommitHash, issueKey," +
                    " type, rule, component, severity, project, line, startLine, endLine, status, message, effort, debt) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = DriverManager.getConnection(SQL_LITE_PATH);
                pstmt = conn.prepareStatement(sql);
                for (int i = 0; i < issues.size(); i++) {
                    SonarIssue issue = issues.get(i);
                    pstmt.setLong(1, issue.getCreationDate());
                    pstmt.setLong(2, issue.getUpdateDate());
                    pstmt.setString(3, issue.getDiscoveredCommit());
                    pstmt.setString(4, issue.getKey());
                    pstmt.setString(5, issue.getType());
                    pstmt.setString(6, issue.getRule());
                    pstmt.setString(7, issue.getComponent());
                    pstmt.setString(8, issue.getSeverity());
                    pstmt.setString(9, issue.getProject());
                    pstmt.setInt(10, issue.getLine());
                    pstmt.setInt(11, issue.getStartLine());
                    pstmt.setInt(12, issue.getEndLine());
                    pstmt.setString(13, issue.getStatus());
                    pstmt.setString(14, issue.getMessage());
                    pstmt.setString(15, issue.getEffort());
                    pstmt.setString(16, issue.getDebt());

                    pstmt.addBatch();
                }

                pstmt.executeBatch();
            } catch (SQLException e) {
                //LOGGER.log(Level.INFO, e.getMessage(), e);

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    //LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        }
    }


    public static void main(String... args) {
        String projectName = "https://github.com/apache/ambari.git";
        getComponentsOfCommit("9f217483b44249e1b119d544545f7777cb95cf7b");
       //     getAllFilesChangedInCommit("60a05a806fc3203cda35cc3a6be3a5c713d18be3");
       // connectToDB();
      //  createTable();
    }
}
