package com.andreas.dummyProjektboerse.Repository;

import com.andreas.dummyProjektboerse.Entity.Posts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Posts, Integer> {

    @Override
    List<Posts> findAll();
}
