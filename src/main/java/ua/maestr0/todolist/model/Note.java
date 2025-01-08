package ua.maestr0.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "seq_note_id", allocationSize = 1)
    @GeneratedValue(generator = "client_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 50, message = "Title must be up to 50 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 200, message = "Content must be up to 200 characters")
    private String content;
}
