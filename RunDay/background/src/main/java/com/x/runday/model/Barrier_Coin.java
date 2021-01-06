package com.x.runday.model;

import com.x.runday.views.GameView;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @ProjectName: runDay
 * @Package: com.x.runday.model
 * @ClassName: Coin
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/6 10:52
 * @Version:
 */
@Data
public class Barrier_Coin {

    private Image image;

    private int x;

    private int y;

    private int speed;

    private Random random = new Random();

    private static final int WIDTH = 30;

    private static final int HEIGHT = 30;

    public Barrier_Coin() {
        try {
            image = ImageIO.read(new File("images/" + (random.nextInt(6) + 21) + ".png"));
        } catch (IOException e) {
            //@TODO auto-generated catch block
            e.printStackTrace();
        }
        x = GameView.WIDTH + 10;
        y = random.nextInt(600);
        speed = 20;
    }

    public void step() {
        x -= speed;
    }

    public void paintBarrier(Graphics graphics) {
        graphics.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public boolean outOfBounds() {
        return (this.x <= -WIDTH);
    }
}
