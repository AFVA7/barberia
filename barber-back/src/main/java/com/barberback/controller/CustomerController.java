package com.barberback.controller;

import com.barberback.model.dto.*;
import com.barberback.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CustomerDTORequest customerDTORequest){
        CustomerDTOResponse response = iCustomerService.save(customerDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/update")
    public ResponseEntity<?> update(@RequestBody CustomerDTOResponse customerDTOResponse,
                                    @PathVariable("id") Long id){
        CustomerDTOResponse response = iCustomerService.update(id,customerDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        CustomerDTOResponse response = iCustomerService.findById(id);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<CustomerDTOResponse> responses = iCustomerService.findAll();
        if(responses!=null){
            return new ResponseEntity<>(responses, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}/remove")
public ResponseEntity<?> remove(@PathVariable("id")Long id){
        if(iCustomerService.remove(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/update/record")
    public ResponseEntity<?> changeRecord(@PathVariable("id") Long id,
                                          @RequestBody RecordDTOResponse recordDTOResponse){
        CustomerDTOResponse response = iCustomerService.changeRecord(id,recordDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
