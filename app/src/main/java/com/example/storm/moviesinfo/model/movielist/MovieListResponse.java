package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/27.
 */

public class MovieListResponse {
    String reason;
    int error_code;
    MovieListWrapper result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public MovieListWrapper getResult() {
        return result;
    }

    public void setResult(MovieListWrapper result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MovieListResponse{" +
                "reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }
}
