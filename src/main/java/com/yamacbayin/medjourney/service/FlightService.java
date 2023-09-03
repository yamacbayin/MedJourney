package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.AirplaneEntity;
import com.yamacbayin.medjourney.database.entity.FlightEntity;
import com.yamacbayin.medjourney.database.entity.FlightSeatEntity;
import com.yamacbayin.medjourney.database.repository.FlightRepository;
import com.yamacbayin.medjourney.database.specification.FlightSpecification;
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
        FlightEntity, FlightResponseDTO, FlightRequestDTO, FlightRepository, FlightMapper, FlightSpecification> {

    private final FlightRepository flightRepository;
    private final FlightSpecification flightSpecification;

    private final AirplaneService airplaneService;

    @Override
    protected String getEntityName() {
        return "Flight";
    }

    @Override
    protected FlightMapper getMapper() {
        return FlightMapper.INSTANCE;
    }

    @Override
    protected FlightRepository getRepository() {
        return this.flightRepository;
    }

    @Override
    protected FlightSpecification getSpecification() {
        return flightSpecification;
    }

    @Override
    @Transactional
    public FlightResponseDTO save(FlightRequestDTO flightRequestDTO) {
        AirplaneEntity airplane = airplaneService.getEntityByUuid(flightRequestDTO.getAirplaneUuid());

        FlightEntity flight = FlightMapper.INSTANCE.requestDtoToEntity(flightRequestDTO);
        flight.setAirplane(airplane);
        return getMapper().entityToResponseDto(getRepository().save(flight));
    }

    @Override
    @Transactional
    public FlightResponseDTO update(UUID uuid, FlightRequestDTO flightRequestDTO) {

        FlightEntity flight = this.getEntityByUuid(uuid);


        FlightEntity updatedFlight = getMapper().updateEntityFromRequestDTO(flightRequestDTO, flight);

        if (flightRequestDTO.getAirplaneUuid() != null
                && flight.getAirplane().getUuid() != flightRequestDTO.getAirplaneUuid()) {

            AirplaneEntity airplane = airplaneService.getEntityByUuid(flightRequestDTO.getAirplaneUuid());


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
