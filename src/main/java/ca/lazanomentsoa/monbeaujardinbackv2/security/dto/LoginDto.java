package ca.lazanomentsoa.monbeaujardinbackv2.security.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
    private Boolean withRefreshToken;
    private String grantType;
    private String refreshToken;
}
