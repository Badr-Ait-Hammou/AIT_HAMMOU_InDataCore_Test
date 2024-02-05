package com.indatacore.backend.fuel.services.impl;

import com.indatacore.backend.fuel.domain.Fuel;
import com.indatacore.backend.fuel.dto.LineChartDTO;
import com.indatacore.backend.fuel.dto.PieChartDTO;
import com.indatacore.backend.fuel.repository.FuelRepository;
import com.indatacore.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuelServiceImpl {
    private final FuelRepository fuelRepository;

    private final UserRepository userRepository;


    public Fuel save(Fuel fuel) {
        log.info("type {} total {}",fuel.getType(),fuel.getTotale());
            double litres = fuel.getTotale() / 14.3;
            fuel.setLitre(litres);
            fuel.setDate(new Date());
            fuel.setType(fuel.getType());
            fuel.setPrice(14.3);
            return fuelRepository.save(fuel);
    }


    public Page<Fuel> getAll(Pageable pageable) {
        log.info("Get all fuel page {} size {}", pageable.getPageNumber(), pageable.getPageSize());
        return fuelRepository.findAll(pageable);
    }

    public Fuel getTransactionById(Long id) {
        if (fuelRepository.existsById(id)) {
            log.info("Transaction with id {}", id);
            return fuelRepository.findById(id).orElse(null);
        } else {
            log.warn("Transaction with id {} not found", id);
            return null;
        }
    }

    public Boolean deleteTransaction(Long id) {
        if (fuelRepository.existsById(id)) {
            log.info("Deleting transaction with id = {}", id);
            fuelRepository.deleteById(id);
            return true;
        }
        return false;
    }

       public List<PieChartDTO> pieChartDTOS(Long id) {
        List<ArrayList> result = fuelRepository.getChart(id);

        return result.stream()
                .map(row -> {
                    String type = (String) row.get(0);
                    Double litres = (Double) row.get(1);
                    Double totale = (Double) row.get(2);
                    return new PieChartDTO(type, litres, totale);
                })
                .collect(Collectors.toList());
    }

    public List<LineChartDTO> lineChartDTOS(Long id) {
        List<ArrayList> result = fuelRepository.getLineChart(id);
        return result.stream()
                .map(row -> {
                    String type = (String) row.get(0);
                    Double litres = (Double) row.get(1);
                    Double totale = (Double) row.get(2);
                    Date date = (Date) row.get(3);
                    return new LineChartDTO(type, litres, totale,date);
                })
                .collect(Collectors.toList());
    }

    public Page<Fuel> getTransactionByUserId(Long id, Pageable pageable) {
        if (userRepository.existsById(id)) {
            log.info("User Transaction with id {}", id);
            return fuelRepository.getFuelByUserId(id, pageable);
        } else {
            log.warn("User Transaction with id {} not found", id);
            return null;
        }
    }

}
