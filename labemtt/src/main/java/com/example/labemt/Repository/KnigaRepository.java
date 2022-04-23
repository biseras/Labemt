package com.example.labemt.Repository;

import com.example.labemt.Model.Kniga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnigaRepository extends JpaRepository<Kniga, Long> {
    void deleteByName(String name);
}
