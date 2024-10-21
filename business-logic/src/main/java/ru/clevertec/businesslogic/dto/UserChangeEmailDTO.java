package ru.clevertec.businesslogic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.clevertec.dto.SessionRequest;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangeEmailDTO extends SessionRequest {
    private String email;
}
