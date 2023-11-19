package com.example.lock_and_load.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_locker")
public class Locker {
    @Id
    private String id;

    private Integer capacity;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
