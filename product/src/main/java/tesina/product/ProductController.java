package tesina.product;

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

@RequestMapping
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repo;

    @GetMapping("/name/{name}")
    public Product productList(@PathVariable("name") String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product with given name does not exist."));
    }

    @GetMapping("/id/{id}")
    public Product productList(@PathVariable("id") Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with given id does not exist."));
    }

    @PostMapping
    public Product save(@RequestBody @NotNull Product product) {
        return repo.save(product);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

}
