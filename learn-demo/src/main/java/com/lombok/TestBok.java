package com.lombok;

/**
 * @author Chen Xiao
 * @since 2021-12-24 17:42
 */
public class TestBok {


    public static void main(String[] args) {
        Comment comment = new Comment();
        comment.setLiveId(111);
        comment.setLiveCount(222);
        comment.setNjCommentFirst(false);

        System.out.println(comment.toString());
    }
}
