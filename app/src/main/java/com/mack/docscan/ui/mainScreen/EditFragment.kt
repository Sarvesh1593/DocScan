package com.mack.docscan.ui.mainScreen

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.mack.docscan.Adapter.DocumentPagerAdapter
import com.mack.docscan.ViewModel.ImageSharedViewModel
import com.mack.docscan.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private var binding : FragmentEditBinding? = null
    private val imageUris = mutableListOf<Uri>()
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: DocumentPagerAdapter
    private lateinit var imageSharedViewModel: ImageSharedViewModel
    private var currentPage: Int = 0
    private val args : EditFragmentArgs by navArgs()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater,container,false)
        viewPager = binding!!.imageViewPager


        binding?.btnEdit?.setOnClickListener {
        }
        // This button do for retaking the photo and replacing the existing photo

        return binding?.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageSharedViewModel = ViewModelProvider(requireActivity())[ImageSharedViewModel::class.java]
        setUpPager()

        imageSharedViewModel.imageUris.observe(viewLifecycleOwner) { uri ->
            adapter.updateData(uri)
        }

        imageSharedViewModel.currentIndex.observe(viewLifecycleOwner) { index ->
            viewPager.currentItem = index
        }
//        binding?.btnRetake?.setOnClickListener {
//            Log.d("retake",it.toString())
//            currentPage = viewPager.currentItem
//            imageSharedViewModel.setCurrentIndex(currentPage)
//            findNavController().navigate(EditFragmentDirections.actionEditFragmentToCameraFragment())
//        }
        binding?.btnScanMore?.setOnClickListener{
            Log.d("ScanMore",it.toString())
            imageSharedViewModel.setCurrentIndex(-1)
            findNavController().navigate(EditFragmentDirections.actionEditFragmentToCameraFragment())
        }

    }

    private fun setUpPager(){
        adapter = DocumentPagerAdapter(imageSharedViewModel.imageUris.value ?: mutableListOf())
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                imageSharedViewModel.setCurrentIndex(position)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Glide.get(requireActivity()).clearMemory()
    }
}


