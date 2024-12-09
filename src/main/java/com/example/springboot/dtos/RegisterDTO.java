package com.example.springboot.dtos;

import com.example.springboot.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {

}
