package com.ai_study_assist.ai_study_assistant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)

public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notes_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;
}
