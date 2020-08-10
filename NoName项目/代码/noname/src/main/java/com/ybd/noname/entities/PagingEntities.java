package com.ybd.noname.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yeah
 * @date 2020/8/10 17:54
 */
@Data
public class PagingEntities implements Serializable {
    /**
     * 当前页
     *      上一页 = 当前 - 1
     *      当前
     *      下一页 = 当前 + 1
     */
    private String currentPage;
    /**
     * 每页显示的大小
     */
    private String pageSize;
}
