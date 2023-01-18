package delivery.member.acceptance;

import delivery.auth.dto.TokenResponse;
import delivery.member.dto.MemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberAcceptance {
    public static ExtractableResponse<Response> create_member(String email, String password, String name) {
        MemberRequest memberRequest = new MemberRequest(email, password, name);

        return RestAssured
                .given().log().all()
                .body(memberRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/members")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    public static ExtractableResponse<Response> select_me(TokenResponse tokenResponse) {
        return RestAssured
                .given().log().all()
                .auth().oauth2(tokenResponse.getAccessToken())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/me")
                .then().log().all()
                .extract();
    }
}
