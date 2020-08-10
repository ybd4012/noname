package com.ybd.noname.service;

import com.ybd.noname.entities.WelcomeDataEntities;

/**
 * @author yeah
 * @date 2020/8/8 15:38
 */
public interface WelcomeService {
    /**
     * 第一次从 mysql 取得数据 第二次从缓存中读取
     * 第一次会执行方法体 第二次直接缓存中读取
     * @param key
     * @return
     */
    WelcomeDataEntities showData(Integer key);

    /**
     * 更新缓存中的数据
     * @param key
     * @return
     */
    WelcomeDataEntities updateWelcomeData(Integer key);
}
