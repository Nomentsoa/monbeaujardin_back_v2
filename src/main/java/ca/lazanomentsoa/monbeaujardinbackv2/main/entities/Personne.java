package ca.lazanomentsoa.monbeaujardinbackv2.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String matricule;
    private String nom;
    private String prenom;
    @Column(nullable = false, length = 10)
    private String dateNaissance;
    private String adresse;
    private Character sexe;
    private String noteSupplementaire;
    private String image;
}
