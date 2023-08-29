package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.AirplaneEntity;
import com.yamacbayin.medjourney.database.entity.FlightEntity;
import com.yamacbayin.medjourney.database.entity.FlightSeatEntity;
import com.yamacbayin.medjourney.database.repository.FlightRepository;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.mapper.FlightMapper;
import com.yamacbayin.medjourney.model.requestdto.FlightRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService extends BaseService<
        FlightEntity, FlightResponseDTO, FlightRequestDTO, FlightRepository, FlightMapper> {

    private final FlightRepository flightRepository;

    private final AirplaneService airplaneService;

    @Override
    protected FlightMapper getMapper() {
        return FlightMapper.INSTANCE;
    }

    @Override
    protected FlightRepository getRepository() {
        return this.flightRepository;
    }

    @Override
    @Transactional
    public FlightResponseDTO save(FlightRequestDTO flightRequestDTO) {
        AirplaneEntity airplane = airplaneService.getEntityByUuid(flightRequestDTO.getAirplaneUuid());

        if (airplane == null) {
            throw new InvalidUuidException("Airplane UUID is not valid.");
        }

        FlightEntity flight = FlightMapper.INSTANCE.requestDtoToEntity(flightRequestDTO);
        flight.setAirplane(airplane);
        return getMapper().entityToResponseDto(getRepository().save(flight));
    }

    @Override
    @Transactional
    public FlightResponseDTO update(UUID uuid, FlightRequestDTO flightRequestDTO) {


        FlightEntity flight = this.getEntityByUuid(uuid);

        if (flight == null) {
            throw new InvalidUuidException("Flight UUID is not valid.");
        }

        FlightEntity updatedFlight = getMapper().updateEntityFromRequestDTO(flightRequestDTO, flight);

        if (flightRequestDTO.getAirplaneUuid() != null
                && flight.getAirplane().getUuid() != flightRequestDTO.getAirplaneUuid()) {

            AirplaneEntity airplane = airplaneService.getEntityByUuid(flightRequestDTO.getAirplaneUuid());

            if (airplane == null) {
                throw new InvalidUuidException("Airplane UUID is not valid.");
            }

            updatedFlight.setAirplane(airplane);

        }

        return getMapper().entityToResponseDto(getRepository().save(updatedFlight));
    }

    public void removeSeatFromFlight(FlightEntity flight, FlightSeatEntity seat) {
        flight.getSeatList().remove(seat);
        seat.setFlight(null);

        flightRepository.save(flight);
    }

}
