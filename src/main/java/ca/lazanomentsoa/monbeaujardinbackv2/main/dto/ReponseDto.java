package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReponseDto {
    Boolean isError;
    String message;
}
