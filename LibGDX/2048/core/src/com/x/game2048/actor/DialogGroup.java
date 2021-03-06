package com.x.game2048.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.x.game2048.MainGame;
import com.x.game2048.bus.constant.ResConstants;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.actor
 * @ClassName: DialogGroup
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/15 14:39
 * @Version:
 */
public class DialogGroup extends BaseGroup {

    /**
     * background color
     */
    private final Color bgColor = new Color(0xECECEC00 | (int) (255 * 0.95F));

    /**
     * 文本标签的字体颜色
     */
    private final Color msgTextColor = new Color(0x777777FF);

    /**
     * 按钮透明度
     */
    private final float btnAlpha = 0.95F;

    /**
     * 背景图片
     */
    private Image bgImage;

    /**
     * 对话框文本提示标签
     */
    private Label msgLabel;

    /**
     * 确认按钮
     */
    private Button okButton;

    /**
     * 取消按钮
     */
    private Button cancelButton;

    public DialogGroup(MainGame mainGame, String message) {
        super(mainGame);
        setWidth(ResConstants.DIALOG_WIDTH);
        init(message);
    }

    public void init(String message) {
        //background
        bgImage = new Image(getMainGame().getTextureAtlas().findRegion(ResConstants.AtlasNames.GAME_BLANK));
        bgImage.setColor(bgColor);
        bgImage.setOrigin(0, 0);
        //水平方向先缩放到铺满对话框宽度
        bgImage.setScale(getWidth() / bgImage.getWidth());
        addActor(bgImage);
        //确定按钮
        Button.ButtonStyle okBtnStyle = new ImageButton.ImageButtonStyle();
        okBtnStyle.up = new TextureRegionDrawable(getMainGame().getTextureAtlas()
                .findRegion(ResConstants.AtlasNames.DIALOG_BTN_OK, 1));
        okBtnStyle.down = new TextureRegionDrawable(getMainGame().getTextureAtlas()
                .findRegion(ResConstants.AtlasNames.DIALOG_BTN_OK, 2));
        okButton = new Button(okBtnStyle);
        okButton.setPosition(getWidth() - okButton.getWidth() - 10, 10);
        okButton.getColor().a = btnAlpha;
        addActor(okButton);

        //取消按钮
        Button.ButtonStyle cancelBtnStyle = new ImageButton.ImageButtonStyle();
        cancelBtnStyle.up = new TextureRegionDrawable(getMainGame().getTextureAtlas()
                .findRegion(ResConstants.AtlasNames.DIALOG_BTN_CANCEL, 1));
        cancelBtnStyle.down = new TextureRegionDrawable(getMainGame().getTextureAtlas()
                .findRegion(ResConstants.AtlasNames.DIALOG_BTN_CANCEL, 2));
        cancelButton = new Button(cancelBtnStyle);
        cancelButton.setPosition(10, 10);
        cancelButton.getColor().a = btnAlpha;
        addActor(cancelButton);

        //对话框文本提示标签
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getMainGame().getBitmapFont();
        labelStyle.fontColor = msgTextColor;
        msgLabel = new Label(message, labelStyle);
        //设置字体大小
        msgLabel.setFontScale(0.5F);
        //标签包裹字体
        msgLabel.setX(getWidth() / 2 - msgLabel.getWidth() / 2);
        msgLabel.setY(okButton.getY() + okButton.getHeight() + 50);
        addActor(msgLabel);
        //根据对话框中的控件计算对话框高度
        setHeight(msgLabel.getY() + msgLabel.getHeight() + 50);
        //已知对话框高度后，将背景竖直方向缩放到铺满对话框高度
        bgImage.setScaleY(getHeight() / bgImage.getHeight());

    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
