package net.thumbtack.school.elections;

import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.model.Voter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRatingOperations extends TestBase {
//
//    @Test
//    public void testInsertRating() {
//        Voter voter = insertVoter("Иван", "Иванов","Иванович",
//                "Московская" ,"5", 6,"polk", "poll");
//        String context = "площадь";
//        Offer offer = insertOffer(voter, context);
//        Rating rating = insertRating(voter, 5, offer);
//        Rating ratingFromDB = ratingDao.getById(rating.getId());
//        assertEquals(rating, ratingFromDB);
//    }
//
//    @Test
//    public void testInsertRatingWithNullVoter() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter voter = insertVoter("Иван", "Иванов","Иванович",
//                    "Московская" ,"5", 6,"polk", "poll");
//            String context = "площадь";
//            Offer offer = insertOffer(voter, context);
//            insertRating(null, 5, offer);
//        });
//    }
//
//    @Test
//    public void testInsertRatingWithNullNumberRating() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter voter = insertVoter("Иван", "Иванов","Иванович",
//                    "Московская" ,"5", 6,"polk", "poll");
//            String context = "площадь";
//            Offer offer = insertOffer(voter, context);
//            insertRating(voter, null, offer);
//        });
//    }
//
//    @Test
//    public void testInsertRatingWithNullOffer() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter voter = insertVoter("Иван", "Иванов","Иванович",
//                    "Московская" ,"5", 6,"polk", "poll");
//            insertRating(voter, 5, null);
//        });
//    }
//
//    @Test
//    public void testUpdateTrainee() {
//        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
//        Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
//        assertEquals(traineeIvanov, traineeIvanovFromDB);
//        traineeIvanov.setLastName("Федор");
//        traineeDao.update(traineeIvanov);
//        traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
//        assertEquals(traineeIvanov, traineeIvanovFromDB);
//    }
//
//    @Test
//    public void testUpdateTraineeSetNullLastName() {
//        assertThrows(RuntimeException.class, () -> {
//            Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
//            Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
//            assertEquals(traineeIvanov, traineeIvanovFromDB);
//            traineeIvanov.setLastName(null);
//            traineeDao.update(traineeIvanov);
//        });
//    }
//
//    @Test
//    public void testDeleteTrainee() {
//        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
//        Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
//        assertEquals(traineeIvanov, traineeIvanovFromDB);
//        traineeDao.delete(traineeIvanov);
//        traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
//        assertNull(traineeIvanovFromDB);
//    }
//
//    @Test
//    public void testInsertTwoTrainees() {
//        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
//        Trainee traineePetrov = insertTrainee("Петр", "Петров", 4);
//        List<Trainee> trainees = new ArrayList<>();
//        trainees.add(traineeIvanov);
//        trainees.add(traineePetrov);
//        List<Trainee> traineesFromDB = traineeDao.getAll();
//        assertEquals(trainees, traineesFromDB);
//    }
//
//    @Test
//    public void testIfCondition() {
//        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
//        Trainee traineePetrovPetr = insertTrainee("Петр", "Петров", 4);
//        Trainee traineePetrovFedor = insertTrainee("Федор", "Петров", 3);
//        Trainee traineeSidorov = insertTrainee("Петр", "Сидоров", 4);
//        Trainee traineeSidorenko = insertTrainee("Иван", "Сидоренко", 2);
//
//        List<Trainee> traineesFull = new ArrayList<>();
//        traineesFull.add(traineeIvanov);
//        traineesFull.add(traineePetrovPetr);
//        traineesFull.add(traineePetrovFedor);
//        traineesFull.add(traineeSidorov);
//        traineesFull.add(traineeSidorenko);
//
//        List<Trainee> traineesIvan = new ArrayList<>();
//        traineesIvan.add(traineeIvanov);
//        traineesIvan.add(traineeSidorenko);
//
//        List<Trainee> traineesSidor = new ArrayList<>();
//        traineesSidor.add(traineeSidorov);
//        traineesSidor.add(traineeSidorenko);
//
//        List<Trainee> traineesPetrovWithRating4 = new ArrayList<>();
//        traineesPetrovWithRating4.add(traineePetrovPetr);
//
//        List<Trainee> traineesFullFromDB = traineeDao.getAll();
//        traineesFullFromDB.sort(Comparator.comparingInt(Trainee::getId));
//        assertEquals(traineesFull, traineesFullFromDB);
//
//        List<Trainee> traineesIvanFromDB = traineeDao.getAllWithParams("Иван", null, null);
//        traineesIvanFromDB.sort(Comparator.comparingInt(Trainee::getId));
//        assertEquals(traineesIvan, traineesIvanFromDB);
//
//        List<Trainee> traineesSidorFromDB = traineeDao.getAllWithParams(null, "Сидор%", null);
//        traineesSidorFromDB.sort(Comparator.comparingInt(Trainee::getId));
//        assertEquals(traineesSidor, traineesSidorFromDB);
//
//        List<Trainee> traineesPetrovWithRating4FromDB = traineeDao.getAllWithParams(null, "Петров", 4);
//        traineesPetrovWithRating4FromDB.sort(Comparator.comparingInt(Trainee::getId));
//        assertEquals(traineesPetrovWithRating4, traineesPetrovWithRating4FromDB);
//
//    }
//
//    @Test
//    public void testBatchInsertTrainees() {
//        Trainee traineeIvanov = new Trainee("Иван", "Иванов", 5);
//        Trainee traineePetrovPetr = new Trainee("Петр", "Петров", 4);
//        Trainee traineePetrovFedor = new Trainee("Федор", "Петров", 3);
//        Trainee traineeSidorov = new Trainee("Петр", "Сидоров", 4);
//        Trainee traineeSidorenko = new Trainee("Иван", "Сидоренко", 2);
//
//        List<Trainee> traineesFull = new ArrayList<>();
//        traineesFull.add(traineeIvanov);
//        traineesFull.add(traineePetrovPetr);
//        traineesFull.add(traineePetrovFedor);
//        traineesFull.add(traineeSidorov);
//        traineesFull.add(traineeSidorenko);
//        traineeDao.batchInsert(traineesFull);
//        List<Trainee> traineesFullFromDB = traineeDao.getAll();
//        traineesFullFromDB.sort(Comparator.comparingInt(Trainee::getId));
//        assertEquals(traineesFull, traineesFullFromDB);
//    }

}


