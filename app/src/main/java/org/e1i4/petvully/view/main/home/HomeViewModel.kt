package org.e1i4.petvully.view.main.home

import android.graphics.Point
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.e1i4.petvully.data.local.model.HomeArea
import org.e1i4.petvully.util.helper.home.Movement
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private lateinit var homeAreas:List<HomeArea>
    private lateinit var movement: Movement
    fun setHomeArea(_homeAreas: List<HomeArea>){
        homeAreas = _homeAreas
        movement = Movement(_homeAreas)
    }
    fun goTo(dog: View, event: MotionEvent){
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                movement.goToPosition(dog, Point(event.x.toInt(),event.y.toInt()))
            }
        }
    }
}