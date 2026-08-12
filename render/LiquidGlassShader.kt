package com.vivo.liquidglass.render

import android.graphics.RuntimeShader

class LiquidGlassShader {
    companion object {
        fun getShader(
            radius: Float,
            dispersion: Float,
            refract: Float
        ): RuntimeShader {
            val agslCode = """
                uniform shader src;
                uniform float2 size;
                uniform float blurRadius;
                uniform float dispersionStrength;
                uniform float refractPower;

                float sdRoundedBox(float2 p, float2 b, float4 r){
                    r.xy = (p.x>0.0)?r.xy : r.zw;
                    r.x  = (p.y>0.0)?r.x  : r.y;
                    float2 q = abs(p)-b+r.x;
                    return min(max(q.x,q.y),0.0)+length(max(q,0.0))-r.x;
                }

                half4 main(float2 coord){
                    float2 uv = coord/size;
                    float2 center = float2(0.5);
                    float2 offset = (uv-center)*refractPower;
                    float rOff = offset*dispersionStrength;
                    float gOff = offset*(dispersionStrength*0.55);
                    float bOff = offset*(dispersionStrength*0.25);

                    half3 col;
                    col.r = src.eval(coord+rOff*blurRadius).r;
                    col.g = src.eval(coord+gOff*blurRadius).g;
                    col.b = src.eval(coord+bOff*blurRadius).b;

                    float dist = sdRoundedBox(uv-center,float2(0.48),float4(0.18));
                    float edge = 1.0-smoothstep(-0.02,0.02,dist);
                    half3 highlight = half3(0.98,0.99,1.0)*pow(edge,2.2)*0.75;
                    half glassAlpha = 0.86-abs(dist)*0.35;
                    return half4(col+highlight,glassAlpha);
                }
            """.trimIndent()

            val shader = RuntimeShader(agslCode)
            shader.setFloatUniform("blurRadius", radius)
            shader.setFloatUniform("dispersionStrength", dispersion / 100f)
            shader.setFloatUniform("refractPower", refract / 100f)
            return shader
        }
    }
}
