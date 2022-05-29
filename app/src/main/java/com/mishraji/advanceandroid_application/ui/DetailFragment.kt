package com.mishraji.advanceandroid_application.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mishraji.advanceandroid_application.data.User
import com.mishraji.advanceandroid_application.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var singleData: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivity()?.setTitle("Trending");
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        viewModel.user.observe(requireActivity()) { result ->
            Glide.with(this)
                .load(singleData.owner?.avatarUrl)
                .into(binding.avatarImg)
            binding.textViewNo.text = singleData.name
            binding.textViewName.text = singleData.fullName
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(data: User) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    singleData = data
                }
            }

    }
}