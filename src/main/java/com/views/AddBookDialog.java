package main.java.com.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import main.java.com.service.*;
import main.java.util.ScreenSize;

public class AddBookDialog extends JDialog {
    final int width = 600;
    final int height = 450;

    public AddBookDialog(JFrame jFrame, String title, Boolean isMode) {
        super(jFrame, title, isMode);
        // 组装视图
        // 创建纵向容器
        Box vBox = Box.createVerticalBox();

        // 创建横向容器和创建username标签和输入框
        Box bookIdBox = Box.createHorizontalBox();
        JLabel bookIdLabel = new JLabel("图书编号: ");
        JTextField bookIdField = new JTextField();

        // 横向容器添加
        bookIdBox.add(bookIdLabel);
        bookIdBox.add(Box.createHorizontalStrut(20));
        bookIdBox.add(bookIdField);

        // 创建横向容器和创建username标签和输入框
        Box titleBox = Box.createHorizontalBox();
        JLabel titleLabel = new JLabel("图书名称: ");
        JTextField titleField = new JTextField();

        // 横向容器添加
        titleBox.add(titleLabel);
        titleBox.add(Box.createHorizontalStrut(20));
        titleBox.add(titleField);

        // 创建横向容器和创建password标签和输入框
        Box authorBox = Box.createHorizontalBox();
        JLabel authorJLabel = new JLabel("图书作者: ");
        JTextField authorField = new JTextField();

        // 横向容器添加
        authorBox.add(authorJLabel);
        authorBox.add(Box.createHorizontalStrut(20));
        authorBox.add(authorField);

        // 创建横向容器和创建password标签和输入框
        Box pressBox = Box.createHorizontalBox();
        JLabel pressJLabel = new JLabel(" 出 版 社 : ");
        JTextField pressField = new JTextField();

        // 横向容器添加
        pressBox.add(pressJLabel);
        pressBox.add(Box.createHorizontalStrut(20));
        pressBox.add(pressField);

        // 创建横向容器和创建password标签和输入框
        Box statusBox = Box.createHorizontalBox();
        JLabel statusJLabel = new JLabel("借阅状态: ");
        JTextField statusField = new JTextField();

        // 横向容器添加
        statusBox.add(statusJLabel);
        statusBox.add(Box.createHorizontalStrut(20));
        statusBox.add(statusField);

        // 创建横向容器和创建password标签和输入框
        Box curTimeBox = Box.createHorizontalBox();
        JLabel curTimeJLabel = new JLabel("借阅时间: ");
        JTextField curTimeField = new JTextField();

        // 横向容器添加
        curTimeBox.add(curTimeJLabel);
        curTimeBox.add(Box.createHorizontalStrut(20));
        curTimeBox.add(curTimeField);

        // 创建横向容器和创建password标签和输入框
        Box curStuIdBox = Box.createHorizontalBox();
        JLabel curStuIdJLabel = new JLabel("借阅学生: ");
        JTextField curStuIdField = new JTextField();

        // 横向容器添加
        curStuIdBox.add(curStuIdJLabel);
        curStuIdBox.add(Box.createHorizontalStrut(20));
        curStuIdBox.add(curStuIdField);

        // 创建横向容器和创建按钮
        Box bBox = Box.createHorizontalBox();
        JButton confirmButton = new JButton("确认");
        JButton cancelButton = new JButton("取消");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int isOk = JOptionPane.showConfirmDialog(null, "确认添加", "添加图书信息", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                // 如果确认要添加，需要获取数据
                // 调用entryBook方法，同时刷新表格。
                if (isOk == 0) {
                    String[] name = new String[] { "图书编号", "书名", "作者", "出版社", "借阅状态", "借阅学生", "借阅时间" };
                    Vector<String> columnName = new Vector<String>(List.of(name));
                    String bookId = bookIdField.getText();
                    String bookTitle = titleField.getText();
                    String author = authorField.getText();
                    String press = pressField.getText();
                    String temStatus = statusField.getText();
                    int status = 0;
                    if (!temStatus.equals("")) {
                        status = Integer.parseInt(temStatus);
                    }
                    // 处理输入的时间 可能是 " "、2021 12 14、2021-12-14、2021:12:14
                    String s = curTimeField.getText();
                    Timestamp curTime = null;
                    // 处理非法数据
                    if (bookId.equals("") || bookTitle.equals("") || author.equals("")
                            || press.equals("")) {
                        JOptionPane.showMessageDialog(null, "输入数据有误！");
                        return;
                    }
                    if (!s.equals("") && status != 0) {
                        boolean isTime = s.matches("([01]\\d|2[0-3])([:\\-]?)[0-5]\\d([:\\-]?)[0-5]\\d");
                        // 如果满足格式
                        if (isTime) {
                            s = s.replaceAll("\\D|\\|:", "-");
                            curTime = Timestamp.valueOf(s);
                        }
                    }
                    String stuID = curStuIdField.getText();
                    boolean isAdd = EnterBook.entryBook(bookId, bookTitle, author, press, status, curTime, stuID);
                    if (isAdd) {
                        JOptionPane.showMessageDialog(null, "添加成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败");
                    }
                } else {
                    return;
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 直接隐藏界面
                setVisible(false);
            }
        });

        // 横向容器添加
        bBox.add(confirmButton);
        bBox.add(Box.createHorizontalStrut(60));
        bBox.add(cancelButton);

        vBox.add(Box.createVerticalStrut(30));
        vBox.add(bookIdBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(titleBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(authorBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(pressBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(statusBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(curTimeBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(curStuIdBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(20));

        // 添加左右间隔
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(50));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(50));
        // 添加自定义容器
        this.add(hBox);
        this.setBounds((ScreenSize.getScreenWidth() - width) / 2, (ScreenSize.getScreenHeight() - height) / 2,
                width, height);
        this.setVisible(true);
    }
}
