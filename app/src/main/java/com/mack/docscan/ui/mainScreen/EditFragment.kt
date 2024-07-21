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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mack.docscan.Adapter.EditImageAdapter
import com.mack.docscan.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private var binding : FragmentEditBinding? = null
    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var imageAdapter: EditImageAdapter
    private val imageUris = mutableListOf<String>()
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

        arguments?.getString("imageUri")?.let {
            imageUris.add(it)
            imageAdapter.notifyDataSetChanged()
        }
        binding?.btnEdit?.setOnClickListener {
        }
        return binding?.root
    }
}