import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.fe.dse.myapplication.databinding.ActivityMainBinding
import kh.edu.rupp.fe.dse.myapplication.model.UiStateStatus
import kh.edu.rupp.fe.dse.myapplication.viewmodel.ProfileViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Observe changes in profile information
        profileViewModel.profileState.observe(this, Observer { uiState ->
            when (uiState.status) {
                UiStateStatus.SUCCESS -> {
                    val profile = uiState.data
                    profile?.let {
                        // Update UI with profile data
                        binding.userName.text = it.firstName
                        // Similarly, update other views with respective data from the profile object
                    }
                }
                UiStateStatus.ERROR -> {
                    val errorMessage = uiState.message ?: "Unknown error"
                    // Handle error, maybe display a toast or update UI accordingly
                    Log.e("MainActivity", "Error: $errorMessage")
                }
                UiStateStatus.LOADING -> {
                    // Maybe show a loading spinner or progress bar
                }
            }
        })

        // Load profile when the activity is created
        profileViewModel.getProfile()
    }
}


