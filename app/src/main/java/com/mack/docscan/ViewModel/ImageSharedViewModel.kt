package com.mack.docscan.ViewModel


import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageSharedViewModel : ViewModel() {
    private val _imageUris = MutableLiveData<MutableList<Uri>>(mutableListOf())
    val imageUris: LiveData<MutableList<Uri>> get() = _imageUris

    private val _currentIndex = MutableLiveData<Int>(0)
    val currentIndex: LiveData<Int> get() = _currentIndex

    fun addImage(uri: Uri) {
        _imageUris.value?.add(uri)
        _imageUris.postValue(_imageUris.value)
    }

    fun replaceImage(uri: Uri, index: Int) {
        _imageUris.value?.set(index, uri)
        _imageUris.value = _imageUris.value
    }

    fun removeImage(index: Int) {
        _imageUris.value?.removeAt(index)
        _imageUris.value = _imageUris.value
    }

    fun setCurrentIndex(index: Int) {
        _currentIndex.value = index
    }
    fun resetIndex() {
        _currentIndex.value = 0
    }

    fun clearImageUris() {
        _imageUris.value?.clear()
        _imageUris.postValue(_imageUris.value)
        resetIndex()
    }
}
