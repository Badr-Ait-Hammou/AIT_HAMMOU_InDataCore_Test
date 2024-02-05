package com.indatacore.backend.fuel.web;


import com.indatacore.backend.fuel.domain.Fuel;
import com.indatacore.backend.fuel.dto.LineChartDTO;
import com.indatacore.backend.fuel.dto.PieChartDTO;
import com.indatacore.backend.fuel.services.impl.FuelServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel")
@CrossOrigin("*")
public class FuelController {

    private final FuelServiceImpl fuelService;

    public FuelController(FuelServiceImpl service){
        this.fuelService=service;
    }

    @PostMapping("/save")
    public ResponseEntity<Fuel> saveFuel(@RequestBody Fuel fuel) {
        Fuel savedFuel = fuelService.save(fuel);
        return new ResponseEntity<>(savedFuel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Fuel>> getAllFuel(Pageable pageable) {
        Page<Fuel> fuels = fuelService.getAll(pageable);
        return new ResponseEntity<>(fuels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fuel> getFuelById(@PathVariable Long id) {
        Fuel fuel = fuelService.getTransactionById(id);
        if (fuel != null) {
            return new ResponseEntity<>(fuel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFuel(@RequestParam Long id) {
        if (fuelService.deleteTransaction(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pie/{id}")
    public ResponseEntity<List<PieChartDTO>> getPieCharts(@PathVariable Long id) {
        List<PieChartDTO> pieChartData = fuelService.pieChartDTOS(id);
        return new ResponseEntity<>(pieChartData, HttpStatus.OK);
    }

    @GetMapping("/line/{id}")
    public ResponseEntity<List<LineChartDTO>> getLineCharts(@PathVariable Long id) {
        List<LineChartDTO> lineChartData = fuelService.lineChartDTOS(id);
        return new ResponseEntity<>(lineChartData, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Fuel>> getUserById(@PathVariable Long id, Pageable pageable) {
        Page<Fuel> fuelTransactions = fuelService.getTransactionByUserId(id, pageable);
        if (fuelTransactions != null) {
            return new ResponseEntity<>(fuelTransactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
