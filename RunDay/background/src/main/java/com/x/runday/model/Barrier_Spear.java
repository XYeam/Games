package com.x.runday.model;

import com.x.runday.views.GameView;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Random;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.model
 * @ClassName: Barrier_Spear
 * @Author: Yao
 * @Description: 障碍物
 * @CreateDate: 2021/1/6 10:43
 * @Version:
 */
@Data
public class Barrier_Spear {

    private Image image;

    private Image[] images;

    private int x;

    private int y;

    public static final int WIDTH = 150;

    public static final int HEIGHT = 350;

    public Barrier_Spear() {
        Random random = new Random();
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File("image/11.png"));
            images[1] = ImageIO.read(new File("image/12.png"));
            images[2] = ImageIO.read(new File("image/13.png"));
            images[3] = ImageIO.read(new File("image/14.png"));
        } catch (Exception e) {
            //@TODO handle exception
            e.printStackTrace();
        }
        image = images[random.nextInt(4)];
        x = GameView.WIDTH + 1500;
        y = 0;
    }

    public void step() {
        x -= 20;
    }

    public void paintBarrier(Graphics graphics) {
        graphics.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public boolean outOfBounds() {
        return (this.x <= -WIDTH);
    }
}
