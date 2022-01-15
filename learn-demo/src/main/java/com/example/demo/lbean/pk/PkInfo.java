package com.example.demo.lbean.pk;

import lombok.Data;

/**
 * Created in 2018-04-12 16:16.
 * pk对战信息
 * @author chenxiao
 */
@Data
public class PkInfo {
    private long initiatorId;
    private String initiatorBand;
    private String initiatorName;
    private long initiatorRadioId;
    private int initiatorLizhi;

    private long receiverId;
    private String receiverBand;
    private String receiverName;
    private long receiverRadioId;
    private int receiverLizhi;

    private int winStatus;
    private String dateStr;
    private String timeStr;
}
