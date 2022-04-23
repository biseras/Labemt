package com.example.labemt.Service;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.dto.AvtorDto;
import com.example.labemt.Model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface AvtorService {
    Optional<Avtor> findById(Long id);
    List<Avtor> listAll();
    Optional<Avtor> save(String name, String surname, Country country);
    Optional<Avtor> edit(Long id, String name, String surname, Country country);
    Optional<Avtor> save(AvtorDto avtorDto);
    Optional<Avtor> edit(Long id, AvtorDto avtorDto);

    void deleteById(Long id);
}
