package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.models.Course;
import ru.itis.models.Student;
import ru.itis.repositories.CourseRepository;
import ru.itis.repositories.JdbcImpl.CourseRepositoryJdbcImpl;
import ru.itis.repositories.StudentsRepository;
import ru.itis.repositories.StudentsRepositoryJdbcImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource);
        CourseRepository courseRepository = new CourseRepositoryJdbcImpl(dataSource);

        System.out.println(studentsRepository.findAll());
        System.out.println(courseRepository.findAll());

//        Student student = Student.builder()
//                .firstName("Имя1")
//                .lastName("Фамилия1")
//                .age(25)
//                .build();
//
//        System.out.println(student);
//        studentsRepository.save(student);
//        System.out.println(student);
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.now().plusMonths(1);
        Date date1 = java.sql.Date.valueOf(localDate);
        Date date2 = java.sql.Date.valueOf(localDate1);
        Course course = Course.builder()
                .title("Algebra")
                .start_date(date1)
                .finish_date(date2)
                .build();

        courseRepository.save(course);


        System.out.println(studentsRepository.findAll());
        System.out.println(courseRepository.findAll());
    }
}
