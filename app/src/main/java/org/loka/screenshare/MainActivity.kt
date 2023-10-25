package org.loka.screenshare

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.apkfuns.logutils.LogUtils
import org.loka.screenshar.R
import org.loka.screensharekit.EncodeBuilder
import org.loka.screensharekit.ScreenShareKit
import org.loka.screensharekit.callback.AudioCallBack
import org.loka.screensharekit.callback.H264CallBack
import org.loka.screensharekit.callback.RGBACallBack
import java.nio.ByteBuffer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.start).setOnClickListener {
            requestCapture()
        }

        findViewById<Button>(R.id.stop).setOnClickListener {
            ScreenShareKit.stop()
        }
    }


    private fun requestCapture() {

        ScreenShareKit.init(this).onH264(object : H264CallBack {
            override fun onH264(
                buffer: ByteBuffer,
                isKeyFrame: Boolean,
                width: Int,
                height: Int,
                ts: Long
            ) {
                LogUtils.d("用户同意开始采集数据了ts：$ts")
                LogUtils.d("数据了ts：$buffer")

            }

        }).onStart({
            //用户同意采集，开始采集数据
            LogUtils.d("用户同意开始采集数据了")
        }).start()

    }
}