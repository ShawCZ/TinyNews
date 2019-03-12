package com.shaw.tinynews.api;

import com.shaw.tinynews.model.detail.Detail;
import com.shaw.tinynews.model.main.Latest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public interface TinyService {
	@GET("news/latest")
	Observable<Latest> latest();

	@GET("news/before/{date}")
	Observable<Latest> before(@Path("date") String date);

	@GET("news/{id}")
	Observable<Detail> detail(@Path("id") long date);

}
