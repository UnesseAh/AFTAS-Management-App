package com.example.aftas.controller.vm.Member;

import com.example.aftas.entities.Member;

import java.time.LocalDate;

public record MemberResponseVM(
        Long MEMBER_NUMBER,
        String FIRST_NAME,
        String LAST_NAME,
        LocalDate ACCESSION_DATE,
        String NATIONALITY,
        String IDENTITY_DOCUMENT_TYPE,
        String IDENTITY_NUMBER
) {
    public static MemberResponseVM fromMember(Member member){
        return new MemberResponseVM(
                member.getNumber(),
                member.getFirstName(),
                member.getLastName(),
                member.getAccessionDate(),
                member.getNationality(),
                member.getIdentityDocument().toString(),
                member.getIdentityNumber()
        );
    }

}
