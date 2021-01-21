package com.x.game2048.bus.enums;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.bus.enums
 * @ClassName: GameState
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/20 17:54
 * @Version:
 */
public enum GameState {

    /**
     * 正在游戏状态
     */
    GAME(1, "game"),
    /**
     * 游戏胜利状态
     */
    WIN(2, "win"),
    /**
     * 游戏失败状态
     */
    GAME_OVER(0, "game over");

    private Integer code;

    private String desc;

    GameState(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GameState resolve(Integer code) {
        for (GameState gameState : GameState.values()) {
            if (gameState.code.equals(code)) {
                return gameState;
            }
        }
        return null;
    }

    public static GameState valueOf(Integer code) {
        GameState resolve = resolve(code);
        if (resolve == null) {
            return null;
        }
        return resolve;
    }
}
