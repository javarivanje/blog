package com.slaverivanje.blog;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository <Article, String> {

}

