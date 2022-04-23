package com.example.labemt.Service;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.Enumerations.Category;
import com.example.labemt.Model.dto.AvtorDto;
import com.example.labemt.Model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    List<Country> listAll();
    Optional<Country> save(String name, String continent);
    Optional<Country> edit(Long id, String name, String continent);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> edit(Long id, CountryDto countryDto);

    void deleteById(Long id);
}
