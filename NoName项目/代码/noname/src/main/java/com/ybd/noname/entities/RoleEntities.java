package com.ybd.noname.entities;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yeah
 * @date 2020/7/30 16:19
 */
@Data
@ToString
public class RoleEntities implements Serializable {
    private Integer id;
    private String name;
    private String power;
    private String describe;
    private Integer status;
    private String perms;
}
