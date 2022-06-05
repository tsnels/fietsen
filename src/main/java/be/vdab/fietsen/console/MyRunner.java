package be.vdab.fietsen.console;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.domain.Geslacht;
import be.vdab.fietsen.repositories.DocentRepository;
import be.vdab.fietsen.services.DocentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;

@Component
public class MyRunner implements CommandLineRunner {

    private final DocentService service;
    private final DocentRepository repository;

    public MyRunner(DocentService service, DocentRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("oke");
//        var lijst = repository.findEmailAdressen();
//        lijst.forEach(adres -> System.out.println(adres + "/n"));

        var nieuweDocent = new Docent("test", "testt", BigDecimal.valueOf(3500), "test@test.be", Geslacht.MAN);
        try {
//            repository.delete(472);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        System.err.println("okej");
    }
}
