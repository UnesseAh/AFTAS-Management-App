package com.example.aftas.controller.vm;

import com.example.aftas.entities.Member;
import com.example.aftas.entities.enums.IdentityDocumentType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record MemberRequestVM(
        @Digits(integer = 10, fraction = 2, message = "Member number must be a digit")
        Integer memberNumber,
        @NotBlank(message = "First name can't be blank")
        String firstName,
        @NotBlank(message = "Last name can't be blank")
        String lastName,
        LocalDate accessionDate,
        @NotBlank(message = "Nationality can't be blank")
        String nationality,
        @NotBlank(message = "Identity document can't be blank")
        IdentityDocumentType identityDocument,
        @NotBlank(message = "Identity number can't be blank")
        String identityNumber
) {
        public Member toMember(){
                return new Member().builder()
                        .memberNumber(memberNumber)
                        .firstName(firstName)
                        .lastName(lastName)
                        .accessionDate(accessionDate)
                        .nationality(nationality)
                        .identityDocument(identityDocument)
                        .identityNumber(identityNumber)
                        .build();
        }

}
