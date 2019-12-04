package in.rahultyagi.mytestapp.networkcalls;



import java.util.List;

import in.rahultyagi.mytestapp.models.Results;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyApi {
    @GET("/users")
   
    Call<List<Results>> getApiList();
}
