package com.arul.jpmc.codingassessment.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arul.jpmc.codingassessment.model.HighSchool;
import com.arul.jpmc.codingassessment.network.GetDataService;
import com.arul.jpmc.codingassessment.network.RecyclerViewClickListener;
import com.arul.jpmc.codingassessment.R;
import com.arul.jpmc.codingassessment.adapter.SchoolsAdapter;
import com.arul.jpmc.codingassessment.model.SATScore;
import com.arul.jpmc.codingassessment.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private SchoolsAdapter schoolsAdapter;
    private RecyclerView schoolRecyclerView;
    private List<HighSchool> highSchoolList;
    private List<SATScore> satScoreList;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);
        highSchoolList = new ArrayList<>();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        //Get list of DOE high schools
        Call<List<HighSchool>> call = service.getSchools();
        call.enqueue(new Callback<List<HighSchool>>() {

            /**
             * On successful school response
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<List<HighSchool>> call, Response<List<HighSchool>> response) {
                progressDialog.dismiss();
                highSchoolList = response.body();
                generateDataList(highSchoolList);
            }

            /**
             * Handling when the API request fails
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<List<HighSchool>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        //Getting SAT scores
        progressDialog.show();
        Call<List<SATScore>> callSAT = service.getSATScores();
        callSAT.enqueue(new Callback<List<SATScore>>() {

            @Override
            public void onResponse(Call<List<SATScore>> call, Response<List<SATScore>> response) {
                progressDialog.dismiss();
                satScoreList = response.body();
                generateDataList(highSchoolList);
            }

            @Override
            public void onFailure(Call<List<SATScore>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    /**
     * Setting up the adapter and recyclerview for schools list
     *
     * @param highSchoolList List of high schools
     */
    private void generateDataList(List<HighSchool> highSchoolList) {
        schoolRecyclerView = findViewById(R.id.customRecyclerView);
        schoolsAdapter = new SchoolsAdapter(this, highSchoolList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        schoolRecyclerView.setLayoutManager(layoutManager);
        schoolRecyclerView.setAdapter(schoolsAdapter);
    }

    /**
     * Listener implementation to handle the click on a school to view SAT scores
     *
     * @param position Selected school index
     */
    @Override
    public void OnSchoolSelected(int position) {
        showSATScore(highSchoolList.get(position).getDbn());
    }

    /**
     * Lounch another screen to show the SAT score details for the selected school
     *
     * @param dbn key name to get the SAT scores for the corresponding school.
     */
    private void showSATScore(String dbn) {
        boolean satScoreFound = false;
        for (SATScore satScore : satScoreList) {
            if (satScoreFound = satScore.getDbn().equals(dbn)) {
                Intent intent = new Intent(this, SatScoreActivity.class);
                intent.putExtra("SAT_DATA", satScore);
                startActivity(intent);
                break;
            }
        }

        // Handling that scenario of some school does not has SAT scores
        if (!satScoreFound) {
            Intent intent = new Intent(this, SatScoreActivity.class);
            startActivity(intent);
        }
    }
}
