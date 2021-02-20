package tesis.subscription;

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

@RequestMapping
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class SubsController {
    private final SubsRepository repo;

    @PostMapping
    public Subscription save(@RequestBody @NotNull Subscription subscription) {
        return repo.save(subscription);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }
}
