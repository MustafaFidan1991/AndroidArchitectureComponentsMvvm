package com.detoksdiyetleri.ui.home

import android.app.PendingIntent.getActivities
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.detoksdiyetleri.base.BaseViewModel
import com.detoksdiyetleri.data.repository.DietRepository
import com.detoksdiyetleri.model.Diet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class HomeViewModel : BaseViewModel() {

    var pageNumber = 1

    var latestKey = ""

    @Inject
    lateinit var dietRepository: DietRepository

    private var liveDietList: MutableLiveData<MutableList<Diet>>?=null

    fun increasePageNumber() {
        pageNumber += 1
    }


    fun loadDiets() : LiveData<MutableList<Diet>>?{
        swipeLoadingStatus.value = true
        if(liveDietList==null){
            liveDietList = MutableLiveData()
            liveDietList?.value = mutableListOf()
        }
        dietRepository.getDietsByPage(pageNumber,latestKey).addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                val tempList = mutableListOf<Diet>()
                for(snapShot in p0.children){
                    val diet = snapShot.getValue(Diet::class.java)
                    if(diet!=null)tempList.add(diet)
                }
                clearDataSource()

                if(tempList.size>0){
                    if(tempList[tempList.size-1].id == latestKey) {
                        tempList.removeAt(tempList.size-1)
                    }
                    liveDietList?.value?.addAll(tempList)
                    liveDietList?.value = liveDietList?.value
                    setLatestKey(tempList)
                }
                swipeLoadingStatus.value = false
            }
        })
        return liveDietList
    }

    fun setLatestKey(list : MutableList<Diet>){
        if(list.size>0){
            latestKey = list[list.size-1].id
        }
    }


    fun clearDataSource(){
        if(pageNumber == 1){
            liveDietList?.value?.clear()
        }
    }


    override fun onRefresh() {
        latestKey = ""
        pageNumber = 1
        loadDiets()
    }




}