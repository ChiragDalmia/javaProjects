package ca.sheridancollege.dalmiac.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String isbn;
    private String name;
    private String author;
    private String role;
    private LocalDateTime creationDate;
}
