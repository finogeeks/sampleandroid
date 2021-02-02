package com.finogeeks.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.finogeeks.lib.applet.modules.webview.FinAppletWebView
import io.reactivex.Single
import java.util.HashMap
import java.util.concurrent.TimeUnit

/**
 * WebView页面
 */
class WebViewActivity : AppCompatActivity() {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var finAppletWebView: FinAppletWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        finAppletWebView = findViewById(R.id.finAppletWebView)
        swipeRefreshLayout.setOnRefreshListener {
            Single.timer(2000, TimeUnit.MILLISECONDS)
                .subscribe({
                    swipeRefreshLayout.isRefreshing = false
                }) { Log.e(TAG, " ${it.localizedMessage}") }
        }
        finAppletWebView.setOnWebViewScrollListener(object :
            FinAppletWebView.OnWebViewScrollListener {
            override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
                swipeRefreshLayout.isEnabled = t == 0
            }
        })


        finAppletWebView.loadUrl("https://open-test.fdep.cn")
    }

    override fun onBackPressed() {
        if (finAppletWebView.webViewGoBack()) {
            return
        }
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        finAppletWebView.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        private const val TAG = "WebViewActivity"
    }
}