import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.fe.dse.myapplication.api.ApiService
import kh.edu.rupp.fe.dse.myapplication.model.Profile
import kh.edu.rupp.fe.dse.myapplication.model.UiState
import kh.edu.rupp.fe.dse.myapplication.model.UiStateStatus
import perfetto.protos.UiState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileViewModel : ViewModel() {

    private val _profileUiState = MutableLiveData<Profile>()
    val profileState: LiveData<Profile> get() = _profileUiState

    fun getProfile() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getProfile().enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful) {
                    _profileUiState.value = UiState.success(response.body())
                } else {
                    Log.e("ProfileViewModel", "Load profile error: ${response.message()}")
                    _profileUiState.value = UiState.error(response.message())
                }
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.e("ProfileViewModel", "Failed to load profile", t)
                _profileUiState.value = UiState.error("Failed to load profile")
            }
        })
    }
}



//    private val _profileInformation = MutableLiveData<Profile?>() // Change MutableLiveData<List<Profile>> to MutableLiveData<Profile>
//    val profileInformation: LiveData<Profile> // LiveData to observe changes in profile information
//
//    fun loadProfile() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val apiService = retrofit.create(ApiService::class.java)
//
//        apiService.loadProfile().enqueue(object : Callback<Profile> {
//            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
//                if (response.isSuccessful) {
//                    val profile = response.body()
//                    if (profile != null) {
//                        _profileInformation.value = profile // Update LiveData with retrieved profile
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<Profile>, t: Throwable) {
//                // Handle failure
//            }
//        })
//    }
}


