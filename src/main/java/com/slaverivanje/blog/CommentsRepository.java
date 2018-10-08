package com.slaverivanje.blog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository <Comments, String> {
    public List <Comments> findByArticleId(String id);
    public List<Comments> findByAuthor(String author);
}
