package main.java.com.service;

import java.sql.*;
import java.time.LocalDateTime;

import main.java.Setting;

public class EnterBook {
    private static Connection conn = Setting.conMySql();

    public static boolean entryBook(String bookId, String bookTitle, String author, String press, int status
    , Timestamp curTime, String userId) {
        PreparedStatement pstmt = null;
        // 判断借阅状态
        if (status == 0){
            curTime = null;
            userId = null;
        }
        int rows = 0;
        try {
            String sql = "INSERT INTO book(bookId,bookTitle,author,press,status,curTime,userId) VALUES(?,?,?,?,?,?,?)";
            // 开始录入
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,bookId);
            pstmt.setString(2, bookTitle);
            pstmt.setString(3, author);
            pstmt.setString(4, press);
            pstmt.setInt(5, status);
            pstmt.setTimestamp(6, curTime);
            pstmt.setString(7,userId);
            rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
}
