package com.avr.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; // Import the Service annotation

import com.avr.library.dto.BorrowingHistoryResponse;
import com.avr.library.entity.User;
import com.avr.library.exception.LibraryException;
import com.avr.library.repository.BorrowingHistoryRepository;
import com.avr.library.repository.UserRepository;
import com.avr.library.util.JwtService;

@Service // Add this annotation to declare it as a Spring Service bean
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private BorrowingHistoryRepository borrowingHistoryRepository;

    @Autowired
    private JwtService jwtService;

    public String addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepo.save(userInfo);
        return "User added to Database";
    }

    public String authenticateUser(String username, String password) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new LibraryException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LibraryException("Invalid credentials");
        }
        Map<String, Object> claims = new HashMap<>();
        return jwtService.createJwtToken(claims, user.getUsername());
    }

    public List<BorrowingHistoryResponse> getBorrowingHistory(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new LibraryException("User not found"));
        return borrowingHistoryRepository.findByUser(user).stream()
            .map(history -> new BorrowingHistoryResponse(history.getBook().getBookId().toString(),
                history.getBook().getTitle(), history.getBorrowDate(), history.getDueDate(),
                history.getReturnDate()))
            .collect(Collectors.toList());
    }
}
