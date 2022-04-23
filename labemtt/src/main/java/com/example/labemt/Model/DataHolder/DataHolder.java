package com.example.labemt.Model.DataHolder;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Model.Enumerations.Category;
import com.example.labemt.Model.Kniga;
import com.example.labemt.Repository.AvtorRepository;
import com.example.labemt.Repository.CountryRepository;
import com.example.labemt.Repository.KnigaRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Country> countries=new ArrayList<>();
    public static List<Avtor>avtors=new ArrayList<>();
    public static List<Kniga>knigas=new ArrayList<>();
    private final CountryRepository countryRepository;
    private final AvtorRepository avtorRepository;
    private final KnigaRepository knigaRepository;

    public DataHolder(CountryRepository countryRepository, AvtorRepository avtorRepository, KnigaRepository knigaRepository) {
        this.countryRepository = countryRepository;
        this.avtorRepository = avtorRepository;
        this.knigaRepository = knigaRepository;
    }

    @PostConstruct
    public void init() {
        Country country1=new Country("Makedonija", "Evropa");
        countryRepository.save(country1);
        Country country=new Country("Germanija", "Evropa");
        countryRepository.save(country);
        Avtor avtor=new Avtor("slavko", "janevski", country1);
        avtorRepository.save(avtor);
        Avtor avtor1=new Avtor("johan", "gete", country);
        avtorRepository.save(avtor);
        avtorRepository.save(avtor1);
        Kniga kniga=new Kniga("verter", Category.NOVEL, avtor1, 12 );
        knigaRepository.save(kniga);
        Kniga kniga1=new Kniga("crni i zolti", Category.NOVEL, avtor, 12 );
        knigaRepository.save(kniga1);

    }
}
