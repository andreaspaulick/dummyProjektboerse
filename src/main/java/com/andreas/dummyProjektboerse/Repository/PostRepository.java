package com.andreas.dummyProjektboerse.Repository;

import com.andreas.dummyProjektboerse.Entity.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Posts, Integer> {
}
