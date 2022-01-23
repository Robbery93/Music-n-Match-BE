package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import nl.robbertij.matchnmusic.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public Lesson getLesson(StudentTeacherKey id){
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);

        if(optionalLesson.isPresent()){
            return optionalLesson.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void updateHomework(StudentTeacherKey id, String homework) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);

        if(optionalLesson.isPresent()){
            Lesson storedLesson = optionalLesson.get();

            storedLesson.setHomework(homework);
            lessonRepository.save(storedLesson);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }
}
