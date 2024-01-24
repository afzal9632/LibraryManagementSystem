package com.root.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(
        description = "Author Model Information"
)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Schema(
            description = "Author_Id"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(
            description = "Author Name"
    )
    @NotBlank(message = "Author name can't be null")
    private String name;

    @Schema(
            description = "Author date of birth"
    )
    private LocalDate birthDate;


}
