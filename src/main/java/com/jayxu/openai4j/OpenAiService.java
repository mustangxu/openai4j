/**
 *
 */
package com.jayxu.openai4j;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.jayxu.openai4j.model.CompletionRequest;
import com.jayxu.openai4j.model.CompletionResponse;
import com.jayxu.openai4j.model.ErrorResponse;
import com.jayxu.openai4j.model.ImageRequest;
import com.jayxu.openai4j.model.ImageResponse;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelList;
import com.jayxu.openai4j.model.ServiceException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Jay Xu @2023
 */
public interface OpenAiService {
    String BASE_URL = "https://api.openai.com/v1/";
    Logger log = LoggerFactory.getLogger(OpenAiService.class);

    static OpenAiService init(String apikey) {
        return init(apikey, Level.BODY, Duration.ofMinutes(1));
    }

    static OpenAiService init(String apikey, Level logLevel, Duration timeout) {
        var json = Jackson2ObjectMapperBuilder.json().build();

        var interceptor = new HttpLoggingInterceptor(l -> {
            log.debug(l);
        });
        interceptor.setLevel(logLevel);

        var okhttp = new OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(chain -> {
                var newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + apikey).build();
                return chain.proceed(newRequest);
            }).addInterceptor(chain -> {
                var resp = chain.proceed(chain.request());

                if (resp.isSuccessful()) {
                    return resp;
                }

                throw new ServiceException(resp.code(),
                    json.readValue(resp.body().string(), ErrorResponse.class)
                        .getError());
            }).callTimeout(timeout).connectTimeout(timeout).readTimeout(timeout)
            .build();

        return new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(okhttp).build().create(OpenAiService.class);
    }

    /**
     * @see <a href=
     *      "https://platform.openai.com/docs/api-reference/models/list">https://platform.openai.com/docs/api-reference/models/list</a>
     */
    @GET("models")
    Call<ModelList> listModels();

    /**
     * @see <a href=
     *      "https://platform.openai.com/docs/api-reference/models/retrieve">https://platform.openai.com/docs/api-reference/models/retrieve</a>
     */
    @GET("models/{model}")
    Call<Model> retrieveModel(@Path("model") String model);

    /**
     * @see <a href=
     *      "https://platform.openai.com/docs/api-reference/chat/create">https://platform.openai.com/docs/api-reference/chat/create</a>
     */
    @POST("chat/completions")
    Call<CompletionResponse> createChat(@Body CompletionRequest chat);

    /**
     * @see <a href=
     *      "https://platform.openai.com/docs/api-reference/completions/create">https://platform.openai.com/docs/api-reference/completions/create</a>
     */
    @POST("completions")
    Call<CompletionResponse> createCompletions(@Body CompletionRequest chat);

    /**
     * @see <a href=
     *      "https://platform.openai.com/docs/api-reference/images/create">https://platform.openai.com/docs/api-reference/images/create</a>
     */
    @POST("images/generations")
    Call<ImageResponse> createImage(@Body ImageRequest request);

}
