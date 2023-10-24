package com.ssafy.omr.dummy;

import com.ssafy.omr.modules.auth.util.Encryptor;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.RoleType;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@RequiredArgsConstructor
@Component
public class MemberDummy {

    private final MemberRepository memberRepository;
    private final Encryptor encryptor;

    private final List<String> nicknames = new ArrayList<>(){
        {
            add("james");
            add("paul");
            add("arch");
            add("sam");
            add("daniel");
        }
    };
    private final List<String> passwords = new ArrayList<>(){
        {
            add("testtestpwd1");
            add("testtestpwd2");
            add("testtestpwd3");
            add("testtestpwd4");
            add("testtestpwd5");
        }
    };
    private final List<String> loginIds = new ArrayList<>(){
        {
            add("testtestid1");
            add("testtestid2");
            add("testtestid3");
            add("testtestid4");
            add("testtestid5");
        }
    };


    public List<Member> createMemberDummies() {
        List<Member> members = new ArrayList<>();

        for(int i = 0; i < nicknames.size(); i++) {
            members.add(Member.builder()
                    .loginId(encryptor.encrypt(loginIds.get(i)))
                    .password(encryptor.encrypt(passwords.get(i)))
                    .nickname(nicknames.get(i))
                    .roleType(RoleType.USER)
                    .emoji("⛑")
                    .build());
        }

        memberRepository.saveAll(members);
        return members;
    }

}
