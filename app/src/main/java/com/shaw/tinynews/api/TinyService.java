package com.shaw.tinynews.api;

import com.shaw.tinynews.model.main.Latest;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public interface TinyService {
	@GET("news/latest")
	Observable<Latest> latest();

}
