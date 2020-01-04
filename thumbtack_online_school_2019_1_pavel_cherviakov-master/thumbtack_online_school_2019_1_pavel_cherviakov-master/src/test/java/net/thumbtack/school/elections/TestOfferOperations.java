package net.thumbtack.school.elections;

import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Voter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOfferOperations extends TestBase {

//    @Test
//    public void testInsertOffer() {
//        Voter voter = insertVoter("Иван", "Иванов","Иванович",
//                "Московская" ,"5", 6,"polk", "poll");
//        String content = "площадь";
//        Offer offer = insertOffer(voter, content);
//        Offer offerFromDB = offerDao.getById(offer.getId());
//        assertEquals(offer, offerFromDB);
//    }
//
//    @Test
//    public void testInsertOfferWithNullVoter() {
//        assertThrows(RuntimeException.class, () -> {
//            String content = "площадь";
//            insertOffer(null, content);
//        });
//    }
//
//    @Test
//    public void testInsertOfferWithNullContent() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter voter = insertVoter("Иван", "Иванов","Иванович",
//                    "Московская" ,"5", 6,"polk", "poll");
//            Offer offer = insertOffer(voter, null);
//        });
//    }
//
//    @Test
//    public void testInsertSchoolWithNullName() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter school = new Voter(null, 2018);
//            schoolDao.insert(school);
//        });
//    }
//
//
//    @Test
//    public void testUpdateSchool() {
//        Voter school = insertTTSchool("TTSchool", 2018);
//        Voter schoolFromDB = schoolDao.getById(school.getId());
//        assertEquals(school, schoolFromDB);
//        school.setName("Школа ТТ");
//        school.setYear(2019);
//        schoolDao.update(school);
//        schoolFromDB = schoolDao.getById(school.getId());
//        assertEquals(school, schoolFromDB);
//    }
//
//    @Test
//    public void testChangeSchoolNameToNull() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter school2018 = insertTTSchool("TTSchool", 2018);
//            assertNotEquals(0, school2018.getId());
//            Voter schoolFromDB = schoolDao.getById(school2018.getId());
//            assertEquals(school2018, schoolFromDB);
//            school2018.setName(null);
//            schoolDao.update(school2018);
//        });
//    }
//
//    @Test
//    public void testDeleteSchool() {
//        Voter school2018 = insertTTSchool("TTSchool", 2018);
//        Voter schoolFromDB = schoolDao.getById(school2018.getId());
//        assertEquals(school2018, schoolFromDB);
//        schoolDao.delete(school2018);
//        schoolFromDB = schoolDao.getById(school2018.getId());
//        assertNull(schoolFromDB);
//    }
//
//    @Test
//    public void testInsertTwoSchools() {
//        Voter school2018 = insertTTSchool("TTSchool", 2018);
//        Voter school2019 = insertTTSchool("TTSchool", 2019);
//        List<Voter> schoolsFromDB = schoolDao.getAllLazy();
//        assertEquals(2, schoolsFromDB.size());
//        schoolsFromDB.sort(Comparator.comparingInt(Voter::getId));
//        assertEquals(school2018, schoolsFromDB.get(0));
//        assertEquals(school2019, schoolsFromDB.get(1));
//    }
//
//    @Test
//    public void testInsertTwoSchoolsWithSameNameAndYear() {
//        assertThrows(RuntimeException.class, () -> {
//            Voter school20181 = insertTTSchool("TTSchool", 2018);
//            Voter school20182 = insertTTSchool("TTSchool", 2018);
//        });
//    }


}
