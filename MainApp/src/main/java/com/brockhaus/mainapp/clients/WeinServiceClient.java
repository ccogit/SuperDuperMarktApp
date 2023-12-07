package com.brockhaus.mainapp.clients;

import com.brockhaus.mainapp.model.Wein;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("WEIN-SERVICE")
@Service
@RestController
public interface WeinServiceClient {
    @GetMapping("/weine")
    List<Wein> getWeine();

    @GetMapping("/weine/{id}")
    Wein getWeinById(@PathVariable Long id);

    @PostMapping("/weine")
    Wein saveWein(@RequestBody Wein wein);

    @DeleteMapping("/weine/{id}")
    void deleteWein(@PathVariable Long id);

    @DeleteMapping("/weine/all")
    void deleteWeinAll();

    @PutMapping("/weine/{id}")
    void updateWein(@RequestBody Wein wein, @PathVariable Long id);

}
