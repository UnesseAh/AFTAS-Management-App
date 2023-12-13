package com.example.aftas.controller.vm;

import com.example.aftas.entities.Hunting;

public record HuntingRequestVM(
        CompetitionResponseVM CODE,
        MemberResponseVM MEMBER_NUMBER,
        String fishName
) {
//    public Hunting toHunting(){
//        return new Hunting().builder()
//                .member()
//                .competition()
//                .fish(fishName)
//                .build();
//    }

}
