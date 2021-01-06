package com.x.runday.model;

import com.x.runday.views.GameView;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.model
 * @ClassName: Barrier_Missile
 * @Author: Yao
 * @Description: 障碍物
 * @CreateDate: 2021/1/6 10:36
 * @Version:
 */
@Data
public class Barrier_Missile {

    private Image image;

    private Image[] images;

    private int x;

    private int y;

    private int speed;

    public static final int WIDTH = 150;

    public static final int HEIGHT = 70;

    public Barrier_Missile() {
        try {
            image = ImageIO.read(new File("images/daodan.pan"));
        } catch (Exception e) {
            //@TODO handle exception
            e.printStackTrace();
        }
        x = GameView.WIDTH + 1000;
        y = 450;
        speed = 25;
    }

    public void step() {
        x -= speed;
    }

    public void paintBarrier(Graphics graphics) {
        graphics.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}
