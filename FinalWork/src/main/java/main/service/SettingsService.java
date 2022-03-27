package main.service;

import main.api.response.SettingResponse;
import main.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    @Autowired
    private final SettingsRepository repository;

    public SettingsService(SettingsRepository repository){
        this.repository = repository;
    }

    public SettingResponse getGlobalSettings(){
        Iterable<SettingsService> response = repository.findAll();

        return response;
    }
}
