package com.bibliotheque.angular.repository;

import com.bibliotheque.angular.entites.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepo extends JpaRepository<Livre,Long> {
}
