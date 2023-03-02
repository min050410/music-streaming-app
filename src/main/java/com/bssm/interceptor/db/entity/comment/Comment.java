package com.bssm.interceptor.db.entity.comment;

import com.bssm.interceptor.db.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long Id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(updatable = false, name="create_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
