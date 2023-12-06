package com.brockhaus.kaeseservice.controller;

import com.brockhaus.kaeseservice.model.Kaese;
import com.brockhaus.kaeseservice.services.KaeseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kaese")
@RequiredArgsConstructor
public class KaeseController {

    private final KaeseService kaeseService;

    @GetMapping("/{id}")
    public Kaese getKaeseById(@PathVariable Long id) {
        return kaeseService.getKaeseById(id);
    }

    @GetMapping
    public List<Kaese> getAllKaese() {
        return kaeseService.getAllKaese();
    }

    @PostMapping
    public Kaese saveKaese(@RequestBody Kaese kaese) {
        return kaeseService.saveKaese(kaese);
    }

    @DeleteMapping("/{id}")
    public void deleteKaeseById(@PathVariable Long id) {
        kaeseService.deleteKaeseById(id);
    }

    @DeleteMapping("/all")
    public void deleteKaeseAll() {
        kaeseService.deleteAllKaese();
    }

    @PutMapping("/{id}")
    public Kaese updateKaese(@RequestBody Kaese kaese, @PathVariable Long id) {
        return kaeseService.updateKaese(kaese, id);
    }

}

