/**
 * Created by Kornel Hamula on 2018. 05. 12..
 */
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class MyRESTTest {

    final static Logger logger = Logger.getLogger(MyRESTTest.class);

    @Test
    public void checkResultTest() {
            try {
               HttpResponse<JsonNode> jsonNodeHttpResponse =
                       Unirest.get("http://localhost:8080/today")
                        .asJson();
               logger.info(jsonNodeHttpResponse.getHeaders().toString());
               logger.info(jsonNodeHttpResponse.getBody().toString());
               logger.info("Checking the \"response status\" is 200...");
               assertThat(jsonNodeHttpResponse.getStatus(), is(equalTo(200)));
               logger.info("Expected: 200, "+ "Actual: "+jsonNodeHttpResponse.getStatus());
               logger.info("Checking the \"format of date is YYYY-MM-DD\"...");
               assertThat(jsonNodeHttpResponse.getBody().getObject().get("date").toString(), is(matchesPattern("\\d{4}-\\d{2}-\\d{2}")));
               logger.info("Expected: YYYY-MM-DD, "+ "Actual: "+jsonNodeHttpResponse.getBody().getObject().get("date").toString());
               logger.info("Checking the \"status is holiday or workday\"...");
               assertThat(jsonNodeHttpResponse.getBody().getObject().get("status").toString(), is(matchesPattern("workday||holiday")));
               logger.info("Expected: workday or holiday, "+ "Actual: "+jsonNodeHttpResponse.getBody().getObject().get("status").toString());
            }
            catch (UnirestException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void alwaysFailTest() {
        logger.info("This test will always fail...");
        assertThat(199, is(equalTo(200)));
    }
}
