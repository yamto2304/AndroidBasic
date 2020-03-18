package com.example.testactivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.TypefaceCompatUtil.closeQuietly
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL


class IOUtils {
    fun closeQuietly(ab: InputStream) {
        try {
            ab.close()
        } catch (e: Exception) {
        }
    }

    fun closeQuietly(reader: Reader) {
        try {
            reader.close()
        } catch (e: Exception) {
        }
    }
}


// Một nhiệm vụ với tham số đầu vào String, và kết quả trả về là String.
public class DownloadJsonTask(private val textView: TextView) :
    AsyncTask<String?, Void?, String?>() {
    @SuppressLint("RestrictedApi")
    override fun doInBackground(vararg params: String?): String? {
        val textUrl = params[0]
        var ab: InputStream? = null
        var br: BufferedReader? = null
        try {
            val url = URL("https://lh3.googleusercontent.com/proxy/9NGta1mTr5IHtFbqql7UHP__2pBqvUsvnM-fpkRFSJgEC15ucoNFKrALlhHYeQYpv38n4qJ_CV5QxsW-X6jHS2qH6tsp6tKsJiP29svYYp1HEgD62tWxtTZHPH_X5qwDGj1TcG8RcVM_LzhDQdmHPIvuHBPPDCmxvNuI6a3_76LqtWdUqZ_2dWRwChM")
            val httpConn =
                url.openConnection() as HttpURLConnection
            httpConn.allowUserInteraction = false
            httpConn.instanceFollowRedirects = true
            httpConn.requestMethod = "GET"
            httpConn.connect()
            val resCode = httpConn.responseCode
            return if (resCode == HttpURLConnection.HTTP_OK) {
                ab = httpConn.inputStream
                br = BufferedReader(InputStreamReader(ab))
                val sb = StringBuilder()
                var s: String? = null
                while (br.readLine().also { s = it } != null) {
                    sb.append(s)
                    sb.append("\n")
                }
                sb.toString()
            } else {
                null
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            closeQuietly(ab)
            closeQuietly(br)
        }
        return null
    }

    // Khi nhiệm vụ hoàn thành, phương thức này sẽ được gọi.
    // Download thành công, update kết quả lên giao diện.
    override fun onPostExecute(result: String?) {
        if (result != null) {
            textView.text = result
        } else {
            Log.e("MyMessage", "Failed to fetch data!")
        }
    }

}


// Một nhiệm vụ với tham số đầu vào String, và kết quả trả về là Bitmap.
public class DownloadImageTask(private val imageView: ImageView) :
    AsyncTask<String?, Void?, Bitmap?>() {
    @SuppressLint("RestrictedApi")
    override fun doInBackground(vararg params: String?): Bitmap? {
        val imageUrl = params[0]
        var ab: InputStream? = null
        try {
            val url = URL("https://lh3.googleusercontent.com/proxy/9NGta1mTr5IHtFbqql7UHP__2pBqvUsvnM-fpkRFSJgEC15ucoNFKrALlhHYeQYpv38n4qJ_CV5QxsW-X6jHS2qH6tsp6tKsJiP29svYYp1HEgD62tWxtTZHPH_X5qwDGj1TcG8RcVM_LzhDQdmHPIvuHBPPDCmxvNuI6a3_76LqtWdUqZ_2dWRwChM")
            val httpConn =
                url.openConnection() as HttpURLConnection
            httpConn.allowUserInteraction = false
            httpConn.instanceFollowRedirects = true
            httpConn.requestMethod = "GET"
            httpConn.connect()
            val resCode = httpConn.responseCode
            ab = if (resCode == HttpURLConnection.HTTP_OK) {
                httpConn.inputStream
            } else {
                return null
            }
            return BitmapFactory.decodeStream(ab)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            closeQuietly(ab)
        }
        return null
    }

    // Khi nhiệm vụ hoàn thành, phương thức này sẽ được gọi.
    // Download thành công, update kết quả lên giao diện.
    override fun onPostExecute(result: Bitmap?) {
        if (result != null) {
            imageView.setImageBitmap(result)
        } else {
            Log.e("MyMessage", "Failed to fetch data!")
        }
    }
}


class MainActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView =
            findViewById<View>(R.id.imageView) as ImageView
        textView = findViewById<View>(R.id.textView) as TextView
    }

    private fun checkInternetConnection(): Boolean {
        // Lấy ra bộ quản lý kết nối.
        val connManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Thông tin mạng đang kích hoạt.
        val networkInfo = connManager.activeNetworkInfo
        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show()
            return false
        }
        if (!networkInfo.isConnected) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show()
            return false
        }
        if (!networkInfo.isAvailable) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show()
            return false
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show()
        return true
    }

    // Khi người dùng click vào nút "Download Image".
    fun downloadAndShowImage(view: View?) {
        val networkOK = checkInternetConnection()
        if (!networkOK) {
            return
        }
        val imageUrl = "//o7planning.org/download/static/default/demo-data/logo.png"


        // Tạo một đối tượng làm nhiệm vụ download image.
        val task = DownloadImageTask(imageView!!)

        // Thực thi nhiệm vụ (Truyền vào URL).
        task.execute(imageUrl)
    }

    // Khi người dùng click vào nút "Download Json".
    fun downloadAndShowJson(view: View?) {
        val networkOK = checkInternetConnection()
        if (!networkOK) {
            return
        }
        val jsonUrl =
            "//o7planning.org/download/static/default/demo-data/company.json"

        // Tạo một đối tượng làm nhiệm vụ download nội dung json từ URL.
        val task = DownloadJsonTask(textView!!)


        // Thực thi nhiệm vụ (Truyền vào URL).
        task.execute(jsonUrl)
    }
}