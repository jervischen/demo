package com.example.demo.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 社交红包消息体
 */
@Data
@ToString
public class SocialRedEnvelopeMsg {
	/**
	 * 发红包人的ID
	 */
	private long senderId;
	/**
	 * 房间ID
	 */
	private long roomId;
	/**
	 * 红包ID
	 */
	private long redEnvelopeId;
	/**
	 * 是否在公屏上发送评论，例如：我在直播间里藏了个红包，大家在公屏发送「红包口令」就可以抢到。祝大家恭喜发财，万事如意！
	 */
	private boolean isSendComment;
	/**
	 * 是否已经被抢完
	 */
	private boolean isFinish;

	/**
	 * 1.普通红包 2.口令红包
	 */
	private int type;
	/**
	 * 口令
	 */
	private String word;
	private long createTime;
	/**
	 * 是否屏蔽的红包
	 */
	private boolean isShield;

	/**
	 * 0:发红包  1:领红包
	 */
	private int actionType;
}
