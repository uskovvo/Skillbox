package main.repository;

import main.otherEntities.CaptchaCodes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaRepository extends CrudRepository<CaptchaCodes, Integer> {
}
