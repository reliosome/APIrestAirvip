package com.airvip.APIrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // GET : Retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET : Retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : Register a new user with hashed password
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        return userRepository.save(user);
    }

    // POST : Authenticate user and create session
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest, HttpServletRequest request) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getMotDePasse(), user.get().getMotDePasse())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user.get());
            return ResponseEntity.ok("Authentication successful, session created.");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // GET : Retrieve logged-in user profile
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return ResponseEntity.status(401).build();
        }
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(user);
    }

    // POST : Logout user and invalidate session
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("User logged out successfully.");
    }
}
