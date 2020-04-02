package com.yw.sports.service;


import com.yw.sports.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void addCommit(Comment comment);
    List<Comment> findCommentByConditionId(Integer id);

}
