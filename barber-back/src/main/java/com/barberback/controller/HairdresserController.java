package com.barberback.controller;

import com.barberback.model.dto.*;
import com.barberback.service.IHairdresserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/hairdresser")
public class HairdresserController {
    @Autowired
    private IHairdresserService iHairdresserService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HairdresserDTORequest hairdresserDTORequest){
        HairdresserDTOResponse response = iHairdresserService.save(hairdresserDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody HairdresserDTOResponse hairdresserDTOResponse){
        HairdresserDTOResponse response = iHairdresserService.update(hairdresserDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        HairdresserDTOResponse response = iHairdresserService.findById(id);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<HairdresserDTOResponse> response = iHairdresserService.findAll();
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        if(iHairdresserService.remove(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/add/appointment")
    public ResponseEntity<?> addAppointment(@PathVariable("id") Long id,
                                            @RequestBody AppointmentDTOResponse appointmentDTOResponse){
        HairdresserDTOResponse response = iHairdresserService.addAppointment(id,appointmentDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
