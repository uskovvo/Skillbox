package main.repository;

import main.posts.PostVotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends CrudRepository<PostVotes, Integer> {
}
