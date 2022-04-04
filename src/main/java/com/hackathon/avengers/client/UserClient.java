package com.hackathon.avengers.client;

import com.hackathon.avengers.dto.IdDTO;
import com.hackathon.avengers.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userservice")
public interface UserClient {

  @GetMapping(path = "/avengers/users/{id}")
  UserDTO getUser(@PathVariable("id") Long id);

  @PutMapping(path = "/avengers/users/{id}")
  UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user);

  @PostMapping(path = "/avengers/users")
  IdDTO createUser(@RequestBody UserDTO user);

}
