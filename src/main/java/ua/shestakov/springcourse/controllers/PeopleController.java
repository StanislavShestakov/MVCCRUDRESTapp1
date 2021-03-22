package ua.shestakov.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.shestakov.springcourse.dao.PersonDAO;
import ua.shestakov.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public  String index(Model model){
        //Получим всех людей из DAO и передадим их на отображение и представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public  String show(@PathVariable("id") int id,Model model){
        //Получим одного человека по id из DAO и передадим их на отображение и представление
        model.addAttribute("person",personDAO.show(id));
        return "people/show";

    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public  String delte(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";

    }
}
