package com.x.game2048.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.x.game2048.MainGame;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.actor
 * @ClassName: BaseGroup
 * @Author: Yao
 * @Description: base class of actor
 * @CreateDate: 2021/1/15 14:35
 * @Version:
 */
public class BaseGroup extends Group {

    private MainGame mainGame;

    public BaseGroup(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }
}
