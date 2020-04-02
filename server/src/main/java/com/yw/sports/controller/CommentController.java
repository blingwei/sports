package com.yw.sports.controller;

import com.yw.sports.bean.responseBean.CommentResponse;
import com.yw.sports.pojo.Comment;
import com.yw.sports.result.Result;
import com.yw.sports.service.CommentService;
import com.yw.sports.service.UserService;
import com.yw.sports.utils.CommonUtil;
import com.yw.sports.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment){
        try{
            comment.setUserId(userService.getCurrentUser().getId());
            commentService.addCommit(comment);
            return ResultFactory.buildSuccessResult("评论成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误，添加失败");
        }
    }

    @GetMapping("/findCommentByConditionId")
    public Result findCommentByConditionId(@Param("id") Integer id){
        try{
            List<Comment> comments = commentService.findCommentByConditionId(id);
            List<CommentResponse> commentInfoList = new ArrayList();
            for(Comment comment: comments){
                CommentResponse commentInfo = new CommentResponse();
                commentInfo.setUserId(comment.getUserId());
                String username = userService.findUserById(comment.getUserId()).getUsername();
                commentInfo.setId(comment.getId());
                commentInfo.setUsername(username);
                commentInfo.setCreateDate(CommonUtil.DateToString(comment.getCreateTime(), "yyyy/MM//dd HH:mm"));
                commentInfo.setContent(comment.getContent());
                commentInfo.setPid(comment.getPid());
                if(comment.getReplyId() != 0){
                    String replayName = userService.findUserById(comment.getReplyId()).getUsername();
                    commentInfo.setReplayName(replayName);
                    commentInfo.setReplyId(comment.getReplyId());
                }
                commentInfoList.add(commentInfo);
            }
            return ResultFactory.buildSuccessResult("", commentInfoList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

}
