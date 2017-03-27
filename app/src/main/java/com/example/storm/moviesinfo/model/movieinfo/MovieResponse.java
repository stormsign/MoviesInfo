package com.example.storm.moviesinfo.model.movieinfo;

/**
 * Created by khb on 2017/3/14.
 */
public class MovieResponse  {
    String reason;
    Movie result;
    int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Movie getResult() {
        return result;
    }

    public void setResult(Movie result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
