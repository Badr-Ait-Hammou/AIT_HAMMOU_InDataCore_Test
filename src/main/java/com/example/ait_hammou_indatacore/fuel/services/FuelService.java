package com.indatacore.backend.fuel.services;



import com.indatacore.backend.fuel.domain.Fuel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface FuelService {
    Fuel save(Fuel fuel);
    Page<Fuel> getAll(Pageable pageable);
    Fuel getTransactionById(Long id);
    Boolean deleteTransaction(Long fuel);
}
