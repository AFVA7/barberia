package com.barberback.controller;

import com.barberback.model.dto.UserDTORequest;
import com.barberback.model.dto.UserDTOResponse;
import com.barberback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTORequest userDTORequest){
        UserDTOResponse response = userService.updateData(id,userDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<UserDTOResponse> responses = userService.findAllUsers();
        if(responses!=null){
            return new ResponseEntity<>(responses, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
