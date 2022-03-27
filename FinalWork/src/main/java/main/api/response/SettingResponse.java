package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.YesNoType;

public class SettingResponse {
    @JsonProperty("MULTIUSER_MODE")
    @Getter @Setter
    private YesNoType multiuserMode;
    @JsonProperty("POST_PREMODERATION")
    @Getter @Setter
    private YesNoType postPremoderation;
    @JsonProperty("STATISTICS_IS_PUBLIC")
    @Getter @Setter
    private YesNoType statisticsIsPublic;
}
