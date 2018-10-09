package com.slaverivanje.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    public CommentsService commService;

    @RequestMapping (method = RequestMethod.GET, value = "/article/{id}/comments")
    public ArrayList <Comments> findAllComments(@PathVariable String id){
        return commService.findAllComments(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/article/{id}/comments")
    public Comments saveComment(@RequestBody Comments comment, @PathVariable String id){
         return commService.saveComment(comment);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/article/{id}/comments/{author}")
    public List<Comments> findAllCommentsByAuthor(@PathVariable String id, @PathVariable String author){
        return commService.findAllCommentsByAuthor(author);
    }

}
