package com.furnitureshop.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import com.furnitureshop.importer.entity.Importer;
import com.furnitureshop.order.entity.Order;
import com.furnitureshop.order.entity.OrderDetail;
import com.furnitureshop.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "furnitureshop_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private String imgUrl;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String activeFlag = "Y";

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Importer> imports = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();
}
