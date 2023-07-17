package com.bibliotheque.angular.controller;

import com.bibliotheque.angular.entites.Livre;
import com.bibliotheque.angular.repository.LivreRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class LivreController {
    private LivreRepo livreRepo;
    @GetMapping("")
    public List<Livre> bookList(){
        return livreRepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livre> getbook(@PathVariable Long id){
        Livre livre =  livreRepo.findById(id).get();
        if(livre!=null){
            return ResponseEntity.ok(livre);
        }
        else return ResponseEntity.badRequest().build();
    }
    @PostMapping("/add")
    public Livre BookList(@RequestBody Livre livre){
        return livreRepo.save(livre);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id){

        livreRepo.deleteById(id);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Livre> bookEdit(@PathVariable Long id, @RequestBody Livre livre) {
        Optional<Livre> optionalLivre = livreRepo.findById(id);
        if (optionalLivre.isPresent()) {
            Livre existingLivre = optionalLivre.get();
            existingLivre.setAuteur(livre.getAuteur());
            existingLivre.setEditeur(livre.getEditeur());
            existingLivre.setIntitule(livre.getIntitule());
            existingLivre.setAnnee(livre.getAnnee());
            Livre updatedLivre = livreRepo.save(existingLivre);
            return ResponseEntity.ok(updatedLivre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
