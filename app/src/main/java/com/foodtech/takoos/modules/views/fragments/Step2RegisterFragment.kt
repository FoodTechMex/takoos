package com.foodtech.takoos.modules.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.foodtech.takoos.R
import com.foodtech.takoos.databinding.FragmentStep2RegisterBinding


class Step2RegisterFragment : Fragment() {

private lateinit var binding : FragmentStep2RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_step2_register, container, false)
        return binding.root
    }

}