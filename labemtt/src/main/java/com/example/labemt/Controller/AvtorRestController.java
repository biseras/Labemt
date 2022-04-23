package com.example.labemt.Controller;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.dto.AvtorDto;
import com.example.labemt.Model.dto.CountryDto;
import com.example.labemt.Service.AvtorService;
import com.example.labemt.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/avtor")
public class AvtorRestController {

    private final AvtorService avtorService;

    public AvtorRestController(AvtorService avtorService) {
        this.avtorService = avtorService;
    }

    @GetMapping
    private List<Avtor> findAll() {
        return this.avtorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avtor> findById(@PathVariable Long id) {
        return this.avtorService.findById(id)
                .map(avtor -> ResponseEntity.ok().body(avtor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Avtor> save(@RequestBody AvtorDto avtorDto) {
        return this.avtorService.save(avtorDto)
                .map(avtor -> ResponseEntity.ok().body(avtor))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Avtor> save(@PathVariable Long id, @RequestBody AvtorDto avtorDto) {
        return this.avtorService.edit(id, avtorDto)
                .map(avtor -> ResponseEntity.ok().body(avtor))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.avtorService.deleteById(id);
        if(this.avtorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}