package com.example.labemt.Controller;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Country;
import com.example.labemt.Service.AvtorService;
import com.example.labemt.Service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/avtor")
public class AvtorController {
    private final AvtorService avtorService;
    private final CountryService countryService;

    public AvtorController(AvtorService avtorService, CountryService countryService) {
        this.avtorService = avtorService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        // if (error != null && !error.isEmpty()) {
        ////   model.addAttribute("hasError", true);
        //    model.addAttribute("error", error);
        // }
        List<Avtor> avtors = this.avtorService.listAll();
        model.addAttribute("avtor", avtors);
        model.addAttribute("bodyContent", "avtor");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.avtorService.deleteById(id);
        return "redirect:/avtor";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.avtorService.findById(id).isPresent()) {
            Avtor avtor = this.avtorService.findById(id).get();
            model.addAttribute("avtor", avtor);
            model.addAttribute("bodyContent", "add-avtor");
            return "master-template";
        }
        return "redirect:/avtor?error=UcenikNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Country> countries=countryService.listAll();
        model.addAttribute("country", countries);
        model.addAttribute("bodyContent", "add-avtor");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Country country) {
        if (id != null) {
            this.avtorService.edit(id, name, surname, country);
        } else {
            this.avtorService.save(name, surname, country);
        }
        return "redirect:/avtor";
    }
}
