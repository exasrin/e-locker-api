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
@Table(name = "m_user")
public class User {
    @Id
    private String id;

    private String name;

    private String address;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    private String password;
}
