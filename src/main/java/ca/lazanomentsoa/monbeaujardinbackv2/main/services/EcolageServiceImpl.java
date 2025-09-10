package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EcolageDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Ecolage;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.mappers.EcolageMapper;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.EcolageRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class EcolageServiceImpl implements EcolageService {

    private EcolageRepository ecolageRepository;
    private EtudiantRepository etudiantRepository;
    private EcolageMapper ecolageMapper;

    @Override
    public EcolageDto getEcolageByIdEtudiantAndMoisAndAnnee(int idEtudiant, byte mois, short annee) {
        Ecolage ecolage = ecolageRepository.getEcolageByIdEtudiantAndMoisAndAnnee(idEtudiant, mois, annee);
        return ecolage != null ? ecolageMapper.fromEcolageToEcolageDto(ecolage) : null;
    }

    @Override
    public ReponseDto addEcolageToEtudiant(int idEtudiant, byte jour, byte mois, short annee) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(idEtudiant);
        Ecolage ecolage = new Ecolage();

        if(etudiant.isPresent()){
            ecolage.setEtudiant(etudiant.get());
            ecolage.setJour(jour);
            ecolage.setMois(mois);
            ecolage.setAnnee(annee);
            return new ReponseDto(false, "Saved");
        }else
            return new ReponseDto(true, "Erreur");

    }
}
