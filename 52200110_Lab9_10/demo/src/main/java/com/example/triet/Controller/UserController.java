package com.example.triet.Controller;

import com.example.triet.Model.User;
import com.example.triet.Repository.UserRepository;
import com.example.triet.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class UserController {
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    // POST: Register a new account
    @PostMapping("/register")
    public void createUser(@RequestBody User User) {
        String hashedPassword = bCryptPasswordEncoder.encode(User.getPassword());
        User.setPassword(hashedPassword);
        UserRepository.save(User);
    }

    // POST: User login
    @PostMapping("/login")
    public String loginUser(@RequestBody User User) {
        User user = UserRepository.findUserByEmail(User.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // Kiểm tra mật khẩu
        if (bCryptPasswordEncoder.matches(User.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(User);
            return token;
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }
}