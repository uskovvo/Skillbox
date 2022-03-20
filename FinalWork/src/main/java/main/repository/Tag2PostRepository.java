package main.repository;

import main.otherEntities.Tag2Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tag2PostRepository extends CrudRepository<Tag2Post, Integer> {
}
