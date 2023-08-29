package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.FlightEntity;
import com.yamacbayin.medjourney.database.entity.FlightSeatEntity;
import com.yamacbayin.medjourney.database.repository.FlightSeatRepository;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.mapper.FlightSeatMapper;
import com.yamacbayin.medjourney.model.requestdto.FlightSeatRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightSeatResponseDTO;
import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import com.yamacbayin.medjourney.util.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightSeatService extends BaseService<
        FlightSeatEntity, FlightSeatResponseDTO, FlightSeatRequestDTO, FlightSeatRepository, FlightSeatMapper> {

    private final FlightSeatRepository flightSeatRepository;

    private final FlightService flightService;

    @Override
    protected FlightSeatMapper getMapper() {
        return FlightSeatMapper.INSTANCE;
    }

    @Override
    protected FlightSeatRepository getRepository() {
        return this.flightSeatRepository;
    }


    @Override
    public FlightSeatResponseDTO save(FlightSeatRequestDTO flightSeatRequestDTO) {
        FlightEntity flight = flightService.getEntityByUuid(flightSeatRequestDTO.getFlightUuid());

        if (flight == null) {
            throw new InvalidUuidException("Hospital UUID is not valid.");
        }

        FlightSeatEntity seat = FlightSeatMapper.INSTANCE.requestDtoToEntity(flightSeatRequestDTO);
        seat.setFlight(flight);
        return getMapper().entityToResponseDto(getRepository().save(seat));
    }

    @Override
    public FlightSeatResponseDTO update(UUID uuid, FlightSeatRequestDTO flightSeatRequestDTO) {

        FlightSeatEntity seat = this.getEntityByUuid(uuid);

        if (seat == null) {
            throw new InvalidUuidException("Doctor UUID is not valid.");
        }

        if (flightSeatRequestDTO.getFlightUuid() != null
                && seat.getFlight().getUuid() != flightSeatRequestDTO.getFlightUuid()) {

            FlightEntity flight = flightService.getEntityByUuid(flightSeatRequestDTO.getFlightUuid());

            if (flight == null) {
                throw new InvalidUuidException("Hospital UUID is not valid.");
            }

            flightService.removeSeatFromFlight(seat.getFlight(), seat);
            seat.setFlight(flight);
        }

        seat = getMapper().updateEntityFromRequestDTO(flightSeatRequestDTO, seat);

        return getMapper().entityToResponseDto(getRepository().save(seat));
    }

    //TODO: update seat status by uuid
    public FlightSeatEntity updateSeatStatus(FlightSeatEntity seat, SeatStatusEnum status){
        seat.setStatus(status);
        return getRepository().save(seat);
    }
}
