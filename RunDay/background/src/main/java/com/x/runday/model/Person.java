package com.x.runday.model;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.x.runday.views.GameView;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.model
 * @ClassName: Person
 * @Author: Yao
 * @Description: player
 * @CreateDate: 2021/1/6 9:23
 * @Version: 0.0.1
 */
@Data
public class Person {

    /**
     * 玩家当前显示的图片
     */
    private Image image;

    /**
     * 玩家所有图片
     */
    private Image[] images;

    /**
     * 玩家宽高
     */
    public static final int WIDTH = 120;

    public static final int HEIGHT = 120;

    /**
     * 玩家初始位置坐标
     */
    private int x;

    private int y;

    /**
     * 下面用作切换图片
     */
    private int index;

    /**
     * 玩家得分
     */
    private int score;

    /**
     * 玩家跑酷距离
     */
    private int distance;

    public Person() {
        init();
        //默认当前显示图片位第一张图片
        image = images[0];

        x = 90;
        y = 580;
        index = 0;
        score = 0;
        distance = 0;
    }

    /**
     * init
     */
    private void init() {
        images = new Image[9];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("images/" + (i + 1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean outOfBounds() {
        return (this.x >= GameView.WIDTH || this.x <= -WIDTH);
    }

    /**
     * 玩家移动的方法
     */
    public void step() {
        //玩家图片的切换
        image = images[index++ / 3 % images.length];
        //玩家坐标改变-玩家坐标通过键盘控制，此次不做处理
    }

    /**
     * 绘制玩家
     * @param graphics
     */
    public void paintPerson(Graphics graphics) {
        graphics.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * 玩家自由下落方法
     */
    public void drop() {
        y += 5;
        //下落，也不能踩破了地板
        if (y >= 580) {
            y = 580;
        }
    }
}
