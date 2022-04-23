package com.example.labemt.Controller;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Enumerations.Category;
import com.example.labemt.Model.Kniga;
import com.example.labemt.Service.AvtorService;
import com.example.labemt.Service.KnigaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kniga")
public class KnigaController {
    private final KnigaService knigaService;
    private final AvtorService avtorService;

    public KnigaController(KnigaService knigaService, AvtorService avtorService) {
        this.knigaService = knigaService;
        this.avtorService = avtorService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        // if (error != null && !error.isEmpty()) {
        ////   model.addAttribute("hasError", true);
        //    model.addAttribute("error", error);
        // }
        List<Kniga> knigas = this.knigaService.listAll();
        model.addAttribute("kniga", knigas);
        model.addAttribute("bodyContent", "kniga");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.knigaService.deleteById(id);
        return "redirect:/kniga";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.knigaService.findById(id).isPresent()) {
            Kniga kniga = this.knigaService.findById(id).get();
            model.addAttribute("kniga", kniga);
            model.addAttribute("bodyContent", "add-kniga");
            return "master-template";
        }
        return "redirect:/kniga?error=UcenikNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Avtor> avtors=avtorService.listAll();
        model.addAttribute("avtor", avtors);
        model.addAttribute("bodyContent", "add-kniga");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam Avtor avtor,
            @RequestParam Integer availablecopies) {
        if (id != null) {
            this.knigaService.edit(id, name, category, avtor, availablecopies);
        } else {
            this.knigaService.save(name, category, avtor, availablecopies);
        }
        return "redirect:/kniga";
    }
    @PostMapping("/addkopija/{id}")
    public String addkopijaProduct(@PathVariable Long id) {
        this.knigaService.knigataezemena(id);
        return "redirect:/kniga";
    }
}
