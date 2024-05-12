//package org.sanjay.learnerservice;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.sanjay.learnerservice.dto.EnrollmentDTO;
//import org.sanjay.learnerservice.dto.EnrollmentItemsDTO;
//import org.sanjay.learnerservice.dto.ProgressTrackerDTO;
//
//import java.time.LocalDateTime;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class EnrollmentServiceApplicationTests {
//
//    @BeforeAll
//    public static void setup() {
//        // Set base URI for Rest Assured
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 7010; // Your server port
//    }
//
//    @Test
//    public void testCreateEnrollment() {
//        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
//        enrollmentDTO.setUserId("user123");
//        enrollmentDTO.setEnrollmentDate(LocalDateTime.now());
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(enrollmentDTO)
//                .when()
//                .post("/api/v1/enrollment")
//                .then()
//                .statusCode(200)
//                .body("userId", equalTo("user123"));
//    }
//
//    @Test
//    public void testGetAllEnrollments() {
//        when()
//                .get("/api/v1/enrollment")
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThanOrEqualTo(1)); // Check there's at least one enrollment
//    }
//
//    @Test
//    public void testGetEnrollmentById() {
//        long enrollmentId = 1L; // Ensure this ID exists
//        when()
//                .get("/api/v1/enrollment/" + enrollmentId)
//                .then()
//                .statusCode(200)
//                .body("id", equalTo((int) enrollmentId));
//    }
//
////    @Test
////    public void testUpdateEnrollment() {
////        long enrollmentId = 1; // Ensure this ID exists
////        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
////        enrollmentDTO.setUserId("updatedUser");
////
////        given()
////                .contentType(ContentType.JSON)
////                .body(enrollmentDTO)
////                .when()
////                .put("/api/v1/enrollment/" + enrollmentId)
////                .then()
////                .statusCode(200)
////                .body("userId", equalTo("updatedUser"));
////    }
//
////    @Test
////    public void testAddEnrollmentItem() {
////        long enrollmentId = 1; // Ensure this ID exists
////        EnrollmentItemsDTO enrollmentItemsDTO = new EnrollmentItemsDTO();
////        enrollmentItemsDTO.setCourseId("course456");
////        enrollmentItemsDTO.setCompleted(true);
////
////        given()
////                .contentType(ContentType.JSON)
////                .body(enrollmentItemsDTO)
////                .when()
////                .post("/api/v1/enrollment/" + enrollmentId + "/items")
////                .then()
////                .statusCode(200)
////                .body("courseId", equalTo("course456"));
////    }
//
//    @Test
//    public void testGetEnrollmentsByUserId() {
//        String userId = "user425";
//        when()
//                .get("/api/v1/enrollment/user/" + userId)
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThanOrEqualTo(1)); // Check there's at least one enrollment
//    }
//
//    @Test
//    public void testUpdateProgressTracker() {
//        long enrollmentId = 1; // Ensure this ID exists
//        String courseId = "c42613"; // Ensure this course ID exists
//        ProgressTrackerDTO progressTrackerDTO = new ProgressTrackerDTO();
//        progressTrackerDTO.setContentId("content123");
//        progressTrackerDTO.setAddedDate(LocalDateTime.now());
//        progressTrackerDTO.setLastUpdatedDate(LocalDateTime.now());
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(progressTrackerDTO)
//                .when()
//                .patch("/api/v1/enrollment/" + enrollmentId + "/courses/" + courseId + "/progress")
//                .then()
//                .statusCode(200)
//                .body("enrollmentItemsList[0].progressTrackerItemsList[0].contentId", equalTo("content123"));
//    }
//
//}
