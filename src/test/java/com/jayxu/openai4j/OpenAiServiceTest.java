/**
 *
 */
package com.jayxu.openai4j;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.jayxu.openai4j.model.CompletionRequest;
import com.jayxu.openai4j.model.Message;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelType;
import com.jayxu.openai4j.model.ServiceException;

import okhttp3.logging.HttpLoggingInterceptor.Level;

/**
 * @author Jay Xu @2023
 */
@Execution(ExecutionMode.CONCURRENT)
class OpenAiServiceTest {
    private static OpenAiService service;

    @BeforeAll
    static void setup() {
        var apikey = System.getenv("OPENAI_APIKEY");
        if (Strings.isBlank(apikey)) {
            apikey = System.getProperty("openai.apikey");
        }

        assertFalse(Strings.isBlank(apikey), "apikey");

        service = OpenAiService.init(apikey, Level.BASIC,
            Duration.ofSeconds(10));
    }

    @Test
    void testListModels() throws Exception {
        var models = service.listModels().execute().body();
        assertNotNull(models);

//        models.getData().stream().limit(3).forEach(System.out::println);
        models.getData().stream().map(Model::getId).sorted()
            .forEach(System.out::println);
    }

    @Test
    void testRetrieveModel() throws Exception {
        var models = service.listModels().execute().body();
        var model = service.retrieveModel(models.getData().get(0).getId())
            .execute().body();
        assertNotNull(model);

        System.out.println(model);
    }

    @Test
    void testChat() throws Exception {
        var msg = Message.builder().role("user").content("写一首七绝").build();
        var chat = CompletionRequest.builder().message(msg)
            .model(ModelType.GPT_35_TURBO.value()).build();

        System.out.println(chat);

        try {
            var resp = service.createChat(chat).execute().body();
            assertNotNull(resp);

//            System.out.println(resp);
            System.out.println(resp.getChoices().get(0).getMessage());
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void testCompletion() throws Exception {
        var chat = CompletionRequest.builder().prompt("hello")
            .model(ModelType.TEXT_DAVINCI_003.value()).build();

        System.out.println(chat);

        try {
            var resp = service.createCompletions(chat).execute().body();
            assertNotNull(resp);

//            System.out.println(resp);
            System.out.println(resp.getChoices().get(0).getText());
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }
}
