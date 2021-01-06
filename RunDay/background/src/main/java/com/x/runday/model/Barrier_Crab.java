package com.x.runday.model;

import com.x.runday.views.GameView;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.model
 * @ClassName: Barrier
 * @Author: Yao
 * @Description: 障碍物
 * @CreateDate: 2021/1/6 10:07
 * @Version:
 */
@Data
public class Barrier_Crab {

    private Image image;

    private Image[] images;

    public static final int WIDTH = 100;

    public static final int HEIGHT  = 110;

    private int x;

    private int y;

    private int index;

    private int speed;

    public Barrier_Crab() {
        images = new Image[2];
        try {
            images[0] = ImageIO.read(new File("images/a2.png"));
            images[1] = ImageIO.read(new File("images/a4.png"));
        } catch (Exception e) {
            //@TODO handle exception
            e.printStackTrace();
        }
        image = images[0];
        x = GameView.WIDTH + 100;
        y = 580;
        speed = 30;
        index = 0;
    }
}
