package com.arul.jpmc.codingassessment.network;

import com.arul.jpmc.codingassessment.model.HighSchool;
import com.arul.jpmc.codingassessment.model.SATScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("s3k6-pzi2.json")
    Call<List<HighSchool>> getSchools();

    @GET("f9bf-2cp4.json")
    Call<List<SATScore>> getSATScores();
}
