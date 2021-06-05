package tesis.subscription;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.spi.CriteriaQueryTupleTransformer;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @GetMapping
    public List<String> getMailInfo(){
       return new ArrayList<>(repo.takeMailInformation());
    }
}
