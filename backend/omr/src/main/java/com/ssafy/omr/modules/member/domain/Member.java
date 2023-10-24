package com.ssafy.omr.modules.member.domain;

import com.ssafy.omr.modules.util.base.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = ("member_id")))
@Entity
public class Member extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column
    private String emoji;

    @Column(length = 16)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public void changeEmoji(String emoji) {
        this.emoji = emoji;
    }
    public void changePassword(String password) {
        this.password = password;
    }
}
