package be.vdab.toys.domain;
//make embaddable if time for adress

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String streetAndNumber;

    @NotBlank
    private String city;

    private String state;

    private String postalCode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;



    @Version
    private long version;

    //--
    protected Customer() {
    }

    public Customer(@NotBlank String name, @NotBlank String streetAndNumber, @NotBlank String city, String state, String postalCode, Country country) {
        this.name = name;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
    //--

    public String getName() {
        return name;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Country getCountry() {
        return country;
    }
}
//setters als de klant mag verhuisen