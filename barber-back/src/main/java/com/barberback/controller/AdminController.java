package com.barberback.controller;

import com.barberback.model.dto.AdminDTORequest;
import com.barberback.model.dto.AdminDTOResponse;
import com.barberback.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AdminDTORequest adminDTORequest){
        AdminDTOResponse response = iAdminService.save(adminDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody AdminDTOResponse adminDTOResponse){
        AdminDTOResponse response = iAdminService.update(id,adminDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        if(iAdminService.remove(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        AdminDTOResponse response = iAdminService.findById(id);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<AdminDTOResponse> responses = iAdminService.findAll();
        if(responses!=null){
            return new ResponseEntity<>(responses,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
