package com.brockhaus.mainapp.clients;

import com.brockhaus.mainapp.model.Kaese;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("KAESE-SERVICE")
@RestController
public interface KaeseServiceClient {
    @GetMapping("/kaese")
    List<Kaese> getKaese();

    @GetMapping("/kaese/{id}")
    Kaese getKaeseById(@PathVariable Long id);

    @PostMapping("/kaese")
    Kaese saveKaese(@RequestBody Kaese kaese);

    @DeleteMapping("/kaese/{id}")
    void deleteKaese(@PathVariable Long id);

    @DeleteMapping("/kaese/all")
    void deleteKaeseAll();

    @PutMapping("/kaese/{id}")
    void updateKaese(@RequestBody Kaese kaese, @PathVariable Long id);

}
