package com.x.game2048.model.impl;

import com.badlogic.gdx.Game;
import com.x.game2048.bus.enums.GameState;
import com.x.game2048.model.DataListener;
import com.x.game2048.model.IDataModel;

import java.util.Random;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.model.impl
 * @ClassName: IDataModelImpl
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/20 17:47
 * @Version:
 */

public class IDataModelImpl implements IDataModel {

    /**
     * 数据的总行数
     */
    private final Integer rowSum;

    /**
     * 数据的总列数
     */
    private final Integer colNum;

    /**
     * 二维数组数据
     */
    private final Integer[][] data;

    /**
     * 数据监听器
     */
    private DataListener dataListener;

    /**
     * 得分
     */
    private Integer currentScore;

    /**
     * 游戏状态
     */
    private GameState gameState = GameState.GAME;

    /**
     * 随机数生成器
     */
    private final Random random;

    public IDataModelImpl(Integer rowSum, Integer colNum, DataListener dataListener) {
        this.rowSum = rowSum;
        this.colNum = colNum;
        data = new Integer[this.rowSum][this.colNum];
        this.dataListener = dataListener;
        random = new Random();
        dataListener = new DataListenerImpl();
    }

    /**
     *
     */
    @Override
    public void dataInit() {
        //数据清零
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colNum; col++) {
                data[row][col] = 0;
            }
        }
        //重置状态
        currentScore = 0;
        gameState = GameState.GAME;
        //随机生成两个数字
        randomGeneratorNumber();
        randomGeneratorNumber();
    }

    @Override
    public Integer[][] getData() {
        return data;
    }

    @Override
    public int getCurrentScore() {
        return currentScore;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void toTop() {
        //移动和合并数字，整个2048游戏中最核心的算法部分，也是最难的
        //非正在游戏状态时调用，忽略
        if (gameState != GameState.GAME) {
            return;
        }
        //是否有卡片移动或合并的标记
        boolean hasMove = false;
        //竖直方向移动，依次遍历每一列
        for (int col = 0; col < colNum; col++) {
            //向上移动，从第0行开始依次向下遍历每一行
            for (int row = 0; row < rowSum; row++) {
                //找出当前遍历行row下面的首个非空卡片，将该非空卡片移动到当前row行位置
                for (int tempRow = row + 1; tempRow < rowSum; tempRow++) {
                    if (data[tempRow][col] == 0) {
                        continue;
                    }
                    if (data[row][col] == 0) {
                        //如果当前row行位置是空的，则直接移动卡片
                        data[row][col] = data[tempRow][col];
                        hasMove = true;
                        //数字移动后原位置清零
                        data[tempRow][col] = 0;
                        //当前row行位置是空的，卡片移动到当前row行位置需要在下一循环中重新遍历该row行
                        //移动后的row位置卡片和row下面下一次需要移动的卡片数字如果相同依然要相加，没有重新校验会被忽略
                        row --;
                    } else if (data[row][col].equals(data[tempRow][col])) {
                        //如果当前row行位置和找到的row下面首个非空卡片的数字相同，则合并数字
                        data[row][col] += data[tempRow][col];
                        hasMove = true;
                        //增加分数
                        currentScore += data[row][col];
                        //回调监听
                        if (dataListener != null) {
                            dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                        }
                        //合并后原位置清零
                        data[tempRow][col] = 0;
                    }
                    break;
                }
            }
        }
        //滑动一次，只有卡片有移动过时，才需要检测是否游戏结束和生成新数字
        if (hasMove) {
            //校验游戏是否结束(过关或失败)
            checkGameFinish();
            //移动玩一次后，随机生成一个数字
            randomGeneratorNumber();
            //生成数字后还需要再校验一次游戏是否结束，防止生成数字后就是不可在移动状态
            checkGameFinish();
        }
    }

    @Override
    public void toBottom() {
        if (gameState != GameState.GAME) {
            return;
        }
        boolean hasMove = false;

        for (int col = 0; col < colNum; col++) {
            for (int row = rowSum - 1; row >= 0 ; row--) {
                for (int temRow = row - 1; temRow >= 0; temRow--) {
                    if (data[temRow][col] == 0) {
                        continue;
                    }
                    if (data[row][col] == 0) {
                        hasMove = true;
                        data[row][col] = data[temRow][col];
                        data[temRow][col] = 0;
                        row ++;
                    } else if (data[row][col].equals(data[temRow][col])) {
                        data[row][col] += data[temRow][col];
                        hasMove = true;
                        currentScore += data[row][col];
                        if (dataListener != null) {
                            dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                        }
                        data[temRow][col] = 0;
                    }
                    break;
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }

    @Override
    public void toLeft() {
        if (gameState != GameState.GAME) {
            return;
        }
        boolean hasMove = false;
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colNum; col++) {
                for (int tempCOl = col + 1; tempCOl < colNum; tempCOl++) {
                    if (data[row][tempCOl] == 0) {
                        continue;
                    }
                    if (data[row][col] == 0) {
                        data[row][col] = data[row][tempCOl];
                        hasMove = true;
                        data[row][tempCOl] = 0;
                        col--;
                    } else if (data[row][col].equals(data[row][tempCOl])) {
                        data[row][col] += data[row][tempCOl];
                        hasMove = true;
                        currentScore += data[row][col];
                        if (dataListener != null) {
                            dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                        }
                        data[row][tempCOl] = 0;
                    }
                    break;
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }

    @Override
    public void toRight() {
        if (gameState != GameState.GAME) {
            return;
        }
        boolean hasMove = false;

        for (int row = 0; row < rowSum; row++) {
            for (int col = colNum - 1; col >= 0; col--) {
                for (int temCol = col - 1; temCol >= 0; temCol--) {
                    if (data[row][temCol] == 0) {
                        continue;
                    }
                    if (data[row][col] == 0) {
                        data[row][col] = data[row][temCol];
                        hasMove = true;
                        data[row][temCol] = 0;
                        col ++;
                    } else if (data[row][col].equals(data[row][temCol])) {
                        data[row][col] += data[row][temCol];
                        hasMove = true;
                        currentScore += data[row][col];
                        if (dataListener != null) {
                            dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                        }
                        data[row][temCol] = 0;
                    }
                    break;
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }


    /**
     * 检验游戏是否结束
     */
    private void checkGameFinish() {
        //判断是否游戏胜利
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colNum; col++) {
                if (data[row][col] == 2048) {
                    //只要有一个卡片拼凑出2048，游戏胜利
                    gameState = GameState.WIN;
                    if (dataListener != null) {
                        //监听回调
                        dataListener.onGameOver(true);
                    }
                    return;
                }
            }
        }
        //游戏还没有胜利，则判断是否还可以移动
        if (!isMove()) {
            gameState = GameState.GAME_OVER;
            if (dataListener != null) {
                dataListener.onGameOver(false);
            }
            return;
        }
    }

    /**
     * 判断是否还可移动
     * @return boolean
     */
    private boolean isMove() {
        //校验水平方向上是否可移动
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colNum; col++) {
                //有空位或连续的两个卡片位置数字相等，则可移动
                if (data[row][col] == 0 || data[row][col + 1] == 0
                        || (data[row][col] != null && data[row][col].equals(data[row][col + 1]))) {
                    return true;
                }
            }
        }
        //校验竖直方向是否可移动
        for (int col = 0; col < colNum; col++) {
            for (int row = 0; row < rowSum; row++) {
                if (data[row][col] == 0 || data[row + 1][col] == 0 || data[row][col].equals(data[row + 1][col])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 随机在指定的行列生成一个数字-2或4，整个2048游戏中的核心算法之一
     */
    private void randomGeneratorNumber() {
       //计算出空卡片的数量-数字为0的卡片
        Integer emptyCardsCount = 0;
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colNum; col++) {
                if (data[row][col] == 0) {
                    emptyCardsCount++;
                }
            }
        }
        //如果没有空卡片，则游戏结束
        if (emptyCardsCount == 0) {
            //有卡片移动是才会调用该方法，所以程序不会到达这里
            //但为了逻辑严谨，这里还是要做一下是否有空卡片的判断
            //游戏失败
            gameState = GameState.GAME_OVER;
            if (dataListener != null) {
                dataListener.onGameOver(false);
            }
            return;
        }
        //有空卡片，则这些空卡片中随机挑一个位置生成数字
        Integer newNumOnEmptyCardsPosition = random.nextInt(emptyCardsCount);
        Integer newNum = random.nextFloat() < 0.2F ? 4 : 2;
        //把生成的数字-newNum，放到指定的空卡片中
        Integer emptyCardPosition = 0;
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colNum; col++) {
                //忽略非空卡片
                if (data[row][col] != null && data[row][col] == 0) {
                    continue;
                }
                //如果找到指定位置的空卡片，则放入数字
                if (emptyCardPosition.equals(newNumOnEmptyCardsPosition)) {
                    data[row][col] = newNum;
                    //有数字生成，回调监听
                    if (dataListener != null) {
                        dataListener.onGeneratorNumber(row, col, newNum);
                    }
                    return;
                }
                //还没有遍历到指定位置的空卡片，继续遍历
                emptyCardPosition++;
            }
        }
    }
}
