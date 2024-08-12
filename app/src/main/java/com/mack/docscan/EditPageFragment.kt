package com.mack.docscan

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.mack.docscan.ViewModel.ImageSharedViewModel
import com.mack.docscan.ViewModel.RotateSharedViewModel
import com.mack.docscan.databinding.FragmentEditPageBinding


class EditPageFragment : Fragment() {

    private var binding : FragmentEditPageBinding? = null
    private lateinit var rotateSharedViewModel: RotateSharedViewModel
    private lateinit var imageSharedViewModel: ImageSharedViewModel
    private lateinit var imageUri : Uri
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditPageBinding.inflate(layoutInflater,container,false)

        binding?.tvRotate?.setOnClickListener {
            imageUri = imageSharedViewModel.imageUri.value!!
            rotateSharedViewModel.setUri(imageUri)
            findNavController().navigate(EditPageFragmentDirections.actionEditPageFragmentToRotateFragment())
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageSharedViewModel = ViewModelProvider(requireActivity())[ImageSharedViewModel::class.java]

        rotateSharedViewModel = ViewModelProvider(requireActivity())[RotateSharedViewModel::class.java]

        rotateSharedViewModel.uri.observe(viewLifecycleOwner){ uri ->
            binding?.IVPage?.setImageURI(uri)
        }
        imageSharedViewModel.imageUri.observe(viewLifecycleOwner){ uri ->
            binding?.IVPage?.setImageURI(uri)

        }

    }
}