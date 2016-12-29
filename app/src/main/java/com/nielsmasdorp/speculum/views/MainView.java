package com.nielsmasdorp.speculum.views;

import com.nielsmasdorp.speculum.models.RedditPost;
import com.nielsmasdorp.speculum.models.Weather;
import com.nielsmasdorp.speculum.models.QuotePost;

/**
 * @author Niels Masdorp (NielsMasdorp)
 */
public interface MainView extends BaseView {

	void showListening();

	void hideListening();

	void showMap(String location);

	void hideMap();

	void displayCurrentWeather(Weather weather, boolean isSimpleLayout);

	void displayCalendarEvents(String events);

	void displayQuotePost(QuotePost quotePost);

	void displayTopRedditPost(RedditPost redditPost);
}
