package com.finogeeks.sample

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.finogeeks.lib.applet.BuildConfig
import com.finogeeks.lib.applet.client.FinAppClient.appletApiManager
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.findViewById<Button>(R.id.btn_demo).setOnClickListener {
            appletApiManager.startApplet(this@MainActivity, "5fb266f21a86780001ddf052")
        }

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),2000)
        }

        this.findViewById<TextView>(R.id.finVersion).text="SDK版本号：${BuildConfig.VERSION_NAME}"
    }
}