package com.example.shop.service.impl;

import com.example.shop.entity.User;
import com.example.shop.exception.NotFoundException;
import com.example.shop.model.UserModel;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean add(UserModel userModel) throws Exception {
        User user = new ModelMapper().map(userModel, User.class);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        repository.save(user);
        return true;
    }

    @Override
    public boolean update(UserModel userModel) throws Exception {
        Optional<User> userById = repository.findById(userModel.getUserId());
        if (userById.isPresent()) {
            repository.save(User.builder()
                    .username(userModel.getUsername())
                    .email(userModel.getEmail())
                    .role(userModel.getRole())
                    .build()
            );
        } else {
            throw new NotFoundException("user not found id:" + userModel.getUserId());
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    public UserModel find(int id) throws Exception {
        return null;
    }

    @Override
    public List<UserModel> getAll(int start, int end) throws Exception {

        return new ArrayList<>();
    }

    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        throw new RuntimeException("we");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = repository.getUserByUsername(username);
        return userByUsername.map(UserDetailsImpl::new).orElseThrow((() -> new UsernameNotFoundException("user not found " + username)));
    }
}
