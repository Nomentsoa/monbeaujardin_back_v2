package ca.lazanomentsoa.monbeaujardinbackv2.main.services;


import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.DernierMatricul;

import java.util.Optional;


public interface DernierMatriculService {

    String getDernierMatricul(String appartenant);
    void setDernierMatricul(String dernierMatricul, String appartenant);
}
