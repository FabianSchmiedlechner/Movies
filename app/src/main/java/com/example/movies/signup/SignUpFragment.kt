package com.example.movies.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() { //BaseFragment(R.layout.fragment_sign_up, SignUpViewModel()) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignUpBinding.inflate(inflater)

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
        }

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.vm = viewModel
//
//        viewModel.onAttached()
//
//        bindObservers()
//
//        binding.swipeContainer.setOnRefreshListener {
//            viewModel.refresh()
//            getSavingsBanner().refresh()
//        }
//        binding.verificationInfoView.setOnActionButtonClickListener {
//            verificationInfoViewModel.onActionClicked()
//        }
//    }

}