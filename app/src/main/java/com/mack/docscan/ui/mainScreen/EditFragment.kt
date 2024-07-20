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
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.mack.docscan.Adapter.EditImageAdapter
import com.mack.docscan.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private var binding : FragmentEditBinding? = null
    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var imageAdapter: EditImageAdapter
    private val imageUris = mutableListOf<String>()
    @SuppressLint("NotifyDataSetChanged")
    private val scanLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if(result.resultCode == RESULT_OK){
            val scanningResult = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            scanningResult?.pages.let { pages ->
                if(pages != null){
                    for(page in pages){
                        val imageUri = page.imageUri
                        imageUris.add(imageUri.toString())
                    }
                    imageAdapter.notifyDataSetChanged()
                }
            }
            scanningResult?.pdf.let { pdfPage ->
                val pdfUri = pdfPage?.uri
                val pageCount = pdfPage?.pageCount
            }
        }
    }
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
            initiateDocumentScan()
        }

        return binding?.root
    }

    private fun initiateDocumentScan(){
        val options = GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(false)
            .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,GmsDocumentScannerOptions.RESULT_FORMAT_PDF)
            .build()
    }

}