package com.barberback.controller;

import com.barberback.model.dto.PaymentDTORequest;
import com.barberback.model.dto.PaymentDTOResponse;
import com.barberback.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/payment")
@RestController
public class PaymentController {
    @Autowired
    private IPaymentService iPaymentService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaymentDTORequest paymentDTORequest){
        PaymentDTOResponse response = iPaymentService.save(paymentDTORequest);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody PaymentDTOResponse paymentDTOResponse){

        PaymentDTOResponse response = iPaymentService.update(id,paymentDTOResponse);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/update/{id}/status/{paymentStatus}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id,
                                          @PathVariable("paymentStatus") String status){

        PaymentDTOResponse response = iPaymentService.updateStatus(id,status);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update/{id}/method/{paymentMethod}")
    public ResponseEntity<?> updateMethod(@PathVariable("id") Long id,
                                          @PathVariable("paymentMethod") String method){

        PaymentDTOResponse response = iPaymentService.updateMethod(id,method);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        PaymentDTOResponse response = iPaymentService.findById(id);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        Set<PaymentDTOResponse> responses = iPaymentService.findAll();
        if(responses!=null){
            return new ResponseEntity<>(responses,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        if(iPaymentService.remove(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
