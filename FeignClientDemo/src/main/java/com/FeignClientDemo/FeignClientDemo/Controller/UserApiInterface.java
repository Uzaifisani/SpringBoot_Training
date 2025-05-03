package com.FeignClientDemo.FeignClientDemo.Controller;

import com.FeignClientDemo.FeignClientDemo.dto.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FeignClient(name = "UserApi", url = "https://jsonplaceholder.typicode.com")
public interface UserApiInterface {
    @GetMapping("/users")
    List<Users> getAllUsersApi();
}
