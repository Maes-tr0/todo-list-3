package ua.maestr0.todolist.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.maestr0.todolist.model.Note;
import ua.maestr0.todolist.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@Validated
public class NoteRestController {

    private final NoteService noteService;

    // 1) Створення нотатки (Create)
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        noteService.createNote(note);
        // Повертаємо створену нотатку та статус 201
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    // 2) Отримання однієї нотатки (Read by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id) {
        Note found = noteService.getNote(id);
        return ResponseEntity.ok(found);
    }

    // 2.1) Отримати всі нотатки
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> allNotes = noteService.getAllNotes();
        return ResponseEntity.ok(allNotes);
    }

    // 3) Оновлення (Update)
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody Note updatedNote
    ) {
        Note noteFromDb = noteService.getNote(id);

        noteFromDb.setTitle(updatedNote.getTitle());
        noteFromDb.setContent(updatedNote.getContent());

        noteService.updateNote(noteFromDb);
        return ResponseEntity.ok(noteFromDb);
    }

    // 4) Видалення (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
