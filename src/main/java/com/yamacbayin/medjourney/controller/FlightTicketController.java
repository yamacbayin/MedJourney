package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.FlightTicketEntity;
import com.yamacbayin.medjourney.database.repository.FlightTicketRepository;
import com.yamacbayin.medjourney.database.specification.FlightTicketSpecification;
import com.yamacbayin.medjourney.mapper.FlightTicketMapper;
import com.yamacbayin.medjourney.model.requestdto.FlightTicketRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightTicketResponseDTO;
import com.yamacbayin.medjourney.service.FlightTicketService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flight-tickets")
@RequiredArgsConstructor
public class FlightTicketController extends BaseController<
        FlightTicketEntity, FlightTicketResponseDTO, FlightTicketRequestDTO,
        FlightTicketRepository, FlightTicketMapper, FlightTicketSpecification, FlightTicketService> {

    private final FlightTicketService flightTicketService;

    @Override
    protected FlightTicketService getService() {
        return this.flightTicketService;
    }
}
