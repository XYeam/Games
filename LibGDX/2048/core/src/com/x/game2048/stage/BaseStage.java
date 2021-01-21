package com.x.game2048.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.x.game2048.MainGame;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.stage
 * @ClassName: BaseStage
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/15 16:31
 * @Version:
 */
public class BaseStage extends Stage {
    /**
     *
     */
    private MainGame mainGame;

    /**
     * 舞台是否可见-是否更新和绘制
     */
    private boolean visible = true;

    public BaseStage(MainGame mainGame, Viewport viewport) {
        super(viewport);
        this.mainGame = mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public boolean isVisible() {
        return visible;
    }
}
