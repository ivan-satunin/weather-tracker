package com.me.weathertracker.auth;

import com.me.weathertracker.common.exception.ValidationException;
import com.me.weathertracker.common.validation.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class AppUserServiceIT {
    private static final String VALID_LOGIN = "test@gmail.com";
    private static final String VALID_PASSWORD = "password";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void register_ifUserIsValid_shouldBeSaved() {

        userService.register(VALID_LOGIN, VALID_PASSWORD);
        final var maybeUser = userRepository.findByLogin(VALID_LOGIN);
        assertThat(maybeUser).isPresent();
        final var user = maybeUser.get();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getLogin()).isEqualTo(VALID_LOGIN);
        final var isSamePassword = passwordEncoder.matches(VALID_PASSWORD, user.getPassword());
        assertThat(isSamePassword).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getUsersLoginAndPassword")
    void register_ifUserInvalid_shouldThrow(String login, String password, List<Error> errors) {
        assertThatThrownBy(() -> userService.register(login, password))
                .isInstanceOf(ValidationException.class)
                .extracting("errors")
                .isEqualTo(errors);
    }

    static Stream<Arguments> getUsersLoginAndPassword() {
        return Stream.of(
                Arguments.of(VALID_LOGIN, "  ", List.of(Error.of("The password must be non-blank"))),
                Arguments.of(" ", VALID_PASSWORD, List.of(Error.of("The Username or email must be non-blank"))),
                Arguments.of("   ", "", List.of(Error.of("The Username or email must be non-blank"), Error.of("The password must be non-blank")))
        );
    }

    @Test
    void register_ifLoginExists_shouldThrow() {
        final var login = "Tom";
        userService.register(login, VALID_PASSWORD);
        final var exception = catchThrowable(() -> userService.register(login, anyString()));
        assertThat(exception).isInstanceOf(ValidationException.class);
        var validationException = (ValidationException) exception;
        assertThat(validationException.getErrors()).contains(Error.of("The username or email already taken"));
        assertThat(validationException.getRedirectPage()).isEqualTo("auth/sign-up");
    }
}