package com.x.game2048.stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.x.game2048.MainGame;
import com.x.game2048.actor.DialogGroup;
import com.x.game2048.bus.constant.ResConstants;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.stage
 * @ClassName: HelpStage
 * @Author: Yao
 * @Description: 退出确认对话框的stage，包含一个对话框
 * @CreateDate: 2021/1/20 9:50
 * @Version:
 */
public class HelpStage extends BaseStage{

    /**
     * stage background color , 60% black
     */
    private final Color bgColor = new Color(0, 0, 0, 0.6F);

    /**
     * background
     */
    private Image bgImage;

    /**
     * 确认对话框
     */
    private DialogGroup dialogGroup;

    /**
     *
     * @param mainGame
     * @param viewport
     */
    public HelpStage(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init() {
        //background,一张纯白色的小图片
        bgImage = new Image(getMainGame().getTextureAtlas().findRegion(ResConstants.AtlasNames.GAME_BLANK));
        bgImage.setColor(bgColor);
        bgImage.setOrigin(0, 0);
        //缩放到铺满整个舞台
        bgImage.setScale(getWidth() / bgImage.getWidth(), getHeight() / bgImage.getHeight());
        addActor(bgImage);

        //创建对话框
        dialogGroup = new DialogGroup(getMainGame(), "确定退出游戏？");
    }
}
