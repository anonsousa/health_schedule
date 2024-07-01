package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.entities.UserEntity;

public record TokenDto(
        String email,
        String token
) {
    public TokenDto(UserEntity user, String token){
        this(
                user.getEmail(),
                token
        );
    }
}
