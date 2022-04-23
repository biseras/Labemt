package com.example.labemt.Service;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.Enumerations.Category;
import com.example.labemt.Model.Kniga;
import com.example.labemt.Model.dto.CountryDto;
import com.example.labemt.Model.dto.KnigaDto;
import jdk.jshell.Snippet;

import java.util.List;
import java.util.Optional;

public interface KnigaService {
    Optional<Kniga> findById(Long id);
    List<Kniga> listAll();
    Optional<Kniga> save(String name, Category category, Avtor avtor, Integer availablecopies);
    Optional<Kniga> edit(Long id, String name, Category category, Avtor avtor, Integer availablecopies);
    Optional<Kniga> save(KnigaDto knigaDto);
    Optional<Kniga> edit(Long id, KnigaDto knigaDto);

    void deleteById(Long id);
    public Optional<Kniga> knigataezemena(Long Id);
}
