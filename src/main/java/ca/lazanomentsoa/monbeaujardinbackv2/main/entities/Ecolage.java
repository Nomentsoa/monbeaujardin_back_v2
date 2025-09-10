package ca.lazanomentsoa.monbeaujardinbackv2.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Ecolage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte jour;
    private byte mois;
    private short annee;
    private boolean isPayed;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", referencedColumnName = "id", nullable = false)
    private Etudiant etudiant;

}
