package ru.clevertec.businesslogic.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.clevertec.dto.SessionRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO extends SessionRequest {
    private String username;
    private String email;
}