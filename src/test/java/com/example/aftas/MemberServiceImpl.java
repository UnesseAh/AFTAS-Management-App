package com.example.aftas;

import com.example.aftas.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImpl {
    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberServiceImpl memberService;

    @BeforeAll
    public static void init(){
        MemberServiceImpl memberService = Mockito.mock(MemberServiceImpl.class);
//        MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    }

    @Test
    public void testConvertString(){
//        Mockito.when(memberService.)
    }


}
