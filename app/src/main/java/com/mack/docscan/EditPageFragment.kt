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

        // Track the URI state to avoid redundant updates
        var currentUri: Uri? = null

        // Observe imageSharedViewModel to handle the initial or default image
        imageSharedViewModel.imageUri.observe(viewLifecycleOwner) { uri ->
            if (currentUri == null) {  // Only update if no rotated URI is available
                currentUri = uri
                Log.d("EditPageFragment", "Default image URI: $uri")
                updateImageView(uri)
            }
        }

        // Observe rotateSharedViewModel to handle rotated images
        rotateSharedViewModel.uri.observe(viewLifecycleOwner) { uri ->
            currentUri = uri
            Log.d("EditPageFragment", "Rotated image URI: $uri")
            updateImageView(uri)
        }
    }

    // Helper function to update the ImageView
    private fun updateImageView(uri: Uri?) {
        binding?.IVPage?.apply {
            setImageURI(null)  // Clear existing image
            setImageURI(uri)   // Set new image
        }
    }

}