package com.yw.sports.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertUtil {


//    public Comment covertComment(CommentInfo commentInfo){
//        Comment comment = new Comment();
//        Integer userId = userMapper.findByName(commentInfo.getUsername()).getId();
//        comment.setUserId(userId);
//        TypeEnum typeEnum = TypeEnum.valueOf(Integer.parseInt(commentInfo.getType()));
//        comment.setType(typeEnum);
//        Integer matterId = commentInfo.getMatterId();
//        comment.setMatterId(matterId);
//        if(commentInfo.getPid() != null){
//            comment.setPid(commentInfo.getPid());
//        }
//        if(commentInfo.getReplyId() != null){
//            comment.setReplyId(commentInfo.getReplyId());
//        }
//        comment.setContent(commentInfo.getContent());
//        return comment;
//    }
//
//    public List<CommentInfo> showComment(List<Comment> comments){
//        List<CommentInfo> commentInfoList = new ArrayList();
//        for(Comment comment: comments){
//            CommentInfo commentInfo = new CommentInfo();
//            commentInfo.setUserId(comment.getUserId());
//            String username = userMapper.findUserById(comment.getUserId()).getUsername();
//            commentInfo.setId(comment.getId());
//            commentInfo.setUsername(username);
//            commentInfo.setCreateDate(CommonUtil.DateToString(comment.getCreateDate(), "yyyy/MM//dd HH:mm"));
//            commentInfo.setContent(comment.getContent());
//            commentInfo.setPid(comment.getPid());
//            if(comment.getReplyId() != 0){
//                String replayName = userMapper.findUserById(comment.getReplyId()).getUsername();
//                commentInfo.setReplayName(replayName);
//                commentInfo.setReplyId(comment.getReplyId());
//            }
//            PickResponse pickResponse = userPickManage.getCommentResponse(comment.getId(), comment.getUserId());
//            commentInfo.setPickNum(pickResponse.getNum());
//            commentInfo.setPickStatus(pickResponse.getPickStatus());
//            commentInfoList.add(commentInfo);
//        }
//        return commentInfoList;
//    }



}
