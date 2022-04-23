package com.example.labemt.Controller;

import com.example.labemt.Model.Country;
import com.example.labemt.Model.Kniga;
import com.example.labemt.Model.dto.CountryDto;
import com.example.labemt.Model.dto.KnigaDto;
import com.example.labemt.Service.AvtorService;
import com.example.labemt.Service.CountryService;
import com.example.labemt.Service.KnigaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/kniga")
public class KnigaRestContoller {
    private final CountryService countryService;
    private final AvtorService avtorService;
    private final KnigaService knigaService;

    public KnigaRestContoller(CountryService countryService, AvtorService avtorService, KnigaService knigaService) {
        this.countryService = countryService;
        this.avtorService = avtorService;
        this.knigaService = knigaService;
    }

    @GetMapping
    private List<Kniga> findAll() {
        return this.knigaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kniga> findById(@PathVariable Long id) {
        return this.knigaService.findById(id)
                .map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Kniga> save(@RequestBody KnigaDto knigaDto) {
        return this.knigaService.save(knigaDto)
                .map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Kniga> save(@PathVariable Long id, @RequestBody KnigaDto knigaDto) {
        return this.knigaService.edit(id, knigaDto)
                .map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/knigataezemena/{id}")
    public ResponseEntity<Kniga> markAsTaken(@PathVariable Long id){
        return knigaService.knigataezemena(id)
                .map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.knigaService.deleteById(id);
        if(this.knigaService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
