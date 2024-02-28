package repository;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Products {
    private int id;
    private String name;
    private double price;
    private LocalDateTime registration_date;
}
