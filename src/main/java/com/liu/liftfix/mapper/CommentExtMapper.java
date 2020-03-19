package com.liu.liftfix.mapper;

import com.liu.liftfix.model.Comment;
import com.liu.liftfix.model.CommentExample;
import com.liu.liftfix.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}