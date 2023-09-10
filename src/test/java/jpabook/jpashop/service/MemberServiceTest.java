package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;
    
    @Test
    @DisplayName("")
//    @Rollback(value = false)
    public void JoinTest() {
        // given
        Member member = new Member();
        member.setName("kim");
        
        // when
        Long savedId = memberService.join(member);
        
        // then
        assertEquals(member, memberRepository.findById(savedId));
    }

    @Test(expected = IllegalStateException.class)
    @DisplayName("중복_회원_예외")
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 합니다.");
    }
}
