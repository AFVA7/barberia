package com.barberback.service;

import com.barberback.model.Appointment;
import com.barberback.model.Customer;
import com.barberback.model.Record;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.RecordDTORequest;
import com.barberback.model.dto.RecordDTOResponse;
import com.barberback.repository.RecordRepository;
import com.barberback.service.mapper.RecordDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecordServiceImp implements IRecordService{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private RecordDTOMapper recordDTOMapper;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IAppointmentService iAppointmentService;
    @Override
    public RecordDTOResponse save(RecordDTORequest recordDTORequest) {
        Record record = new Record(
                null,
                appointments(recordDTORequest.appointments()),
                customer(recordDTORequest.customer().id())
        );
        LOGGER.info("RECORD: record saved successfully");
        return recordDTOMapper.apply(record);
    }

    @Override
    public RecordDTOResponse findById(Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        if(record!=null){
            LOGGER.info("RECORD: record found successfully");
            return recordDTOMapper.apply(record);
        }else{
            LOGGER.error("RECORD: the record doesn't exist");
            return null;
        }
    }

    @Override
    public Set<RecordDTOResponse> findAll() {
        List<Record> records = recordRepository.findAll();
        if(records!=null){
            LOGGER.info("RECORD: records found successfully");
            return records.stream().map(record -> {
                return recordDTOMapper.apply(record);
            }).collect(Collectors.toSet());
        }else{
            LOGGER.error("RECORD: there aren't records");
            return null;
        }
    }

    private Set<Appointment> appointments(Set<AppointmentDTOResponse> appointments){
        if(appointments!=null){
            return appointments.stream().map(appointmentDTOResponse -> {
                return appointment(appointmentDTOResponse.id());
            }).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
    private Customer customer(Long id){
        return iCustomerService.findCustomerById(id);
    }
    private Appointment appointment(Long id){
        return iAppointmentService.findAppointmentById(id);
    }
    @Override
    public RecordDTOResponse addAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse) {
        Record record = recordRepository.findById(id).orElse(null);
        if(record!=null){
            Appointment appointment = iAppointmentService.findAppointmentById(appointmentDTOResponse.id());
            if(appointment!=null){
                record.getAppointments().add(appointment);
                LOGGER.info("RECORD: appointment added successfully");
                return recordDTOMapper.apply(recordRepository.saveAndFlush(record));
            }else{
                LOGGER.error("RECORD: the appointment doesn't exist, therefore is not possible add it to the record");
                return null;
            }
        }else{
            LOGGER.error("RECORD: the record doesn't exist, therefore is not possible add the appointment");
            return null;
        }
    }

    @Override
    public RecordDTOResponse removeAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse) {
        Record record = recordRepository.findById(id).orElse(null);
        if(record!=null){
            Appointment appointment = iAppointmentService.findAppointmentById(appointmentDTOResponse.id());
            if(appointment!=null){
                record.getAppointments().remove(appointment);
                LOGGER.info("RECORD: appointment removed successfully");
                return recordDTOMapper.apply(recordRepository.saveAndFlush(record));
            }else{
                LOGGER.error("RECORD: the appointment doesn't exist, therefore is not possible remove it to the record");
                return null;
            }
        }else{
            LOGGER.error("RECORD: the record doesn't exist, therefore is not possible remove the appointment");
            return null;
        }
    }

    @Override
    public RecordDTOResponse updateCustomer(Long id, CustomerDTOResponse customerDTOResponse) {
        Record record = recordRepository.findById(id).orElse(null);
        if(record!=null){
            Customer c = iCustomerService.findCustomerById(customerDTOResponse.id());
            if(c!=null){
                record.setCustomer(c);
                recordRepository.saveAndFlush(record);
                iCustomerService.changeRecord(c.getId(),record);
                LOGGER.info("RECORD: record's customer changed successfully");
                return recordDTOMapper.apply(record);
            }else{
                LOGGER.error("RECORD: the customer doesn't exist, therefore is not possible update it into the record");
                return null;
            }
        }else{
            LOGGER.error("RECORD: the record doesn't exist, therefore is not possible update the record's customer");
            return null;
        }
    }
}
