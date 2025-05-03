package com.FeignClientDemo.FeignClientDemo.Controller;

import com.FeignClientDemo.FeignClientDemo.dto.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserApiInterface userApiInterface;

    public UserController(UserApiInterface userApiInterface) {
        this.userApiInterface = userApiInterface;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userApiInterface.getAllUsersApi();
    }
}
