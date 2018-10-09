package com.slaverivanje.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentRepo;

    public ArrayList<Comments> findAllComments(String id) {
        return (ArrayList<Comments>) commentRepo.findByArticleId(id);
    }

    public Comments saveComment(Comments comment){
        return commentRepo.save(comment);
    }

    public ArrayList<Comments> findAllCommentsByAuthor(String author){
        return (ArrayList<Comments>) commentRepo.findByAuthor(author);
    }

}
