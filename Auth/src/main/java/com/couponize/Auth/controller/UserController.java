package com.couponize.Auth.controller;


import com.couponize.Auth.controller.model.SignupRequest;
import com.couponize.Auth.model.User;
import com.couponize.Auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService= userService;
    }

    @RequestMapping(value = "signup",method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated final SignupRequest request) {
        User user = new User();
        user.setCompanySize(request.getCompanySize());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return userService.createUser(user);
    }


}
