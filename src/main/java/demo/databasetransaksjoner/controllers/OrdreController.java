package demo.databasetransaksjoner.controllers;

import demo.databasetransaksjoner.models.Ordre;
import demo.databasetransaksjoner.repositories.OrdreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdreController {

    private final OrdreRepository ordreRepository;

    @PostMapping("/lagre")
    public Boolean postOrdre(@RequestBody final Ordre ordre) {
        return ordreRepository.createOrdre(ordre);
    }
}
