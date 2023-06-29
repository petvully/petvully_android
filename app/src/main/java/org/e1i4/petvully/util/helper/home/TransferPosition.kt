package org.e1i4.petvully.util.helper.home

import android.graphics.Point
import android.util.Log

class TransferPosition(private val imageSize: Point, private val screenSize: Point) {

    fun toViewPoint(point: Point): Point{
        // 이미지의 가로 및 세로 스케일 팩터 계산
        val scaleX = imageSize.x / screenSize.x.toFloat()
        val scaleY = imageSize.y / screenSize.y.toFloat()
        // 좌표를 View의 좌표계로 변환
        return Point((point.x / scaleX).toInt(), (point.y / scaleY).toInt())
    }
}