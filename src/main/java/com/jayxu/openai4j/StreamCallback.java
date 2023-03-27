/**
 *
 */
package com.jayxu.openai4j;

import java.util.Scanner;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import reactor.core.publisher.FluxSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jay Xu @2023
 */
@Slf4j
public class StreamCallback<T> implements Callback<ResponseBody> {
    private static final String DATA_PREFIX = "data: ";
    private static final String EOF = "[DONE]";
    private FluxSink<T> sink;
    private Class<T> type;

    public StreamCallback(FluxSink<T> sink, Class<T> type) {
        this.type = type;
        this.sink = sink;
    }

    @Override
    public void onResponse(Call<ResponseBody> call,
            Response<ResponseBody> response) {
        var scan = new Scanner(response.body().byteStream())
            .useDelimiter(DATA_PREFIX);
        var gson = new Gson();

        while (scan.hasNext()) {
            try {
                var s = scan.next().trim();
                log.debug(s);

                if (EOF.equals(s)) {
                    log.debug("EOF");
                    this.sink.complete();

                    break;
                }

                var body = gson.fromJson(s, this.type);

                this.sink.next(body);
            } catch (Exception ex) {
                log.error("{}", ex);
                this.sink.error(ex);
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        log.error("{}", t);
        this.sink.error(t);
    }
}
