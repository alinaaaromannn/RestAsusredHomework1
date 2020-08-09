package com.endava.Homework;

import com.endava.homework.models.Owner;
import com.endava.homework.models.Pet;
import com.endava.homework.models.Type;
import com.endava.homework.models.Visit;
import com.endava.homework.util.EnvReader;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomeworkTest {

    private Faker faker = new Faker();
    private Owner myOwner;
    private Pet myPet;

    @BeforeEach
    public void setUp() {
        myOwner = new Owner();

        myOwner.setId(faker.number().randomDigit());
        myOwner.setAddress(faker.address().streetAddress());
        myOwner.setCity(faker.address().city());
        myOwner.setFirstName(faker.name().firstName());
        myOwner.setLastName(faker.name().lastName());
        myOwner.setTelephone(faker.number().digits(10));
        myOwner.setPets(Collections.EMPTY_LIST);

        myPet = new Pet().builder()
                .name("tom")
                .birthDate("2020/02/02")
                .type(new Type(6, "hamster"))
                .owner(myOwner)
                .visits(Collections.emptyList())
                .build();
    }

    @Test
    public void testAddGetOwnerAPI() {

        ValidatableResponse postOwnerResponse = given() //request
                .baseUri(EnvReader.getBaseUri())
                .port(EnvReader.getPort())
                .basePath(EnvReader.getBasePath())
                .contentType(ContentType.JSON)
                .log().all()
                .body(myOwner)
                .post("/api/owners")
                //response
                .prettyPeek()
                //validation
                .then().statusCode(HttpStatus.SC_CREATED);

        Integer idAddedOwner = postOwnerResponse.extract().jsonPath().getInt("id");

        ValidatableResponse getOwnerResponse = given()
                .baseUri(EnvReader.getBaseUri())
                .port(EnvReader.getPort())
                .basePath(EnvReader.getBasePath())
                .pathParam("ownerId", idAddedOwner)
                .log().all()
                .get("/api/owners/{ownerId}")
                //response
                .prettyPeek()
                //validation
                .then().statusCode(HttpStatus.SC_OK);

        Owner actualOwner = getOwnerResponse.extract().as(Owner.class);
        assertThat(actualOwner, is(myOwner));
    }

    @Test
    public void testAddGetPetAPI() {

        myPet.setId(12);

        ValidatableResponse postPetResponse = given() //request
                .baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .log().all()
                .body(myPet)
                .post("/api/pets")
                //response
                .prettyPeek()
                //validation
                .then().statusCode(HttpStatus.SC_CREATED);

        ValidatableResponse getPetsResponse = given()
                .baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("/petclinic")
                .log().all()
                .get("/api/pets")
                .prettyPeek()
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createVisit() {

        myPet.setId(20);

        Visit myVisit = new Visit().builder()
                .date("2020/04/12")
                .description("Description")
                .id(111)
                .pet(myPet)
                .build();

        given()
                .baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .log().all()
                .body(myVisit)
                .post("/api/visits")
                .prettyPeek()
                .then().statusCode(HttpStatus.SC_CREATED);
    }
}
