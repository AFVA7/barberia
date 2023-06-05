package com.barberback.controller;

import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.RecordDTORequest;
import com.barberback.model.dto.RecordDTOResponse;
import com.barberback.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private IRecordService iRecordService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RecordDTORequest recordDTORequest){
        RecordDTOResponse response = iRecordService.save(recordDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<RecordDTOResponse> response = iRecordService.findAll();
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        RecordDTOResponse response = iRecordService.findById(id);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/add/appointment")
    public ResponseEntity<?> addAppointment(@PathVariable("id") Long id,
                                            @RequestBody AppointmentDTOResponse appointmentDTOResponse){

        RecordDTOResponse response = iRecordService.addAppointment(id,appointmentDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/remove/appointment")
    public ResponseEntity<?> removeAppointment(@PathVariable("id") Long id,
                                            @RequestBody AppointmentDTOResponse appointmentDTOResponse){

        RecordDTOResponse response = iRecordService.removeAppointment(id,appointmentDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
