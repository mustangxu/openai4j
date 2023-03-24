/**
 *
 */
package com.jayxu.openai4j;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jayxu.openai4j.model.ChatRequest;
import com.jayxu.openai4j.model.Message;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelType;

/**
 * @author Jay Xu @2023
 */
class OpenAiServiceTest {
    private static OpenAiService service;

    @BeforeAll
    static void setup() {
        var apikey = System.getenv("OPENAI_APIKEY");
        if (Strings.isBlank(apikey)) {
            apikey = System.getProperty("openai.apikey");
        }

        assertFalse(Strings.isBlank(apikey), "apikey");

        service = OpenAiService.init(apikey);
    }

    @Test
    void testListModels() throws Exception {
        var models = service.listModels().execute().body();
        assertNotNull(models);

        models.getData().stream().limit(3).forEach(System.out::println);
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
        var chat = ChatRequest.builder().messages(Arrays.asList(msg))
            .model(ModelType.GPT_35_TURBO.value()).build();

        System.out.println(chat);
        var resp = service.chat(chat).execute().body();
        assertNotNull(resp);

        System.out.println(resp);
        System.out.println(resp.getChoices().get(0).getMessage());
    }
}
