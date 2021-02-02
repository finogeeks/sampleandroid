package com.finogeeks.sample

import android.app.Application
import android.os.AsyncTask
import android.view.Gravity
import android.widget.Toast
import com.finogeeks.lib.applet.client.FinAppClient
import com.finogeeks.lib.applet.client.FinAppConfig
import com.finogeeks.lib.applet.client.FinAppConfig.UIConfig
import com.finogeeks.lib.applet.interfaces.FinCallback
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import org.json.JSONObject

/**
 * @since 1.0.0
 * @date 12/30/20
 * @author cenxiaozhong
 */
class App : Application() {

    val TAG = App::class.java.simpleName

    override fun onCreate() {
        super.onCreate()

//
//        AsyncTask.SERIAL_EXECUTOR.execute {
//
//
//            FrameworkSyncManager.unzipAssetsFramework(applicationContext) { result ->
//                if (result) {
//
//                }else{
//                    Toast.makeText(this@App, "初始化失败", Toast.LENGTH_LONG).show()
//                }
//            }
//
//        }


        val uiConfig = UIConfig()
        uiConfig.isHideNavigationBarCloseButton = true
        val config = FinAppConfig.Builder()
            .setSdkFingerprint(BuildConfig.SDK_FINGERPRINT)
            .setSdkKey(BuildConfig.APP_KEY)
            .setSdkSecret(BuildConfig.APP_SECRET)
            .setApiUrl(BuildConfig.API_URL)
            .setApiPrefix(BuildConfig.API_PREFIX)
            .setDebugMode(BuildConfig.DEBUG)
            .setUiConfig(uiConfig)
            .setEncryptionType(FinAppConfig.ENCRYPTION_TYPE_SM)
            .build()
        FinAppClient.init(this, config, object : FinCallback<Any?> {
            override fun onSuccess(result: Any?) {
                Toast.makeText(this@App, "初始化完成", Toast.LENGTH_LONG).show()

            }

            override fun onError(code: Int, error: String) {
                this@App.runOnUiThread { this@App.toast("SDK初始化失败") }
            }

            override fun onProgress(status: Int, error: String) {}
        })
    }

}