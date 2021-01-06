package com.x.runday.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.views
 * @ClassName: RunDayView
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/5 9:44
 * @Version:
 */
public class RunDayLoginFrameView extends JFrame {
    /**
     * 用户名变量-文本
     */
    private JLabel userLabel;

    /**
     * 用户名输入框-文本输入框
     */
    JTextField userField;

    /**
     * 密码变量-文本
     */
    private JLabel passwordLabel;

    /**
     * 密码输入框-文本输入框
     */
    private JPasswordField passwordField;

    /**
     * 登录按钮、取消按钮--按钮
     */
    private JButton login,cancel;

    /**
     *
     */
//    private Logger logger = LoggerFactory.getLogger(RunDayLoginFrameView.class);

    /**
     * 无参构造
     */
    public RunDayLoginFrameView() {
        userLabel = new JLabel("用户名");
        //设置字体
        userLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        passwordLabel = new JLabel("密 码");
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        //布局方式：绝对布局
        //x、y，显示所占空间的大小
        userLabel.setBounds(20, 220, 100, 30);
        //将文字添加到登录界面上
        this.add(userLabel);
        passwordLabel.setBounds(20, 280, 100, 30);
        this.add(passwordLabel);
        //用户名输入框
        userField = new JTextField();
        userField.setBounds(80, 220, 100, 30);
        //设置输入框凹陷效果
        userField.setBorder(BorderFactory.createLoweredBevelBorder());
        //设置输入框背景透明
        userField.setOpaque(false);
        this.add(userField);

        passwordField = new JPasswordField();
        passwordField.setBounds(80, 280, 100, 30);
        passwordField.setBorder(BorderFactory.createLoweredBevelBorder());
        passwordField.setOpaque(false);
        this.add(passwordField);

        //登录按钮
        login = new JButton("登 录");
        login.setBounds(45, 350, 75, 36);
        //背景色
        //login.setBackground(new Color(44, 22, 44));
        //前景色
        //login.setForeground(Color.BLUE);
        //绑定登录按钮的事件监听
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Loading.......");
                //获取用户名输入框内容
                String userName = userField.getText();
                String password = passwordField.getText();
                if ("xxx".equals(userName) && "123456".equals(password)) {
                    //登录成功
                    JOptionPane.showMessageDialog(null, "welcome " + userName + " to RunDay");
                    //跳转到下一个界面
                    new MainFrame();
                    //关闭当前界面
                    dispose();
                } else if ("".equals(userName) || "".equals(password)) {
                    //
                    JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
                }else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误");
                }
            }
        });
        this.add(login);

        //取消按钮
        cancel = new JButton("取消");
        cancel.setBounds(135, 350, 75, 36);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO Auto-generated method stub
                dispose();
            }
        });
        this.add(cancel);

        //创建本经面板，并添加到窗体上
        LoginPanel loginPanel = new LoginPanel();
        this.add(loginPanel);

        //社会登录界面的基本属性
        this.setSize(900, 530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        //设置窗体的logo图标
        this.setIconImage(new ImageIcon("images/115.png").getImage());
        this.setVisible(true);
    }

    /**
     * 面板
     */
    class LoginPanel extends JPanel {
        //背景图片变量
        Image background;

        public LoginPanel() {
            //读取图片文件，赋值给background变量
            try {
                background = ImageIO.read(new File("images/login.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //绘制方法

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //绘制背景图片
            g.drawImage(background, 0, 0, 900, 530, null);
        }
    }

    public static void main(String[] args) {
        new RunDayLoginFrameView();
    }

}
