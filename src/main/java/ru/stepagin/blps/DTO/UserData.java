package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.UserEntity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserData {
    private Long id;
    private String login;
    private String nickname;

    public UserData(UserEntity u) {
        this.setId(u.getId());
        this.setLogin(u.getLogin());
        this.setNickname(u.getNickname());
    }
}
