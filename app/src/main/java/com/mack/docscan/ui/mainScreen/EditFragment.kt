package com.mack.docscan.ui.mainScreen

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mack.docscan.Adapter.EditImageAdapter
import com.mack.docscan.R
import com.mack.docscan.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private var binding : FragmentEditBinding? = null
    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var imageAdapter: EditImageAdapter
    private val imageUris = mutableListOf<String>()
    private val args : EditFragmentArgs by navArgs()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater,container,false)

        imageRecyclerView = binding!!.imageRV
        imageAdapter = EditImageAdapter(imageUris)
        imageRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        imageRecyclerView.adapter = imageAdapter

        args.imageUri.let {
            if (args.replaceIndex >= 0) {
                imageUris[args.replaceIndex] = it
            } else {
                imageUris.add(it)
            }
            imageAdapter.notifyDataSetChanged()
        }
        binding?.btnEdit?.setOnClickListener {
        }
        binding?.btnRetake?.setOnClickListener {
            Log.d("retake",it.toString())
            val action = EditFragmentDirections.actionEditFragmentToCameraFragment(args.replaceIndex)
            findNavController().navigate(action)
        }
        binding?.btnScanMore?.setOnClickListener{
            Log.d("ScanMore",it.toString())
            val action = EditFragmentDirections.actionEditFragmentToCameraFragment()
            findNavController().navigate(action)

        }
        return binding?.root
    }
}