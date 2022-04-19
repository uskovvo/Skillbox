package main.service;

import main.api.response.SettingResponse;
import main.model.otherEntities.GlobalSettings;
import main.repository.SettingsRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class SettingsService {

    private final SettingsRepository repository;
    private static final String multiuserMode = "MULTIUSER_MODE";
    private static final String postPremoderation = "POST_PREMODERATION";
    private static final String statisticsIsPublic = "STATISTICS_IS_PUBLIC";

    public SettingsService(SettingsRepository repository){
        this.repository = repository;
        setSettings(multiuserMode, "Многопользовательский режим", "Yes");
        setSettings(postPremoderation, "Предварительная модерация постов", "No");
        setSettings(statisticsIsPublic, "Публичная статистика", "YES");
    }

    private void setSettings(String code, String name, String value){
        GlobalSettings globalSettings = new GlobalSettings();
        globalSettings.setCode(code);
        globalSettings.setName(name);
        globalSettings.setValue(value);
        repository.save(globalSettings);
    }
    public SettingResponse getGlobalSettings() {
        SettingResponse response = new SettingResponse();
        Iterable<GlobalSettings> globalSettings = repository.findAll();
        globalSettings.forEach(settings -> {
            switch (settings.getCode()) {
                case multiuserMode:
                    response.setMultiuserMode(yesNo(settings.getValue()));
                    break;
                case postPremoderation:
                    response.setPostPremoderation(yesNo(settings.getValue()));
                    break;
                case statisticsIsPublic:
                    response.setStatisticsIsPublic(yesNo(settings.getValue()));
                    break;
                default: break;
            }
        });
        return response;
    }

    private boolean yesNo(String value){
        return value.toLowerCase(Locale.ROOT).equals("yes");
    }
}
