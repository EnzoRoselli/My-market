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
import tesis.company.models.Branch;
import tesis.company.repositories.BranchRepository;

@RequestMapping("/branch")
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class BranchController {

    private final BranchRepository repo;

    @PostMapping
    public Branch save(@RequestBody @NotNull Branch branch) {
        return repo.save(branch);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @GetMapping("id/{id}")
    public Branch getById(@PathVariable("id") Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not not found"));
    }
}
