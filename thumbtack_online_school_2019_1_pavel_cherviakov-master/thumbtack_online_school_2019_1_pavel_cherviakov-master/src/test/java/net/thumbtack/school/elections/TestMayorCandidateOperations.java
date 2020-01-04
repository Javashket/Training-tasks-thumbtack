package net.thumbtack.school.elections;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Voter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMayorCandidateOperations extends TestBase {

//    @Test
//    public void testMayorCandidate() {
//        Voter voter = insertVoter("Иван", "Иванов", "Иванович",
//                "Московская", "5", 6, "polk", "poll");
//        MayorCandidate mayorCandidate = insertMayorCandidate(voter);
//        MayorCandidate mayorCandidateFromDB = mayorCandidateDao.getById(mayorCandidate.getId());
//        assertEquals(mayorCandidate, mayorCandidateFromDB);
//    }
//
//    @Test
//    public void testInsertMayorCandidateWithNullVoter() {
//        assertThrows(RuntimeException.class, () -> {
//            insertMayorCandidate(null);
//        });
//    }
//
////
//    @Test
//    public void testInsertSubjectWithNullName() {
//        assertThrows(RuntimeException.class, () -> {
//            Subject subject = new Subject(null);
//            subjectDao.insert(subject);
//        });
//    }
//
//    @Test
//    public void testUpdateSubject() {
//        Subject subject = insertSubject("Linux");
//        Subject subjectFromDB = subjectDao.getById(subject.getId());
//        assertEquals(subject, subjectFromDB);
//        subject.setName("Windows");
//        subjectDao.update(subject);
//        subjectFromDB = subjectDao.getById(subject.getId());
//        assertEquals(subject, subjectFromDB);
//    }
//
//    @Test
//    public void testUpdateSubjectSetNullName() {
//        assertThrows(RuntimeException.class, () -> {
//            Subject subject = insertSubject("Linux");
//            Subject subjectFromDB = subjectDao.getById(subject.getId());
//            assertEquals(subject, subjectFromDB);
//            subject.setName(null);
//            subjectDao.update(subject);
//        });
//    }
//
//    @Test
//    public void testDeleteSubject() {
//        Subject subject = insertSubject("Linux");
//        Subject subjectFromDB = subjectDao.getById(subject.getId());
//        assertEquals(subject, subjectFromDB);
//        subjectDao.delete(subject);
//        subjectFromDB = subjectDao.getById(subject.getId());
//        assertNull(subjectFromDB);
//    }
//
//    @Test
//    public void testInsertTwoSubjects() {
//        Subject subjectLinux = insertSubject("Linux");
//        Subject subjectMySQL = insertSubject("MySQL");
//        List<Subject> subjects = new ArrayList<>();
//        subjects.add(subjectLinux);
//        subjects.add(subjectMySQL);
//        List<Subject> subjectsFromDB = subjectDao.getAll();
//        assertEquals(subjects, subjectsFromDB);
//    }

}
