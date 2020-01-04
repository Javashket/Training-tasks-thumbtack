package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO trainee (firstname, lastname, rating) VALUES ('" +
          trainee.getFirstName() + "','" + trainee.getLastName() + "'," + trainee.getRating() + ");", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()) {
         trainee.setId(rs.getInt(1));
        }
    }

    public  static void updateTrainee(Trainee trainee) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE trainee SET firstname ='" + trainee.getFirstName()
                + "', lastname='" + trainee.getLastName() + "', rating=" + trainee.getRating() + " WHERE id =" +
                trainee.getId() +";");
        preparedStatement.executeUpdate();
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trainee WHERE id =" + traineeId + ";");
        ResultSet rs =  preparedStatement.executeQuery();
        if (rs.next()) {
            return new Trainee(rs.getInt("id"), rs.getString("firstname"),
                    rs.getString("lastname"), rs.getInt("rating"));
        }
        return null;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trainee WHERE id =" + traineeId + ";");
        ResultSet rs =  preparedStatement.executeQuery();
        if (rs.next()) {
            return new Trainee(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4));
        }
        return null;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trainee;");
        ResultSet rs =  preparedStatement.executeQuery();
        while (rs.next()) {
            trainees.add(new Trainee(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4)));
        }
        return trainees;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trainee;");
        ResultSet rs =  preparedStatement.executeQuery();
        while (rs.next()) {
            trainees.add(new Trainee(rs.getInt("id"), rs.getString("firstname"),
                    rs.getString("lastname"), rs.getInt("rating")));
        }
        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM trainee WHERE id= " +
                trainee.getId() + ";");
        preparedStatement.executeUpdate();
    }

    public static void deleteTrainees() throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM trainee;");
        preparedStatement.executeUpdate();
    }

    public static void insertSubject(Subject subject) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subject (name) VALUES ('" +
                subject.getName()  +"');", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()) {
            subject.setId(rs.getInt(1));
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subject WHERE id =" + subjectId + ";");
        ResultSet rs =  preparedStatement.executeQuery();
        if (rs.next()) {
            return new Subject(rs.getInt("id"), rs.getString("name"));
        }
        return null;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subject WHERE id =" + subjectId + ";");
        ResultSet rs =  preparedStatement.executeQuery();
        if (rs.next()) {
            return new Subject(rs.getInt(1), rs.getString(2));
        }
        return null;
    }

    public static void deleteSubjects() throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM subject;");
        preparedStatement.executeUpdate();
    }

    public static void insertSchool(School school) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO school (name, year) VALUES ('" +
                school.getName() + "'," + school.getYear() + ");", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()) {
            school.setId(rs.getInt(1));
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM school WHERE id =" + schoolId + ";");
        ResultSet rs =  preparedStatement.executeQuery();
        if (rs.next()) {
            return new School(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("year"));
        }
        return null;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM school WHERE id =" + schoolId + ";");
        ResultSet rs =  preparedStatement.executeQuery();
        if (rs.next()) {
            return new School(rs.getInt(1), rs.getString(2),
                    rs.getInt(3));
        }
        return null;
    }

    public static void deleteSchools() throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM school;");
        preparedStatement.executeUpdate();
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO groupp (name, room, school_id) VALUES ('" +
                group.getName() + "','" + group.getRoom() + "'," + school.getId() + ");", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()) {
            group.setId(rs.getInt(1));
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
       for(School school : getSchoolsWithGroups()) {
           if( id == school.getId()) {
               return school;
           }
       }
       return null;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        List<School> schools = new ArrayList<>();
        JdbcUtils.createConnection();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT school.id, school.name," +
                "school.year, groupp.id, groupp.name, groupp.room FROM school, groupp" +
                " WHERE school.id = groupp.school_id");
        ResultSet rs =  preparedStatement.executeQuery();
        while (rs.next()) {
            if(schools.isEmpty()) {
                schools.add(new School(rs.getInt(1), rs.getString(2),
                        rs.getInt(3)));
            }
            List<School> schools1 = new ArrayList<>();
            for (School school: schools) {
                boolean dublicate = true;
                for (School school1 : schools) {
                    if(school1.getId() == rs.getInt(1)) {
                        dublicate = false;
                    }
                }
                if(dublicate) {
                    schools1.add(new School(rs.getInt(1), rs.getString(2),
                            rs.getInt(3)));
                }
                schools.get(schools.size() - 1).addGroup( new Group(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3))
                );
            }
            schools.addAll(schools1);
        }
        return schools;
    }
}
