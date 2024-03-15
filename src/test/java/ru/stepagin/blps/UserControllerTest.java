package ru.stepagin.blps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.stepagin.blps.controller.AuthController;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController userController;

    @Test
    public void testLogin() {
        UserEntity mockedUser = new UserEntity();
        when(userService.login(anyString(), anyString())).thenReturn(mockedUser);

        ResponseEntity<UserEntity> response = userController.login("username", "password");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedUser, response.getBody());
    }

    @Test
    public void testRegister() {
        UserEntity userToRegister = new UserEntity();
        UserEntity registeredUser = new UserEntity();
        when(userService.register(any(UserEntity.class))).thenReturn(registeredUser);

        ResponseEntity<UserEntity> response = userController.register(userToRegister);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registeredUser, response.getBody());
    }
}

