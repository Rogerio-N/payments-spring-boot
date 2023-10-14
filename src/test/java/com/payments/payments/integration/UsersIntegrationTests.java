package com.payments.payments.integration;

import com.payments.payments.integration.helpers.UsersHelper;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersIntegrationTests {

	private final UsersHelper usersHelper = new UsersHelper();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@LocalServerPort
	private Integer port = 0;

	@BeforeEach
	void setup() {
		baseURI = "http://localhost:"+port;
	}

	@AfterEach
	void clear() {
		jdbcTemplate.update("DELETE FROM USERS");
	}

	@Test
	@Sql("classpath:db/createUsers.sql")
    @DisplayName("Sucesso ao listar usuários")
	void givenUsersAlreadyRegistered_whenGetAllUsers_thenSizeIs2() {
		given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/users")
                .then()
                .assertThat()
                .body("size()", is(2))
				.statusCode(200);
	}

	@Test
	@DisplayName("Sucesso ao listar usuários quando não tiver usuário cadastrados")
	void givenEmptyUsers_whenGetAllUsers_ThenSizeIs0() {
		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/v1/users")
				.then()
				.assertThat()
				.body("size()", is(0))
				.statusCode(200);
	}

	@Test
	@DisplayName("Sucesso ao criar novo usuário")
	void givenValidNewUser_whenCreateUser_thenSuccessToCreate() throws JSONException {
		given()
				.contentType(ContentType.JSON)
				.body(usersHelper.createPFUserBody().toString())
				.when()
				.post("/v1/users")
				.then()
				.assertThat()
				.body("$", hasKey("id"))
				.body("$", not(hasKey("password")))
				.body("firstName", equalTo(usersHelper.createPFUserBody().get("firstName").toString()));

		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/v1/users")
				.then()
				.assertThat()
				.body("size()", is(1))
				.statusCode(200);

	}

	@Test
	@Sql("classpath:db/createUsers.sql")
	@DisplayName("Erro ao criar novo usuário, email já utilizado")
	void givenEmailAlreadyUsed_whenCreateUser_thenShouldReturnError() throws JSONException {
		JSONObject body = usersHelper.createPFUserBody();
		body.put("email", "test1@mock.com");
		given()
				.contentType(ContentType.JSON)
				.body(body.toString())
				.when()
				.post("/v1/users")
				.then()
				.assertThat()
				.statusCode(400)
				.body("$", hasKey("message"))
				.body("message", equalTo("Email ou documento já cadastrados"));
	}

	@Test
	@Sql("classpath:db/createUsers.sql")
	@DisplayName("Erro ao criar novo usuário, documento já utilizado")
	void givenDocumentAlreadyUsed_whenCreateUser_thenShouldReturnError() throws JSONException {
		JSONObject body = usersHelper.createPFUserBody();
		body.put("document", "26535588076");
		given()
				.contentType(ContentType.JSON)
				.body(body.toString())
				.when()
				.post("/v1/users")
				.then()
				.assertThat()
				.statusCode(400)
				.body("$", hasKey("message"))
				.body("message", equalTo("Email ou documento já cadastrados"));
	}
}
