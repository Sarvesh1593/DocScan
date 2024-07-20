package com.mack.docscan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mack.docscan.databinding.FragmentEditPageBinding


class EditPageFragment : Fragment() {

    private var binding : FragmentEditPageBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditPageBinding.inflate(layoutInflater,container,false)

        return binding?.root
    }
}