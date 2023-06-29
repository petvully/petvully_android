package org.e1i4.petvully.data.local.model

import android.graphics.Point
import android.graphics.Rect

data class HomeArea (
    val available:Boolean,
    val zIndex:Int,
    val points:List<Point>,
){
    fun isInside(B: Point): Boolean {
        var crosses = 0
        for (i in points.indices) {
            val j = (i + 1) % points.size
            if ((points[i].y > B.y) != (points[j].y > B.y)) {
                val atX = (points[j].x - points[i].x) * (B.y - points[i].y) / (points[j].y - points[i].y) + points[i].x
                if (B.x < atX)
                    crosses++
            }
        }
        return crosses % 2 > 0
    }

}