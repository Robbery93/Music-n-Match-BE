package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.dto.request.UserPostRequestDto;
import nl.robbertij.matchnmusic.exception.*;
import nl.robbertij.matchnmusic.model.Authority;
import nl.robbertij.matchnmusic.model.User;
import nl.robbertij.matchnmusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new UserNotFoundException("User does not exist");
        }
    }

    public String createStudent(UserPostRequestDto userPostRequest) {
        try {
            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());
            User student = new User();
            student.setUsername(userPostRequest.getUsername());
            student.setPassword(encryptedPassword);
            student.setEnabled(true);
            student.addAuthority("ROLE_STUDENT");
            User newUser = userRepository.save(student);
            return newUser.getUsername();
        }
        catch (Exception e) {
            throw new BadRequestException("Cannot create user.");
        }

    }

    public String createTeacher(UserPostRequestDto userPostRequest) {
        try {
            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());

            User teacher = new User();
            teacher.setUsername(userPostRequest.getUsername());
            teacher.setPassword(encryptedPassword);
            teacher.setEnabled(true);
            teacher.addAuthority("ROLE_TEACHER");

            User newUser = userRepository.save(teacher);
            return newUser.getUsername();
        }
        catch (Exception e) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        }
        else {
            throw new UserNotFoundException(username);
        }
    }

    public void updateUser(String username, User newUser) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        else {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setEnabled(newUser.isEnabled());
            userRepository.save(user);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        else {
            User user = userOptional.get();
            return user.getAuthorities();
        }
    }

    public void addAuthority(String username, String authorityString) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        else {
            User user = optionalUser.get();
            user.addAuthority("ROLE_" + authorityString.toUpperCase());
            userRepository.save(user);
        }
    }

    public void removeAuthority(String username, String authorityString) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        else {
            User user = userOptional.get();
            user.removeAuthority(authorityString);
            userRepository.save(user);
        }
    }

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }

    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            if (isValidPassword(password)) {
                Optional<User> userOptional = userRepository.findById(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                }
                else {
                    throw new UserNotFoundException(username);
                }
            }
            else {
                throw new InvalidPasswordException();
            }
        }
        else {
            throw new NotAuthorizedException();
        }
    }

}