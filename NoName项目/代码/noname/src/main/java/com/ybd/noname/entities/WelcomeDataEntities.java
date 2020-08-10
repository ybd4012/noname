package com.ybd.noname.entities;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yeah
 * @date 2020/8/8 15:32
 */
@Data
@ToString
public class WelcomeDataEntities implements Serializable {
    private String allVipNums;
    private String vipUsingNums;
    private String supAdminNums;
    private String adminNums;
    private String logNums;
    private String OrderNums;
    private String lastUpdateTime;
}
