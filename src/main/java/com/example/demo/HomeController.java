package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PatientRepository repo;

    @RequestMapping("/")
    public String listPatients(Model model){
        model.addAttribute("patients", repo.findAll());
        return "patientlist";
    }
    @GetMapping("/add")
    public String getPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "patientform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Patient patient, BindingResult result){
        if(result.hasErrors()){
            return "patientform";
        }
        repo.save(patient);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showPatient(@PathVariable("id") long id, Model model){
        model.addAttribute("patient", repo.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updatePatient(@PathVariable("id") long id, Model model){
        model.addAttribute("patient", repo.findById(id).get());
        return "show";
    }
    @RequestMapping("/delete/{id}")
    public String delPatient(@PathVariable("id") long id){
        repo.deleteById(id);
        return "redirect:/";
    }

}
