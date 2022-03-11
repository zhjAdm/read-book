package com.zhjAdm.ui;

import com.zhjAdm.util.SimpleConfigUtil;

import javax.swing.*;
import java.io.File;

public class ReadBookSettingForm {
    private JPanel panel1;
    private JTextField urlTextField;
    private JButton urlBtn;
    private JLabel urlLable;
    private JTextField lengthField;
    private JTextField rowField;
    private JTextField readTimeField;


    public ReadBookSettingForm() {
        // 给按钮添加一个选择文件的事件
        urlBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(panel1);
            File file = fileChooser.getSelectedFile();
            urlTextField.setText(file.getPath());
        });
        //初始化配置项
        String lengthFieldValue = SimpleConfigUtil.getString("length");
        lengthField.setText(lengthFieldValue);
        String filePath = SimpleConfigUtil.getString("filePath");
        urlTextField.setText(filePath);
        String row = SimpleConfigUtil.getString("row");
        rowField.setText(row);
        String readTime = SimpleConfigUtil.getString("readTime");
        readTimeField.setText(readTime);
    }

    public JComponent getComponent() {
        return panel1;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }
    public JTextField getLengthField() {
        return lengthField;
    }
    public JTextField getRowField() {
        return rowField;
    }
    public JTextField getReadTimeField() {
        return readTimeField;
    }
}
