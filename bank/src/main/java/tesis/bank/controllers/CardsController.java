package tesis.bank.controllers;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.bank.models.Card;
import tesis.bank.repositories.CardsRepository;

@RequestMapping("card")
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository repo;

    @PostMapping
    public Card save(@RequestBody @NotNull Card card) {
        return repo.save(card);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

}
