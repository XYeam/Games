package com.x.game2048.actor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.x.game2048.MainGame;
import com.x.game2048.bus.constant.ResConstants;
import com.x.game2048.common.listener.InputListenerImpl;
import com.x.game2048.model.IDataModel;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.actor
 * @ClassName: MiddleGroup
 * @Author: Yao
 * @Description: 中间部分
 * @CreateDate: 2021/1/15 14:39
 * @Version:
 */
public class MiddleGroup extends BaseGroup {

    /**
     * 卡片的行列数
     */
    private static final int CARD_ROW_SUM = 4;

    private static final int CARD_COL_SUM = 4;

    /**
     * 滑动生效的最小距离
     */
    public static final float SLIDE_MIN_DIFF = 20;

    /**
     * 中间区域背景
     */
    private Image bgImage;

    /**
     * 所有数字卡片
     */
    private final CardGroup[][] allCards = new CardGroup[CARD_ROW_SUM][CARD_COL_SUM];

    /**
     * 移动操作的音效
     */
    private Sound moveSound;

    /**
     * 合并数据的音效
     */
    private Sound mergeSound;

    /**
     * 数据模型-封装了核心的数据与逻辑
     */
    private IDataModel dataModel;

    /**
     *
     * @param mainGame
     */
    public MiddleGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    /**
     *
     */
    private void init() {
        //背景
        bgImage = new Image(getMainGame().getTextureAtlas()
                .findRegion(ResConstants.AtlasNames.GAME_RECT_BG));
        addActor(bgImage);
        //设置组的宽高-以组的背景宽高为组的宽高
        setSize(bgImage.getWidth(), bgImage.getHeight());
        //创建所有的数字卡片
        for (int row = 0; row < CARD_ROW_SUM; row++) {
            for (int col = 0; col < CARD_COL_SUM; col++) {
                allCards[row][col] = new CardGroup(getMainGame());
                allCards[row][col].setOrigin(Align.center);
                addActor(allCards[row][col]);
            }
        }
        //获取卡片的高度
        float cardWidth = allCards[0][0].getWidth();
        float cardHeight = allCards[0][0].getHeight();
        //计算所有卡片按指定的行列排列到组中后卡片间的水平和竖直间隙大小
        float horizontalInterval = (getWidth() - CARD_COL_SUM * cardWidth)
                / (CARD_COL_SUM + 1);
        float verticalInterVal = (getHeight() - CARD_COL_SUM * cardHeight)
                / (CARD_COL_SUM + 1);
        //均匀的排版卡片在组中的位置
        float cardY = 0;
        for (int row = 0; row < CARD_COL_SUM; row++) {
            cardY = getHeight() - (verticalInterVal + cardHeight) * (row + 1);
            for (int co = 0; co < CARD_COL_SUM; co++) {
                allCards[row][co].setX(horizontalInterval + (cardWidth + horizontalInterval) * co);
                allCards[row][co].setY(cardY);
            }
        }
        //获取音效
        moveSound = getMainGame().getAssetManager().get(ResConstants.Audios.MOVE, Sound.class);
        mergeSound = getMainGame().getAssetManager().get(ResConstants.Audios.MERGE, Sound.class);
        //添加输入监听器(用于监听手势的滑动)
        addListener(new InputListenerImpl());

    }

    public void toTop() {
        //操作数据模型中的数据
    }
}
