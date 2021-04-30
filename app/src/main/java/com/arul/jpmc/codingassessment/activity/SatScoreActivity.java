package com.arul.jpmc.codingassessment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.arul.jpmc.codingassessment.model.SATScore;
import com.arul.jpmc.codingassessment.R;

public class SatScoreActivity extends AppCompatActivity {

    private SATScore satScoreScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satscore);
        Bundle data = getIntent().getExtras();

        if (data != null && (SATScore) data.getParcelable("SAT_DATA") != null) {
            (findViewById(R.id.no_record_found)).setVisibility(View.GONE);
            (findViewById(R.id.record_found)).setVisibility(View.VISIBLE);

            satScoreScores = (SATScore) data.getParcelable("SAT_DATA");
            ((TextView) findViewById(R.id.school_name)).setText(satScoreScores.getSchool_name());
            ((TextView) findViewById(R.id.num_test_takers)).setText("Number of Test takers : " + satScoreScores.getNum_of_sat_test_takers());
            ((TextView) findViewById(R.id.reading_avg_score)).setText("Critical thinking average score : " + satScoreScores.getSat_critical_reading_avg_score());
            ((TextView) findViewById(R.id.math_avg_score)).setText("Math average score : " + satScoreScores.getSat_math_avg_score());
            ((TextView) findViewById(R.id.writing_avg_score)).setText("Reading average score : " + satScoreScores.getSat_writing_avg_score());
        } else {
            (findViewById(R.id.no_record_found)).setVisibility(View.VISIBLE);
            (findViewById(R.id.record_found)).setVisibility(View.GONE);
        }
    }
}
