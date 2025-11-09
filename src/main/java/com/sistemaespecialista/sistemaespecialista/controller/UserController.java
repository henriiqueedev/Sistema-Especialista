package com.sistemaespecialista.sistemaespecialista.controller;

import com.sistemaespecialista.sistemaespecialista.entities.UserEntity;
import com.sistemaespecialista.sistemaespecialista.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> salvarUsuario(@RequestBody UserEntity user){
        try{
            UserEntity userSalvo = userService.salvarUsuario(user);
            return ResponseEntity.ok(userSalvo);
        }catch (Exception e){
            System.out.println("Erro ao salvar o usuario:" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
}
