package com.example.aftas.controller.vm.Member;

import com.example.aftas.entities.Member;

import java.time.LocalDate;

public record MemberResponseVM(
        Long memberNumber,
        String firstName,
        String lastName,
        LocalDate accessionDate,
        String nationality,
        String identityDocument,
        String identityNumber
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
