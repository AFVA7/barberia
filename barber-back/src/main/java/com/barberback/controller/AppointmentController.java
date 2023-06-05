package com.barberback.controller;

import com.barberback.model.dto.*;
import com.barberback.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService iAppointmentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AppointmentDTORequest appointmentDTORequest){
        AppointmentDTOResponse response = iAppointmentService.save(appointmentDTORequest);
        if(appointmentDTORequest!=null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody AppointmentDTORequest appointmentDTORequest,
                                    @PathVariable("id") Long id){
        AppointmentDTOResponse response = iAppointmentService.update(id,appointmentDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @PostMapping("/{id}/hairdresser")
    public ResponseEntity<?> changeHairdresser(@RequestBody HairdresserDTOResponse hairdresserDTOResponse,
                                               @PathVariable("id")Long id){
        AppointmentDTOResponse response = iAppointmentService.changeHairdresser(id, hairdresserDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @PostMapping("/{id}/customer")
    public ResponseEntity<?> changeCustomer(@RequestBody CustomerDTOResponse customerDTOResponse,
                                               @PathVariable("id")Long id){
        AppointmentDTOResponse response = iAppointmentService.changeCustomer(id, customerDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @PostMapping("/{id}/payment")
    public ResponseEntity<?> addPayment(@RequestBody PaymentDTOResponse paymentDTOResponse,
                                        @PathVariable("id") Long id){
        AppointmentDTOResponse response = iAppointmentService.addPayment(id,paymentDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        AppointmentDTOResponse response = iAppointmentService.findById(id);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<AppointmentDTOResponse> responses = iAppointmentService.findAll();
        if(responses!=null){
            return new ResponseEntity<>(responses,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable("status") String status){
        Set<AppointmentDTOResponse> responses = iAppointmentService.findAppointmentsByStatus(status);
        if(responses!=null){
            return new ResponseEntity<>(responses,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //TODO: make a removeAppointment method
}
