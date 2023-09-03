package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.database.repository.AppointmentRepository;
import com.yamacbayin.medjourney.database.specification.AppointmentSpecification;
import com.yamacbayin.medjourney.mapper.AppointmentMapper;
import com.yamacbayin.medjourney.model.requestdto.AppointmentCreationRequestDTO;
import com.yamacbayin.medjourney.model.requestdto.AppointmentFlightRequestDTO;
import com.yamacbayin.medjourney.model.requestdto.AppointmentHotelRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AppointmentResponseDTO;
import com.yamacbayin.medjourney.service.AppointmentService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController extends BaseController<
        AppointmentEntity, AppointmentResponseDTO, AppointmentCreationRequestDTO,
        AppointmentRepository, AppointmentMapper, AppointmentSpecification, AppointmentService> {

    private final AppointmentService appointmentService;

    @Override
    protected AppointmentService getService() {
        return this.appointmentService;
    }


    @PostMapping("/add-flight")
    public ResponseEntity<AppointmentResponseDTO> addFlight(@RequestParam UUID uuid,
                                                            @RequestBody AppointmentFlightRequestDTO flightRequestDTO) {
        return new ResponseEntity<>(getService().addFlightTicket(uuid, flightRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/add-hotel")
    public ResponseEntity<AppointmentResponseDTO> addHotel(@RequestParam UUID uuid,
                                                           @RequestBody AppointmentHotelRequestDTO hotelRequestDTO) {
        return new ResponseEntity<>(getService().addHotelReservation(uuid, hotelRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/cancel/{uuid}")
    public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable UUID uuid) {
        return new ResponseEntity<>(getService().cancelAppointment(uuid), HttpStatus.OK);
    }

}
