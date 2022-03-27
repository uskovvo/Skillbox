package main.repository;

import main.service.SettingsService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends CrudRepository<SettingsService, Integer> {
}
