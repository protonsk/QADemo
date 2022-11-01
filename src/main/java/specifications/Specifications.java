package specifications;

import base.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri(TestBase.CONFIG.getString("baseUri"))
            .setContentType(ContentType.JSON).build();

    public static ResponseSpecification resSpec = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
}
