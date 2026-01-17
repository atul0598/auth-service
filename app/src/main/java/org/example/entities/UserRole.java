package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
// ek annotation hain jo hame builder provide krega
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class UserRole {

//    setting role_id
    @Id
//    auto-increment role_id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    setting sql column name
    @Column(name="role_id")
    private Long roleId;
    private String name;
}
