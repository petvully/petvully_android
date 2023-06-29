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
    private val FPS = 20
    private val SPEED = 2
    private val JUMP = 5
    private var movementJob: Job = CoroutineScope(Dispatchers.IO).launch {  }
    private var currentFloor = 0
    fun randomPosition(){

    }

    // position: (x,y)
    fun goToPosition(dog: View, position: Point){
        movementJob.cancel()
        movementJob = CoroutineScope(Dispatchers.Default).launch {
            var xStep = ((position.x - dog.x) / FPS).toInt()*SPEED
            var yStep = ((position.y - dog.y) / FPS).toInt()*SPEED


            while(hasNextMoving(Point(dog.x.toInt()+xStep,dog.y.toInt()+yStep))){
                if(hasNextJump(Point(dog.x.toInt()+(JUMP*xStep),dog.y.toInt()+(JUMP*yStep)))) {
                    Log.d("asd","JUMPJUMPJUMPJUMPJUMPJUMPJUMPJUMP")
                    jump(dog, Point(dog.x.toInt()+(JUMP*xStep),dog.y.toInt()+(JUMP*yStep)))
                    break
                }
                dog.x += xStep
                dog.y += yStep
                xStep = ((position.x - dog.x) / FPS).toInt()*SPEED
                yStep = ((position.y - dog.y) / FPS).toInt()*SPEED
                delay(FPS.toLong())
            }
        }
    }
    private fun hasNextMoving(point: Point):Boolean{
        moveArea.filter { it.available && (it.isInside(point)) }.maxOfOrNull { it.zIndex }?.let {
            currentFloor = it
            return true
        }
        return false
    }
    private fun hasNextJump(point:Point):Boolean{
        return moveArea.any {
            it.available && it.isInside(point) && it.zIndex != currentFloor
        }
    }

    suspend fun jump(dog: View, to: Point){
        delay(FPS*50L)
        val vector = Point((to.x - dog.x).toInt()*JUMP*10, (to.y - dog.y).toInt()*JUMP*10)
        var xStep = ((to.x - dog.x) / FPS).toInt()*SPEED
        var yStep = ((to.y - dog.y) / FPS).toInt()*SPEED

        while(to.x+vector.x!=dog.x.toInt() || to.y+vector.y != dog.y.toInt()){
            dog.x += xStep
            dog.y += yStep
            xStep = ((to.x - dog.x) / FPS*1.5).toInt()*SPEED
            yStep = ((to.y - dog.y) / FPS*1.5).toInt()*SPEED
            delay(FPS.toLong())

        }
    }
    suspend  fun down(dog: View, to: Point){

    }

    fun goFar(){

    }
}