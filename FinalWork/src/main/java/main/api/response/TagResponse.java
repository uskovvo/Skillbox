package main.api.response;

import lombok.Data;
import main.model.otherEntities.Tags;

import java.util.List;
import java.util.Map;

@Data
public class TagResponse {
    private List<Tags> tags;
}
