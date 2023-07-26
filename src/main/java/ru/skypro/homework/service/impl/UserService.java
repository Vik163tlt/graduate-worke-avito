package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDetailsDto;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final ImageService imageService;

    private final UserRepository userRepository;

    public UserService(ImageService imageService, UserRepository userRepository) {
        this.imageService = imageService;
        this.userRepository = userRepository;
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByLogin(authentication.getName()).orElseThrow();
    }


    public void updatePassword(NewPasswordDto newPasswordDto) {
        User user = getUser();
        user.setPassword(newPasswordDto.getNewPassword());
        userRepository.save(user);
    }

    public void updateUser(UpdateUserDto updateUser) {
        User user = getUser();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
    }

    public void updateUserImage(MultipartFile file) {
        User user = getUser();
        user.setImagePath(imageService.updateUserImage(file));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username).orElseThrow();
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user" + username);
        }
        UserDetailsDto userDetailsDto = new UserDetailsDto(user.getLogin(), user.getPassword(), user.getUserId(), user.getRoleDto());
        return new SecurityUserPrincipal(userDetailsDto);
    }

    public boolean userExists(String username) {
        userRepository.findUserByLogin(username).isPresent();
        return true;
    }
}