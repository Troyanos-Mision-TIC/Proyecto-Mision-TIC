package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.model.MovimientoDinero;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class MovimientoDineroService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public MovimientoDineroService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<MovimientoDinero> consultarTodosMovimientos() {
        return transactionRepository.findAll();
    }
    
    public Optional<MovimientoDinero> consultarMovimiento(long id) {
        return transactionRepository.findById(id);
    }
    
    public MovimientoDinero guardarMovimiento(MovimientoDinero movimiento){
        return transactionRepository.save(movimiento);
    }

    public MovimientoDinero editarMovimiento(MovimientoDinero movement, Map<String, Object> fields) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(MovimientoDinero.class, key);
            if (field != null) {
                field.setAccessible(true);
                if (key.equals("usuarioEncargado")) {
                    Empleado user = userRepository.findById((long)(int) value).get();
                    ReflectionUtils.setField(field, movement, user);
                } else {
                    ReflectionUtils.setField(field, movement, value);
                }
            }
        });
        return movement;
    }
    
    public void eliminarMovimiento(long id) {
        transactionRepository.deleteById(id);
    }
}
