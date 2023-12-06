package com.brockhaus.weinservice.controller;

import com.brockhaus.weinservice.model.Wein;
import com.brockhaus.weinservice.services.WeinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weine")
@RequiredArgsConstructor
public class WeinController {

    private final WeinService weinService;

    @GetMapping("/{id}")
    public Wein getWeinById(@PathVariable Long id) {
        return weinService.getWeinById(id);
    }

    @GetMapping
    public List<Wein> getAllWein() {
        return weinService.getAllWein();
    }

    @PostMapping
    public Wein saveWein(@RequestBody Wein wein) {
        return weinService.saveWein(wein);
    }

    @DeleteMapping("/{id}")
    public void deleteWeinById(@PathVariable Long id) {
        weinService.deleteWeinById(id);
    }

    @DeleteMapping("/all")
    public void deleteWeinAll() {
        weinService.deleteAllWein();
    }

    @PutMapping("/{id}")
    public Wein updateWein(@RequestBody Wein wein, @PathVariable Long id) {
        return weinService.updateWein(wein, id);
    }
}

