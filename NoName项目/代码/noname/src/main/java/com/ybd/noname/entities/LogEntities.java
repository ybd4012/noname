package com.ybd.noname.entities;

import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yeah
 * @date 2020/7/30 12:33
 */
@lombok.Data
@ToString
public class LogEntities implements Serializable {
    private Integer id;
    private String content;
    private Timestamp create_time;
}
