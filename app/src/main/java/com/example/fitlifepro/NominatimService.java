// NominatimService.java
package com.example.fitlifepro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NominatimService {
    @GET("search?format=json")
    Call<List<NominatimPlace>> search(
            @Query("q") String query,
            @Query("limit") int limit
    );
}
