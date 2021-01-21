package com.x.game2048.model;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.model
 * @ClassName: DataListener
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/21 9:35
 * @Version:
 */
public interface DataListener {

    /**
     * 随机生成数字时调用
     * @param row 生成的数字所在行
     * @param col 生成的数字所在列
     * @param num 生成的数字
     */
    public void onGeneratorNumber(Integer row, Integer col, Integer num);

    /**
     * 两个数字合并时调用
     *
     * @param rowAfterMerge          合并后数字所在行
     * @param colAfterMerge          合并后数字所在列
     * @param numAfterMerge          合并后的数字
     * @param currentScoreAfterMerge 合并后的当前分数
     */
    public void onNumberMerge(Integer rowAfterMerge, Integer colAfterMerge, Integer numAfterMerge, Integer currentScoreAfterMerge);

    /**
     * 游戏结束时调用
     * @param isWin 是否胜利
     */
    public void onGameOver(boolean isWin);
}
