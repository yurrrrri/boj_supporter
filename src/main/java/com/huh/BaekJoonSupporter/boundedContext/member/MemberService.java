package com.huh.BaekJoonSupporter.boundedContext.member;

import com.huh.BaekJoonSupporter.boundedContext.member.form.MemberCreateForm;
import com.huh.BaekJoonSupporter.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(MemberCreateForm form) {
        if (memberRepository.findByUsername(form.getUsername()).isPresent()) {
            throw new DuplicateKeyException("Member already exists.");
        }

        Member member = Member.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword1()))
                .token(form.getToken())
                .build();
        return memberRepository.save(member);
    }

    public Member getMember(String username) {
        Optional<Member> member = memberRepository.findByUsername(username);

        return member.orElseThrow(() -> new DataNotFoundException("Member not found."));
    }

    public Member modify(Member member, String password, String token) {
        Member modifiedMember = member.toBuilder()
                .password(passwordEncoder.encode(password))
                .token(token)
                .build();
        return memberRepository.save(modifiedMember);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}