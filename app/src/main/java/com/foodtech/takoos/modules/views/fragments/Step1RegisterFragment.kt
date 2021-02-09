package com.foodtech.takoos.modules.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.foodtech.takoos.R
import com.foodtech.takoos.base.BaseFragment
import com.foodtech.takoos.databinding.FragmentStep1RegisterBinding
import com.foodtech.takoos.modules.viewmodel.RegisterViewModel

class Step1RegisterFragment : BaseFragment() {

    private var registerViewModel = RegisterViewModel()
    private lateinit var binding : FragmentStep1RegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step1_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configViewModel()
        observers()
        onclicks()
    }

    private fun observers() {
        registerViewModel.registerAuthSuccess.observe(viewLifecycleOwner, { registerSuccess ->
            if(registerSuccess){
                registerViewModel.registerInFireStore()
            }
        })
        registerViewModel.msgAuth.observe(viewLifecycleOwner, { msg->
            tooast(msg)
        })
    }

    private fun tooast(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun onclicks() {
        binding.bRegistrar.setOnClickListener {
            registerViewModel.registerWithEmailAndPassword("linkinluisave@gmail.com", "123Algo")
        }
    }

    private fun configViewModel() {
        binding.registerViewModel = registerViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


}