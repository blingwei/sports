package com.yw.sports.service.Impl;

import com.yw.sports.dao.CommentMapper;
import com.yw.sports.pojo.Comment;
import com.yw.sports.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public void addCommit(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<Comment> findCommentByConditionId(Integer id) {
        return commentMapper.findCommentByConditionId(id);
    }


}
