package org.iainuk.tracker;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="customer")
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    @NotBlank(message="First name is required.")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message="Last name is required.")
    private String lastName;

    @Column(name="email")
    @NotBlank(message="Email is required.")
    private String email;

    public Customer(String first, String last, String email) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
    }

}
