package edu.jpa;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Error al encriptar la contraseña");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(HttpSession session, @RequestBody User user) {
        try {
            Optional<User> foundUser = userService.loginUser(user.getUsername(), user.getPassword());
            if (foundUser.isPresent()) {
                session.setAttribute("user", foundUser.get().getUsername());
                return ResponseEntity.ok("Login exitoso");
            } else {
                return ResponseEntity.status(401).body("Credenciales incorrectas");
            }
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Error al encriptar la contraseña");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate();  // Invalida la sesión
        return ResponseEntity.ok("Sesión cerrada"); // Mensaje de éxito
    }


    @GetMapping("/checkSession")
    public ResponseEntity<String> checkSession(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return ResponseEntity.ok("Sesión activa");
        } else {
            return ResponseEntity.status(401).body("No autorizado");
        }
    }
}
