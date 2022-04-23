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
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        // if (error != null && !error.isEmpty()) {
        ////   model.addAttribute("hasError", true);
        //    model.addAttribute("error", error);
        // }
        List<Country> countries = this.countryService.listAll();
        model.addAttribute("country", countries);
        model.addAttribute("bodyContent", "country");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.countryService.deleteById(id);
        return "redirect:/country";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.countryService.findById(id).isPresent()) {
            Country country = this.countryService.findById(id).get();
            model.addAttribute("country", country);
            model.addAttribute("bodyContent", "add-country");
            return "master-template";
        }
        return "redirect:/country?error=UcenikNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        model.addAttribute("bodyContent", "add-country");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String continent) {
        if (id != null) {
            this.countryService.edit(id, name, continent);
        } else {
            this.countryService.save(name, continent);
        }
        return "redirect:/country";
    }
}
