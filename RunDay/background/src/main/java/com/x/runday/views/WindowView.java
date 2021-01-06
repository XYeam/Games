package com.x.runday.views;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.views
 * @ClassName: WindowView
 * @Author: Yao
 * @Description: 缓存加载界面：背景图片、进度条
 *                  动态加载过程--线程
 * @CreateDate: 2021/1/5 17:26
 * @Version:
 */
public class WindowView extends JFrame implements Runnable {

    private JLabel background;

    /**
     * 进度条
     */
    JProgressBar jProgressBar;

    /**
     * 创建一个线程并启动
     */
    public void start() {
        WindowView windowView = new WindowView();
        Thread thread = new Thread(windowView);
        thread.start();
        dispose();
    }

    public WindowView() {
        background = new JLabel(new ImageIcon("images/hbg.jpg"));
        //放在窗口上面
        this.add(BorderLayout.NORTH, background);

        jProgressBar = new JProgressBar();
        //加载以字符串形式呈现出来，0%
        jProgressBar.setStringPainted(true);
        jProgressBar.setBackground(Color.ORANGE);
        this.add(BorderLayout.SOUTH, jProgressBar);
        //大小 568 * 340
        this.setSize(568, 340);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setUndecorated(true);
        this.setIconImage(new ImageIcon("images/115.pan").getImage());
        this.setVisible(true);
    }

    @Override
    public void run() {
        int[] values = {0, 1, 3, 10, 23, 32, 40, 55, 66, 76, 86, 89, 95, 99, 99, 99, 200};
        for (int i = 0; i < values.length; i++) {
            jProgressBar.setValue(values[i]);
            //线程休眠
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                //@TODO auto-generated catch block
                ie.printStackTrace();
            }
        }
    }
}
