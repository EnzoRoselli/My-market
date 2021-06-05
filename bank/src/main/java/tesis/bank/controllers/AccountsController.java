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
import tesis.bank.models.Account;
import tesis.bank.repositories.AccountRepository;

@RequestMapping("account")
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class AccountsController {
    private final AccountRepository repo;

    @PostMapping
    public Account save(@RequestBody @NotNull Account account) {
        return repo.save(account);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }
}
