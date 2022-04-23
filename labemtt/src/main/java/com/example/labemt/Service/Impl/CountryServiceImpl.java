package com.example.labemt.Service.Impl;

import com.example.labemt.Model.Country;
import com.example.labemt.Model.Exception.CountryNotFoundException;
import com.example.labemt.Model.dto.CountryDto;
import com.example.labemt.Repository.CountryRepository;
import com.example.labemt.Service.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CountryServiceImpl(CountryRepository countryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.countryRepository = countryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        return Optional.of(this.countryRepository.save(new Country(name, continent)));
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(name);
        country.setContinent(continent);

        return Optional.of(this.countryRepository.save(country));    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        this.countryRepository.deleteByName(countryDto.getName());
        Country country= new Country(countryDto.getName(), countryDto.getContinent());
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        this.countryRepository.save(country);
        //this.refreshMaterializedView(); za update na view no kje pravime i so scheluletasks
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
