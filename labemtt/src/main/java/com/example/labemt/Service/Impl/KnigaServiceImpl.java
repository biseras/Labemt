package com.example.labemt.Service.Impl;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.Enumerations.Category;
import com.example.labemt.Model.Exception.CountryNotFoundException;
import com.example.labemt.Model.Kniga;
import com.example.labemt.Model.dto.CountryDto;
import com.example.labemt.Model.dto.KnigaDto;
import com.example.labemt.Repository.AvtorRepository;
import com.example.labemt.Repository.CountryRepository;
import com.example.labemt.Repository.KnigaRepository;
import com.example.labemt.Service.KnigaService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnigaServiceImpl implements KnigaService {
    private final KnigaRepository knigaRepository;
    private final CountryRepository countryRepository;
    private final AvtorRepository avtorRepository;

    public KnigaServiceImpl(KnigaRepository knigaRepository, CountryRepository countryRepository, AvtorRepository avtorRepository) {
        this.knigaRepository = knigaRepository;
        this.countryRepository = countryRepository;
        this.avtorRepository = avtorRepository;
    }

    @Override
    public Optional<Kniga> findById(Long id) {
        return knigaRepository.findById(id);
    }

    @Override
    public List<Kniga> listAll() {
        return knigaRepository.findAll();
    }

    @Override
    public Optional<Kniga> save(String name, Category category, Avtor avtor, Integer availablecopies) {
        return Optional.of(this.knigaRepository.save(new Kniga(name, category, avtor, availablecopies)));
    }

    @Override
    public Optional<Kniga> edit(Long id, String name, Category category, Avtor avtor, Integer availablecopies) {
        Kniga kniga = this.knigaRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        kniga.setName(name);
        kniga.setCategory(category);
        kniga.setAvtor(avtor);
        kniga.setAvailablecopies(availablecopies);

        return Optional.of(this.knigaRepository.save(kniga));    }

    @Override
    public Optional<Kniga> save(KnigaDto knigaDto) {
        Avtor avtor = this.avtorRepository.findById(knigaDto.getAvtor())
                .orElseThrow(() -> new CountryNotFoundException(knigaDto.getAvtor()));
        Kniga kniga= new Kniga(knigaDto.getName(), knigaDto.getCategory(), avtor, knigaDto.getAvailablecopies());
        this.knigaRepository.save(kniga);

        return Optional.of(kniga);   }

    @Override
    public Optional<Kniga> edit(Long id, KnigaDto knigaDto) {
        Kniga kniga = this.knigaRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        Avtor avtor = this.avtorRepository.findById(knigaDto.getAvtor())
                .orElseThrow(() -> new CountryNotFoundException(knigaDto.getAvtor()));
        kniga.setName(knigaDto.getName());
        kniga.setCategory(knigaDto.getCategory());
        kniga.setAvtor(avtor);
        kniga.setAvailablecopies(knigaDto.getAvailablecopies());

        this.knigaRepository.save(kniga);
        //this.refreshMaterializedView(); za update na view no kje pravime i so scheluletasks
        return Optional.of(kniga);    }

    @Override
    public void deleteById(Long id) {
        knigaRepository.deleteById(id);
    }

    @Override
    public Optional<Kniga> knigataezemena(Long id) {
        Kniga kniga = this.knigaRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        int kopiii=kniga.getAvailablecopies();
        if(kopiii == 0){
            throw new CountryNotFoundException(id);
        }
        int imauste=kopiii-1;
        kniga.setAvailablecopies(imauste);
        return Optional.of(this.knigaRepository.save(kniga));
    }
}

