package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagedEtudiantEcolageDto {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<EtudiantEcolageDto> etudiantEcolages;
}
