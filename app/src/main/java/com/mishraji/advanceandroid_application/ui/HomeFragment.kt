package com.mishraji.advanceandroid_application.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishraji.advanceandroid_application.callback.IOnRecyclerItemClickListener
import com.mishraji.advanceandroid_application.data.User
import com.mishraji.advanceandroid_application.databinding.FragmentHomeBinding
import com.mishraji.advanceandroid_application.util.Resource
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), IOnRecyclerItemClickListener {
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        val userAdapter = UserAdapter(this)
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = userAdapter
            }
            viewModel.user.observe(requireActivity()) { result ->
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
                retryButton.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                userAdapter.setData(result.data as ArrayList<User>)

                retryButton.setOnClickListener {
                    viewModel.viewModelScope.launch {
                    }
                }
            }
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    override fun onItemClick(data: Any) {
        if (data is User) {
            val mActivity = requireActivity() as UserActivity
            mActivity.setFragment(DetailFragment.newInstance(data), "Detail")
        }
    }

    override fun onResume() {
        super.onResume()
        getActivity()?.setTitle("Repository");
    }
}