package com.root.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Book Model Information"
)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Schema(
            description = "Book_Id"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;


    @Schema(
            description = "Book title"
    )
    @NotBlank(message = "Title can not be blank")
    private String title;


    @Schema(
            description = "International Standard Book Number"
    )
    @NotBlank(message = "Isbn can not be blank")
    private String isbn;


    @Schema(
            description = "Book quantity"
    )
    @NotNull(message = "Quantity can not be null")
    private  Integer quantity;


    @Schema(
            description = "Author"
    )
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

}
