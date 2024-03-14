package kh.edu.rupp.fe.dse.myapplication.api;
import kh.edu.rupp.fe.dse.myapplication.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("fb-profile.json")
    Call<Profile> getProfile();
}

