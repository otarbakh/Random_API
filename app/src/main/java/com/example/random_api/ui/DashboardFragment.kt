package com.example.random_api.ui


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.random_api.common.BaseFragment
import com.example.random_api.common.Resource
import com.example.random_api.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private val vm: DashboardViewModel by viewModels()

    override fun viewCreated() {
        observe()
    }

    override fun listeners() {
        TODO("Not yet implemented")
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            binding.tvRandom.text = it.data.contents.fact
                        }
                    }
                }
            }
        }
    }
}