package org.e1i4.petvully.util.helper.home

import android.graphics.Point
import android.graphics.Rect
import android.util.Log
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.e1i4.petvully.data.local.model.HomeArea

class Movement(private val moveArea: List<HomeArea>) {
    private val FPS = 200
    private val SPEED = 5
    var movementJob: Job = CoroutineScope(Dispatchers.IO).launch {  }
    fun randomPosition(){

    }

    // position: (x,y)
    fun goToPosition(dog: View, position: Point){
        movementJob.cancel()
        movementJob = CoroutineScope(Dispatchers.Default).launch {
            var xStep = ((position.x - dog.x) / SPEED).toInt()
            var yStep = ((position.y - dog.y) / SPEED).toInt()
            while(moveArea.all { it.available &&(it.isInside(Point(xStep,yStep))) }){
                dog.x += xStep
                dog.y += yStep
                xStep = ((position.x - dog.x) / SPEED).toInt()
                yStep = ((position.y - dog.y) / SPEED).toInt()
                Log.d("asd", "x $xStep  y $yStep")
                delay(FPS.toLong())
            }
        }
    }

    fun goFar(){

    }
}