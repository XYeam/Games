package com.x.game2048.actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.x.game2048.MainGame;
import com.x.game2048.bus.constant.ResConstants;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.actor
 * @ClassName: BottomGroup
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/15 14:38
 * @Version:
 */
public class BottomGroup extends BaseGroup {

    /**
     * help button
     */
    private Button helpButton;

    /**
     * exit button
     */
    private Button exitButton;

    public BottomGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    private void init() {
        Button.ButtonStyle helpStyle = new Button.ButtonStyle();
        helpStyle.up = new TextureRegionDrawable(
                getMainGame().getTextureAtlas().findRegion(ResConstants.AtlasNames.GAME_BTN_HELP, 1)
        );
        helpStyle.down = new TextureRegionDrawable(
                getMainGame().getTextureAtlas().findRegion(ResConstants.AtlasNames.GAME_BTN_HELP, 2)
        );
        helpButton = new Button(helpStyle);
        helpButton.setX(15);
        //set button click listen
        helpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //display help stage @TODO
                getMainGame().getGameScreen();
            }
        });
        addActor(helpButton);
        //设置组的尺寸(以世界的宽度，按钮的高度，作为组的宽高)
        setSize(getMainGame().getWorldWidth(), helpButton.getHeight());
        //退出按钮
        Button.ButtonStyle exitStyle = new Button.ButtonStyle();
        exitStyle.up = new TextureRegionDrawable(
                getMainGame().getTextureAtlas().findRegion(ResConstants.AtlasNames.GAME_BTN_EXIT, 1)
        );
        exitStyle.down = new TextureRegionDrawable(
                getMainGame().getTextureAtlas().findRegion(ResConstants.AtlasNames.GAME_BTN_EXIT, 2)
        );
        exitButton = new Button(exitStyle);
        exitButton.setX(240);
        //设置按钮点击监听
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //@TODO setShowExitConfirmStage
                getMainGame().getGameScreen();
            }
        });
        addActor(exitButton);
    }
}
