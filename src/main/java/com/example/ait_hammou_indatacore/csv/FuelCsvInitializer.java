package com.indatacore.backend.csv;

import com.indatacore.backend.fuel.domain.Fuel;
import com.indatacore.backend.fuel.services.impl.FuelServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@Component
public class FuelCsvInitializer implements ApplicationRunner {
    private final FuelServiceImpl fuelService;

    public FuelCsvInitializer(FuelServiceImpl fuel) {
        this.fuelService = fuel;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("fuels.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Fuel fuel = new Fuel();
                fuel.setType(data[0]);
                fuel.setPrice(Double.parseDouble(data[1]));
                fuel.setTotale(Double.parseDouble(data[2]));
                fuel.setLitre(Double.parseDouble(data[3]));
                fuel.setDate(new Date());
                fuelService.save(fuel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
