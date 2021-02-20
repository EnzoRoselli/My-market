package tesis.offer.controllers;

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
import tesis.offer.models.Offer;
import tesis.offer.repositories.OfferRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping
@RestController
@Slf4j
@XRayEnabled
@RequiredArgsConstructor
public class OfferController {

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

    @GetMapping("clasification/{clasification}")
    public List<Offer> getByClasification(@PathVariable("clasification") String clasification) {
        return repo.findByClasificationAndAvaliableTrue(clasification);
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

    @GetMapping("city/{city}")
    public List<Offer> getByCity(@PathVariable("city") String city) {
        return repo.findByCity(city);
    }


}