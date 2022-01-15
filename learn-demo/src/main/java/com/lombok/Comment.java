package com.lombok;

import com.google.common.base.Joiner;
import lombok.Data;

/**
 * @author Chen Xiao
 * @since 2021-12-24 17:16
 */
@Data
public class Comment {

    private long liveId;

    private int liveCount;

    private boolean isNjCommentFirst;


    @Override
    public String toString() {
        return Joiner.on("_").join(liveId,liveCount,isNjCommentFirst);
    }
}
