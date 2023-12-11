package com.example.aftas.controller.vm;

import com.example.aftas.entities.Member;
import com.example.aftas.entities.enums.IdentityDocumentType;

import java.time.LocalDate;

public record MemberResponseVM(
        Long MEMBER_NUMBER,
        String FIRST_NAME,
        String LAST_NAME,
        LocalDate ACCESSION_DATE,
        String NATIONALITY,
        IdentityDocumentType IDENTITY_DOCUMENT_TYPE,
        String IDENTITY_NUMBER
) {
    public static MemberResponseVM fromMember(Member member){
        return new MemberResponseVM(
                member.getNumber(),
                member.getFirstName(),
                member.getLastName(),
                member.getAccessionDate(),
                member.getNationality(),
                member.getIdentityDocument(),
                member.getIdentityNumber()
        );
    }

}
