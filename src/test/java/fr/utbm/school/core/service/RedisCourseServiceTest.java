package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.service.impl.RedisCourseServiceImpl;
import fr.utbm.school.core.tools.RedisUtil;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RedisCourseServiceTest {

    @BeforeAll
    static void init(){
        new RedisUtil().clearRedis();
    }

    @AfterAll
    static void ending(){
        new RedisUtil().clearRedis();
    }

    @Test
    @Order(1)
    void save(){
        Course courseTest = new Course("Test", "Test");
        new RedisCourseServiceImpl().save(courseTest);
    }

    @Test
    @Order(2)
    void getCourseById() {
        Course courseTest = new Course("Test", "Test");

        Course courseGetted = new RedisCourseServiceImpl().getCourseById("Test");
        Assertions.assertEquals(courseTest, courseGetted);
    }

    @Test
    @Order(3)
    void getCourseByTitle() {
        Course courseTest = new Course("Test", "Test");

        Collection<Course> courseGetted = new RedisCourseServiceImpl().getCourseByTitle("Test");
        Assertions.assertTrue(courseGetted.contains(courseTest));
    }

    @Test
    @Order(4)
    void getListCourse() {
        Assertions.assertTrue(new RedisCourseServiceImpl().getListCourse().size() == 1);
    }

    @Test
    @Order(5)
    void update() {
        Course courseTest = new Course("Test", "TitleModified");
        new RedisCourseServiceImpl().update(courseTest);

        Assertions.assertEquals("TitleModified",
                new RedisCourseServiceImpl().getCourseById("Test").getTitle());
    }

    @Test
    @Order(6)
    void publish() {
        new RedisCourseServiceImpl().subscribe("Test",
                (channel, message) -> System.out.print("Test OK"));

        Long nbSubcriber = new RedisCourseServiceImpl().publish("Test", new Course("PublishedTest", "Test"));

        Assertions.assertEquals(1L, nbSubcriber);

    }
}