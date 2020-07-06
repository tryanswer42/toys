package be.vdab.toys.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private LocalDate ordered;

    @NotNull
    private LocalDate required;

    private LocalDate shipped;

    @Lob
    private String comments;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Version
    private long version;
}
