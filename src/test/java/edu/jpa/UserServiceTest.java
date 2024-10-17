package edu.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks antes de cada prueba
    }

    @Test
    void testRegisterUser() throws NoSuchAlgorithmException {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("hashedPassword");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser("testUser", "testPassword");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertNotNull(result.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testLoginUserSuccess() throws NoSuchAlgorithmException {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword(PasswordUtils.hashPassword("testPassword"));

        when(userRepository.findByUsernameAndPassword("testUser", user.getPassword()))
                .thenReturn(Optional.of(user));

        Optional<User> result = userService.loginUser("testUser", "testPassword");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUsername());
    }

    @Test
    void testLoginUserFailure() throws NoSuchAlgorithmException {
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        Optional<User> result = userService.loginUser("wrongUser", "wrongPassword");

        assertFalse(result.isPresent());
    }
}
