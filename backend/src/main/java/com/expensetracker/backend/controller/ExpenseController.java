package com.expensetracker.backend.controller;

import com.expensetracker.backend.model.Expense;
import com.expensetracker.backend.model.User;
import com.expensetracker.backend.repository.ExpenseRepository;
import com.expensetracker.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseController(ExpenseRepository expenseRepository,
                             UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    private String getCurrentUserId() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElse(null);
    }

    // Lấy tất cả expense của user
    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(expenseRepository.findByUserId(userId));
    }

    // Thêm expense mới
    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        expense.setUserId(getCurrentUserId());
        return ResponseEntity.ok(expenseRepository.save(expense));
    }

    // Sửa expense
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody Expense updated) {
        Optional<Expense> opt = expenseRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Expense expense = opt.get();
        if (!expense.getUserId().equals(getCurrentUserId())) {
            return ResponseEntity.status(403).body(Map.of("message", "Không có quyền"));
        }
        expense.setTitle(updated.getTitle());
        expense.setAmount(updated.getAmount());
        expense.setCategory(updated.getCategory());
        expense.setDate(updated.getDate());
        expense.setNote(updated.getNote());
        return ResponseEntity.ok(expenseRepository.save(expense));
    }

    // Xóa expense
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Expense> opt = expenseRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!opt.get().getUserId().equals(getCurrentUserId())) {
            return ResponseEntity.status(403).body(Map.of("message", "Không có quyền"));
        }
        expenseRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa"));
    }
}