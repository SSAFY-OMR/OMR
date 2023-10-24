package com.ssafy.omr.modules.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberStreak {

    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentStreak = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer longestStreak = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
