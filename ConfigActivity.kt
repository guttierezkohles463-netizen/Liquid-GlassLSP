package com.vivo.liquidglass

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.vivo.liquidglass.config.ConfigManager
import com.vivo.liquidglass.config.GlassConfig

class ConfigActivity : AppCompatActivity() {
    private lateinit var cfg: GlassConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        cfg = ConfigManager.getConfig(this)

        // 模糊半径
        val sbBlur: SeekBar = findViewById(R.id.sb_blur)
        sbBlur.progress = cfg.blurRadius.toInt()
        sbBlur.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, p: Int, fromUser: Boolean) {
                cfg.blurRadius = p.toFloat()
                ConfigManager.save(this@ConfigActivity, cfg)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        // 色散强度
        val sbDisp: SeekBar = findViewById(R.id.sb_dispersion)
        sbDisp.progress = cfg.dispersion.toInt()
        sbDisp.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, p: Int, fromUser: Boolean) {
                cfg.dispersion = p.toFloat()
                ConfigManager.save(this@ConfigActivity, cfg)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        // 折射强度
        val sbRefract: SeekBar = findViewById(R.id.sb_refract)
        sbRefract.progress = cfg.refract.toInt()
        sbRefract.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, p: Int, fromUser: Boolean) {
                cfg.refract = p.toFloat()
                ConfigManager.save(this@ConfigActivity, cfg)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        // 功能总开关
        val swSysUi: Switch = findViewById(R.id.sw_sysui)
        swSysUi.isChecked = cfg.enableSystemUI
        swSysUi.setOnCheckedChangeListener { _, b ->
            cfg.enableSystemUI = b
            ConfigManager.save(this, cfg)
        }

        val swLauncher: Switch = findViewById(R.id.sw_launcher)
        swLauncher.isChecked = cfg.enableLauncher
        swLauncher.setOnCheckedChangeListener { _, b ->
            cfg.enableLauncher = b
            ConfigManager.save(this, cfg)
        }

        val swSetting: Switch = findViewById(R.id.sw_settings)
        swSetting.isChecked = cfg.enableSettings
        swSetting.setOnCheckedChangeListener { _, b ->
            cfg.enableSettings = b
            ConfigManager.save(this, cfg)
        }
    }
}
