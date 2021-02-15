package tesis.company.controllers;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.company.models.Company;
import tesis.company.repositories.CompanyRepository;

@RequestMapping
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository repo;

    @PostMapping
    public Company save(@RequestBody @NotNull Company company) {
        return repo.save(company);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @GetMapping("id/{id}")
    public Company getById(@PathVariable("id") Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not not found"));
    }
}
