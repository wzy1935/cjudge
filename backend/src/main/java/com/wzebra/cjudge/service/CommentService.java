package com.wzebra.cjudge.service;

import com.wzebra.cjudge.entity.Pager;
import com.wzebra.cjudge.entity.comment.Comment;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.comment.CommentShow;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired CommentRepository commentRepository;

    public WebData addComment(int problemId, User user, String content) {
        Comment comment = new Comment(-1, -1, user, content);
        commentRepository.addComment(problemId, comment);
        return WebData.resultMessageInstance(true, "评论成功！");
    }

    public WebData deleteComment(int commentId, User user) {
        Comment comment = commentRepository.getCommentById(commentId);
        if (comment == null) return WebData.resultMessageInstance(false, "评论不存在");
        if (comment.getUser().getId() != user.getId()) {
            return WebData.resultMessageInstance(false, "非本人操作，删除失败！");
        } else {
            commentRepository.removeCommentById(commentId);
            return WebData.resultMessageInstance(true, "删除成功！");
        }
    }

    public Pager<CommentShow> getCommentsOfProblem(int problemId, int size, int page, User user) {
        List<Comment> comments = commentRepository.getCommentsOfProblem(problemId, size, page);
        List<CommentShow> commentShows = new ArrayList<>();
        for (Comment c : comments) {
            CommentShow commentShow = new CommentShow(c.getId(), c.getUser(), c.getContent(), false);
            boolean deletable = c.getUser().getId() == user.getId();
            commentShow.setDeletable(deletable);
            commentShows.add(commentShow);
        }
        Pager<CommentShow> output = new Pager<>();
        output.setSize(size);
        output.setPage(page);
        output.setRows(commentShows);
        output.setTotal(commentRepository.getCommentsOfProblemAmount(problemId));
        return output;
    }

}
