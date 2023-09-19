package com.example.swipeandcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.swipeandcalendar.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setupSwipeRefreshLayout()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    stateFlowSwipeEnabled.collect {
                        // Disable Pull to Refresh on OfflineMode
                        Log.e("Loading-Value - stateFlowSwipeEnabled", stateFlowSwipeEnabled.value.toString())
                        binding.swipeRefreshLayout.isEnabled = it
                    }
                }
            }
        }
        setContentView(view)
    }

    private fun setupSwipeRefreshLayout() {
        // Keep default start offset and increase distance to one-seventh of screen height
        val progressViewStartOffset = -binding.swipeRefreshLayout.progressCircleDiameter
        val progressViewEndOffset = resources.displayMetrics.heightPixels / 7

        binding.swipeRefreshLayout.setProgressViewOffset(
            false, progressViewStartOffset, progressViewEndOffset
        )
        // The default value is 64, double it then convert to dips to increase animation duration
        binding.swipeRefreshLayout.setDistanceToTriggerSync((128 * resources.displayMetrics.density).toInt())
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    companion object {
        val stateFlowSwipeEnabled = MutableStateFlow(true)
    }
}