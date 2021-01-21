package com.x.game2048.bus.builder;

import com.x.game2048.model.DataListener;
import com.x.game2048.model.IDataModel;
import com.x.game2048.model.impl.IDataModelImpl;

/**
 * @ProjectName: 2048
 * @Package: com.x.game2048.bus.builder
 * @ClassName: GameModelBuilder
 * @Author: Yao
 * @Description:
 * @CreateDate: 2021/1/21 9:42
 * @Version:
 */
public class GameModelBuilder {
    /**
     * 创建一个指定行列数据的数据模型
     */
    public static IDataModel createDataModel(Integer rowSum, Integer colSum, DataListener dataListener) {
        return new IDataModelImpl(rowSum, colSum, dataListener);
    }
}
