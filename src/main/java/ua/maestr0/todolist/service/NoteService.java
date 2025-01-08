package ua.maestr0.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.maestr0.todolist.model.Note;
import ua.maestr0.todolist.repository.NoteRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    @Transactional
    public void createNote(Note note) {
        noteRepository.save(note);
    }

    @Transactional
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note with id " + id + " not found"));
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Transactional
    public void updateNote(Note note) {
        Long id = note.getId();
        noteRepository.updateNoteById(id, note);
    }
}
