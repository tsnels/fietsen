package be.vdab.fietsen.services;

import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.repositories.DocentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class DocentService {

    private final DocentRepository docentRepository;

    public DocentService(DocentRepository docentRepository) {
        this.docentRepository = docentRepository;
    }

    public void opslag(long id, BigDecimal percentage) {
        docentRepository.findById(id).orElseThrow(DocentNietGevondenException::new).
                opslag(percentage);

    }
}