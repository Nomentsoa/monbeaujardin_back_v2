package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.DernierMatricul;
import ca.lazanomentsoa.monbeaujardinbackv2.main.enums.MatriculAppartenant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.DernierMatriculRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DernierMatriculImpl implements DernierMatriculService {

    private DernierMatriculRepository dernierMatriculRepository;

    @Override
    public String getDernierMatricul(String appartenant) {
        // ici si la base de donnée est encore vide donc elle retourne une valeur null => valeur 1 pour le permier matricul
        return dernierMatriculRepository.getDernierMatricul(appartenant) == null ? "0" : dernierMatriculRepository.getDernierMatricul(appartenant).getMatricul();
    }

    @Override
    public void setDernierMatricul(String dernierMatriculValeur, String appartenant) {
        DernierMatricul matriculSaved = dernierMatriculRepository.getDernierMatricul(appartenant);
        if(matriculSaved == null){
            DernierMatricul dernierMatricul = new DernierMatricul();
            dernierMatricul.setAppartenant(MatriculAppartenant.ETUDIANT.toString());
            dernierMatricul.setMatricul(dernierMatriculValeur);
            dernierMatriculRepository.save(dernierMatricul);
        }else{
            matriculSaved.setMatricul(dernierMatriculValeur);
            dernierMatriculRepository.save(matriculSaved);
        }
    }
}
