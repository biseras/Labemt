package com.example.labemt.Repository;

import com.example.labemt.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
        void deleteByName(String name);
}
