package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.UserEntity;

@Getter
@Setter
@ToString
public class UserLoginContext {
    private boolean success;
    private UserData user;
    private String error;

    public UserLoginContext() {
        this.success = false;
        this.user = null;
        this.error = "empty login context";
    }

    public UserLoginContext(UserEntity user) {
        this.setSuccess(true);
        this.setUser(new UserData(user));
        this.setError("");
    }

    public UserLoginContext(String errorMessage) {
        this.success = false;
        this.user = null;
        this.error = errorMessage;
    }
}
