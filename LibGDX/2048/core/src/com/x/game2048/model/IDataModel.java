package com.x.game2048.model;

import com.x.game2048.bus.enums.GameState;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.model
 * @ClassName: IDataModel
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/20 16:52
 * @Version:
 */
public interface IDataModel {

    /**
     * 数据初始化，创建了一个数据模型实例或重新开始游戏
     */
    public void dataInit();

    /**
     * 获取当前数据(4 * 4 二维数组)
     */
    public Integer[][] getData();

    /**
     * 获取当前的分数
     */
    public int getCurrentScore();

    /**
     * 判断当前是否处于游戏胜利状态
     */
    public GameState getGameState();

    /**
     * 对数据的操作，向上滑动
     */
    public void toTop();

    /**
     * 向下滑动
     */
    public void toBottom();

    /**
     * 向左滑动
     */
    public void toLeft();

    /**
     * 向右滑动
     */
    public void toRight();


}
