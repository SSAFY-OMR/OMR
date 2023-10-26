package com.ssafy.omr.modules.member.domain;

import com.ssafy.omr.modules.util.base.AllBaseInitEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = ("member_streak_id")))
@Entity
public class MemberStreak extends AllBaseInitEntity {

    @Column(nullable = false)
    @Builder.Default
    private Integer currentStreak = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer longestStreak = 0;

    public void increaseCurrentStreak() {
        this.currentStreak++;
    }

    public void initializeCurrentStreak() {
        this.currentStreak = 0;
    }

    public void updateLongestStreak() {
        this.longestStreak = this.currentStreak;
    }
}
