package tesis.offer.controllers;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tesis.offer.models.Offer;
import tesis.offer.models.OfferTypes;
import tesis.offer.repositories.OfferRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static tesis.offer.utils.ParametersDefaultValue.CLASIFICATIONS;
import static tesis.offer.utils.ParametersDefaultValue.OFFER_TYPES;

@RequestMapping
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class OfferController {

    @Value("${products-url}")
    private String productUrl;

    private final OfferRepository repo;

    @PostMapping("/")
    public Offer save(@RequestBody @NotNull Offer offer) {
        return repo.save(offer);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @GetMapping("product/{id}")
    public List<Offer> getByProductId(@PathVariable("id") Integer id) {
        return repo.findByProductID(id);
    }

    @GetMapping("company/{id}")
    public List<Offer> getByCompanyId(@PathVariable("id") Integer id) {
        return repo.findByCompanyID(id);
    }

    @GetMapping("type/{type}")
    public List<Offer> getByClasification(@PathVariable("type") String type) {
        return repo.findByOfferTypeAndAvaliableTrue(OfferTypes.valueOf(type));
    }

    @GetMapping("")
    public List<Offer> getByFilters(@RequestParam(required = false,defaultValue = CLASIFICATIONS) List<String> clasificaciones, @RequestParam(required = false,defaultValue = OFFER_TYPES) List<OfferTypes> tipos) {
        List<Offer> ofertas = repo.findAllByFromDateLessThanEqualAndToDateGreaterThanEqualAndAvaliableTrueAndOfferTypeIn(LocalDateTime.now(),LocalDateTime.now(),tipos);
        List<String> products = getProductsByClasifications(clasificaciones);
        return ofertas.stream()
                .filter(v->products.contains(String.valueOf(v.getProductID())))
                .collect(Collectors.toList());
    }

    public List getProductsByClasifications(List<String> clasificaciones){

        RestTemplate rt = new RestTemplate();
       return rt.getForObject(productUrl +"/product?clasificaciones="+ clasificaciones.toString().replace("[","").replace("]",""),List.class);
     //   return null;
    }

    @GetMapping("startDate/{startDate}")
    public List<Offer> getByStartDate(@PathVariable("startDate") String startDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(startDate, formatter);
        return repo.findByFromDateGreaterThanEqualAndAvaliableTrue(dateTime);
    }

    @GetMapping("startDate/{startDate}/endDate/{endDate}")
    public List<Offer> getByStartDateAndEndDate(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDate, formatter);
        return repo.findByFromDateLessThanEqualAndToDateGreaterThanEqualAndAvaliableTrue(startTime, endTime);
    }

    @GetMapping("date/{date}")
    public List<Offer> getBySpecificDate(@PathVariable("date") String specificDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(specificDate, formatter);
        return repo.findBySpecificDate(dateTime);
    }

    @GetMapping("user/{user}")
    public List<Offer> getBySpecificDate(@PathVariable("user") Long userId) {
        return repo.findAvailableOfferForUser(userId);
    }

    @GetMapping("city/{city}")
    public List<Offer> getByCity(@PathVariable("city") String city) {
        return repo.findByCity(city);
    }


}
