package com.example.labemt.Repository;

import com.example.labemt.Model.Avtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvtorRepository extends JpaRepository<Avtor, Long> {
    void deleteByName(String name);
}
