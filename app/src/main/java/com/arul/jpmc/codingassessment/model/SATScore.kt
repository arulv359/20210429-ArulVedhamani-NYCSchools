package com.arul.jpmc.codingassessment.model

import android.os.Parcel
import android.os.Parcelable

class SATScore : Parcelable {
    var dbn: String
    var school_name: String
    var num_of_sat_test_takers: String
    var sat_critical_reading_avg_score: String
    var sat_math_avg_score: String
    var sat_writing_avg_score: String

    constructor(dbn: String, school_name: String, num_of_sat_test_takers: String, sat_critical_reading_avg_score: String, sat_math_avg_score: String, sat_writing_avg_score: String) {
        this.dbn = dbn
        this.school_name = school_name
        this.num_of_sat_test_takers = num_of_sat_test_takers
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score
        this.sat_math_avg_score = sat_math_avg_score
        this.sat_writing_avg_score = sat_writing_avg_score
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(dbn)
        dest.writeString(school_name)
        dest.writeString(num_of_sat_test_takers)
        dest.writeString(sat_critical_reading_avg_score)
        dest.writeString(sat_math_avg_score)
        dest.writeString(sat_writing_avg_score)
    }

    protected constructor(`in`: Parcel) {
        dbn = `in`.readString().toString()
        school_name = `in`.readString().toString()
        num_of_sat_test_takers = `in`.readString().toString()
        sat_critical_reading_avg_score = `in`.readString().toString()
        sat_math_avg_score = `in`.readString().toString()
        sat_writing_avg_score = `in`.readString().toString()
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<SATScore> {
            override fun createFromParcel(parcel: Parcel) = SATScore(parcel)
            override fun newArray(size: Int) = arrayOfNulls<SATScore>(size)
        }
    }
}