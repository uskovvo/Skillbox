package main.repository;

import main.model.otherEntities.CaptchaCodes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaRepository extends CrudRepository<CaptchaCodes, Integer> {
}
