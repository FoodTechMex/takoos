package com.foodtech.takoos.modules.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.foodtech.takoos.R
import com.foodtech.takoos.base.BaseFragment
import com.foodtech.takoos.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment() {


    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        onclicks()
        return binding.root
    }

    private fun onclicks() {
        binding.btnRegister.setOnClickListener {v->
            v.findNavController().navigate(R.id.action_LoginFragment_to_registerFragment)
        }
    }


}