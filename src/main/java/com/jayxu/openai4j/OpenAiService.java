/**
 *
 */
package com.jayxu.openai4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.jayxu.openai4j.model.ChatRequest;
import com.jayxu.openai4j.model.ChatResponse;
import com.jayxu.openai4j.model.ErrorResponse;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelList;
import com.jayxu.openai4j.model.ServiceException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
        var interceptor = new HttpLoggingInterceptor(l -> {
            log.debug(l);
        });
        interceptor.setLevel(Level.HEADERS);

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
                    new Gson()
                        .fromJson(resp.body().string(), ErrorResponse.class)
                        .getError());
            }).build();

        return new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okhttp)
            .build().create(OpenAiService.class);
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

    @POST("chat/completions")
    Call<ChatResponse> chat(@Body ChatRequest chat);
}
