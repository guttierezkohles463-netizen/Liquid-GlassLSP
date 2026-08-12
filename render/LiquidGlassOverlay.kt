package com.vivo.liquidglass.render

import android.content.Context
import android.graphics.*
import android.view.View

class LiquidGlassOverlay(context: Context, private val shader: RuntimeShader) : View(context) {
    private val drawPaint = Paint().apply {
        isAntiAlias = true
        shaderFactory = ShaderFactory.fromRuntimeShader(shader)
    }

    override fun onDraw(canvas: Canvas) {
        shader.setFloatUniform("size", width.toFloat(), height.toFloat())
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), drawPaint)
    }
}
