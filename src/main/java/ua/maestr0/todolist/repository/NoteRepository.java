package ua.maestr0.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.maestr0.todolist.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Modifying
    @Query("UPDATE Note n SET " +
           "n.title = :#{#note.title}, " +
           "n.content = :#{#note.content} " +
           "WHERE n.id = :id")
    void updateNoteById(@Param("id") Long id, @Param("note") Note note);
}
