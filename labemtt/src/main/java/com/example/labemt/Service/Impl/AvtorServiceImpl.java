package com.example.labemt.Service.Impl;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.Exception.CountryNotFoundException;
import com.example.labemt.Model.dto.AvtorDto;
import com.example.labemt.Model.dto.CountryDto;
import com.example.labemt.Repository.AvtorRepository;
import com.example.labemt.Repository.CountryRepository;
import com.example.labemt.Service.AvtorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvtorServiceImpl implements AvtorService {
    private final AvtorRepository avtorRepository;
    private final CountryRepository countryRepository;

    public AvtorServiceImpl(AvtorRepository avtorRepository, CountryRepository countryRepository) {
        this.avtorRepository = avtorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Avtor> findById(Long id) {
        return avtorRepository.findById(id);
    }

    @Override
    public List<Avtor> listAll() {
        return avtorRepository.findAll();
    }

    @Override
    public Optional<Avtor> save(String name, String surname, Country country) {
        return Optional.of(this.avtorRepository.save(new Avtor(name, surname, country)));
    }

    @Override
    public Optional<Avtor> edit(Long id, String name, String surname, Country country) {
        Avtor avtor = this.avtorRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        avtor.setName(name);
        avtor.setSurname(surname);
        avtor.setCountry(country);

        return Optional.of(this.avtorRepository.save(avtor));    }

    public Optional<Avtor> save(AvtorDto avtorDto) {
        Country country = this.countryRepository.findById(avtorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(avtorDto.getCountry()));
        this.avtorRepository.deleteByName(avtorDto.getName());
        Avtor avtor= new Avtor(avtorDto.getName(), avtorDto.getSurname(), country);
        this.avtorRepository.save(avtor);

        return Optional.of(avtor);
    }

    @Override
    public Optional<Avtor> edit(Long id, AvtorDto avtorDto) {
        Avtor avtor = this.avtorRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        Country country = this.countryRepository.findById(avtorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(avtorDto.getCountry()));
        avtor.setName(avtorDto.getName());
        avtor.setSurname(avtorDto.getSurname());
        avtor.setCountry(country);

        this.avtorRepository.save(avtor);
        //this.refreshMaterializedView(); za update na view no kje pravime i so scheluletasks
        return Optional.of(avtor);
    }

    @Override
    public void deleteById(Long id) {
        avtorRepository.deleteById(id);
    }
}
