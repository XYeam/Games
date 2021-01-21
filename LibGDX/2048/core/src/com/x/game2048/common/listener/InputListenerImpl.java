package com.x.game2048.common.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.x.game2048.actor.MiddleGroup;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.common.listener
 * @ClassName: InputListenerImpl
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/20 17:39
 * @Version:
 */
public class InputListenerImpl extends InputListener {

    private float downX;

    private float downY;

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        downX = x;
        downY = y;
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        float diffX = x - downX;
        float diffY = y - downY;
        if (Math.abs(diffX) >= MiddleGroup.SLIDE_MIN_DIFF
                && Math.abs(diffX) * 0.5F > Math.abs(diffY)) {
            //左右滑动
            if (diffX > 0) {

            }
        }
    }

}
