package com.x.runday.model;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.model
 * @ClassName: Barrier_Pet
 * @Author: Yao
 * @Description: 障碍物
 * @CreateDate: 2021/1/6 10:18
 * @Version:
 */
@Data
public class Barrier_Pet {

    private Image image;

    private Image[] images;

    private int x;

    private int y;

    private int index;

    public static final int WIDTH = 70;

    public static final int HEIGHT = 60;

    public Barrier_Pet() {
        init();
        image = images[0];
        x = 300;
        y = 460;
    }

    /**
     *
     */
    public void drop() {
       y ++;
        if (y >= 460) {
            y = 460;
        }
    }

    /**
     *
     */
    public void step() {
        image = images[index++ / 2 % images.length];
    }

    /**
     *
     */
    public boolean outOfBounds() {
        return (this.x <= -WIDTH);
    }

    private void init() {
        images = new Image[6];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("images/" + "d" + (i + 1) + ".png"));
            } catch (IOException e) {
                //@TODO auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
