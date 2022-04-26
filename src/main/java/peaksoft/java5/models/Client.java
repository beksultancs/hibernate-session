package peaksoft.java5.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "clients")
@Getter @Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName; // last_name

    private String phoneNumber; // phone_number

    private String email;

    private String password;

    public Client() {
    }

    public Client(String name,
                  String lastName,
                  String phoneNumber,
                  String email,
                  String password) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}
