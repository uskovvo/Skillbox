package main.api.response;

import lombok.Data;
import main.model.users.Users;

@Data
public class CheckUserResponse {
    private boolean result;
    private Users user;
}
