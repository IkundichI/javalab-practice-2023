package ru.itis.repositories.SpringJdbcImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.models.Course;
import ru.itis.repositories.CourseRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CourseRepositorySpringJdbcImpl implements CourseRepository {

    private static final String COURSE_TABLE = "course";

    private final static String SQL_SELECT_ALL = "select * from course";

    private final DataSource dataSource;

    private final SimpleJdbcInsert insertCourse;

    private final JdbcTemplate jdbcTemplate;

    public CourseRepositorySpringJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;

        jdbcTemplate = new JdbcTemplate(dataSource);

        insertCourse = new SimpleJdbcInsert(dataSource);

        insertCourse.withTableName(COURSE_TABLE)
                .usingGeneratedKeyColumns("id");
    }

    private static final Function<Course, Map<String, Object>> toParams = course -> {
        Map<String, Object> params = new HashMap<>();

        params.put("title", course.getTitle());
        params.put("start_date", course.getStart_date());
        params.put("finish_date", course.getFinish_date());

        return params;
    };

    private static final RowMapper<Course> toCourse = (row, rowNumber) -> Course.builder()
            .id(row.getInt("id"))
            .title(row.getString("title"))
            .start_date(row.getDate("start_date"))
            .finish_date(row.getDate("finish_date"))
            .build();

    @Override
    public void save(Course model) {

        int generatedId = insertCourse.executeAndReturnKey(toParams.apply(model)).intValue();

        model.setId(generatedId);
    }


    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toCourse);
    }
}

