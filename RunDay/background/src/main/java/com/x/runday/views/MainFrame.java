package com.x.runday.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.views
 * @ClassName: MainFrame
 * @Author: Yao
 * @Description:
 * 设置窗体的基本属性、大小
 * 1、1 设置窗体基本属性：大小、居中、边框隐藏、默认关闭按钮、logo 图标
 * 1、2 创建背景面板MainPanel，实现背景图片功能
 * 2、图片按钮功能
 * 2、1 创建开始按钮、帮助按钮、离开按钮，组件
 *
 *
 * @CreateDate: 2021/1/5 16:18
 * @Version:
 */
public class MainFrame extends JFrame implements MouseListener {

    private JLabel start,help,exit;

    private JPanel MainPanel;

    public MainFrame() {
        start = new JLabel(new ImageIcon("images/hh1.png"));
        start.setBounds(350, 320, 150, 40);
        start.setEnabled(false);
        start.addMouseListener(this);
        this.add(start);

        help = new JLabel(new ImageIcon("images/hh2.png"));
        help.setBounds(350, 420, 150, 40);
        help.setEnabled(false);
        help.addMouseListener(this);
        this.add(help);

        exit = new JLabel(new ImageIcon("images/hh3.png"));
        exit.setBounds(350, 520, 150, 40);
        exit.setEnabled(false);
        exit.addMouseListener(this);
        this.add(exit);

        //实现背景图片及窗体属性
        MainFrame.MainPanel mainPanel = new MainPanel();
        this.add(mainPanel);

        //设置窗体基本属性大小、居中、边框隐藏、默认关闭按钮、logo图标
        // 大小
        this.setSize(1200, 730);
        //居中
        this.setLocationRelativeTo(null);
        //边框隐藏
        this.setUndecorated(true);
        //默认关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //logo
        this.setIconImage(new ImageIcon("images/115.png").getImage());
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //鼠标点击
        if (e.getSource().equals(start)) {
            //跳转到下一个界面
            new WindowView().start();
            //关闭当前界面
            //dispose();
        } else if (e.getSource().equals(exit)) {
            dispose();
        } else if (e.getSource().equals(help)) {
            JOptionPane.showMessageDialog(null, "Contact the developer if you have any questions: XY");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //@TODO auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //@TODO auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //鼠标移入
        if (e.getSource().equals(start)) {
            //如果鼠标移入到start组件，图片按钮
            start.setEnabled(true);
        } else if (e.getSource().equals(help)) {
            help.setEnabled(true);
        } else if (e.getSource().equals(exit)) {
            exit.setEnabled(true);
        }
    }

    /**
     * 鼠标移出
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(start)) {
            start.setEnabled(false);
        } else if (e.getSource().equals(help)) {
            help.setEnabled(false);
        } else if (e.getSource().equals(exit)) {
            exit.setEnabled(false);
        }
    }

    /**
     * 创建背景面板MainPanel，实现背景图片功能
     */
    class MainPanel extends JPanel{
        Image background;

        public MainPanel() {
            try {
                background = ImageIO.read(new File("images/main.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            graphics.drawImage(background, 0, 0, 1200, 730, null);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
