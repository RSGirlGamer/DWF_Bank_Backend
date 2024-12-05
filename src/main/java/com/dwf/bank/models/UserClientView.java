package com.dwf.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "user_client_view")
public class UserClientView {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "user_role", nullable = false)
    private String userRole;

    @Column(name = "user_status")
    private String userStatus;

    @Column(name = "client_id", columnDefinition = "BINARY(16)")
    private UUID clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_lastname")
    private String clientLastName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "client_dui")
    private String clientDui;

    @Column(name = "client_credit_limit")
    private Integer clientCreditLimit;

    @Column(name = "client_salary")
    private Double clientSalary;

    @Column(name = "client_work_place")
    private String clientWorkPlace;

}
