package edu.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String rawPassword) throws NoSuchAlgorithmException {
        if (userRepository.findByUsernameAndPassword(username, rawPassword).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        String hashedPassword = PasswordUtils.hashPassword(rawPassword);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String rawPassword) throws NoSuchAlgorithmException {
        String hashedPassword = PasswordUtils.hashPassword(rawPassword);
        return userRepository.findByUsernameAndPassword(username, hashedPassword);
    }
}
