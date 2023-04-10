package com.code.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
    @Id
    @Column(name = "empNo")
    private String empNo;

    @Column(name = "empName")
    private String empName;

    @Column(name = "position")
    private String position;
}
