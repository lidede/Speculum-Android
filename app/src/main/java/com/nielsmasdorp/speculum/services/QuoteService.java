package com.nielsmasdorp.speculum.services;
import com.nielsmasdorp.speculum.models.QuotePost;
import com.nielsmasdorp.speculum.models.quote.Data_;
import com.nielsmasdorp.speculum.models.quote.QuoteResponse;
import com.nielsmasdorp.speculum.util.Constants;


import java.text.DecimalFormat;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public class QuoteService {

    private static final String QUOTE_UPS_THOUSAND_SUFFIX = "k";
    private static final String QUOTE_UPS_UNKNOWN = "-";

    private com.nielsmasdorp.speculum.services.QuoteService.QuoteApi quoteApi;

    public QuoteService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.QUOTE_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quoteApi = retrofit.create(com.nielsmasdorp.speculum.services.QuoteService.QuoteApi.class);
    }

    public Observable<QuotePost> getQuotePost(QuoteResponse response) {

        Random r = new Random();
        int randomNumber = r.nextInt(Constants.QUOTE_LIMIT);
        Data_ postData = response.getData().getChildren().get(randomNumber).getData();

        return Observable.just(new QuotePost(postData.getTitle(), postData.getAuthor(),
                mapQuoteUps(postData.getUps())));
    }

    private String mapQuoteUps(Integer ups) {
        if (ups == null) {
            return QUOTE_UPS_UNKNOWN;
        }
        if ((ups / 1000) > 0) {
            DecimalFormat df = new DecimalFormat("#.#");
            return df.format((double) ups / 1000) + QUOTE_UPS_THOUSAND_SUFFIX;
        } else {
            return ups.toString();
        }
    }

    public com.nielsmasdorp.speculum.services.QuoteService.QuoteApi getApi() {

        return quoteApi;
    }

    public interface QuoteApi {

        @GET("{quote}/top.json")
        Observable<QuoteResponse> getQuotePostForQuote(@Path("quote") String quote, @Query("limit") int limit);
    }
}
