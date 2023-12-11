package com.example.aftas.controller.vm;

import com.example.aftas.entities.Member;
import com.example.aftas.entities.enums.IdentityDocumentType;

import java.time.LocalDate;

public record MemberResponseVM(
        Integer memberNumber,
        String firstName,
        String lastName,
        LocalDate accessionDate,
        String nationality,
        IdentityDocumentType identityDocument,
        String identityNumber
) {
    public static MemberResponseVM fromMember(Member member){
        return new MemberResponseVM(
                member.getMemberNumber(),
                member.getFirstName(),
                member.getLastName(),
                member.getAccessionDate(),
                member.getNationality(),
                member.getIdentityDocument(),
                member.getIdentityNumber()
        );
    }
}
