package com.example.internetchecker

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.example.internetchecker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Creating connection manager
        val connectionManager : ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected :Boolean = activeNetwork?.isConnectedOrConnecting == true

//        check the connection and display the information in the layout
        if (isConnected){
            binding.ImgStatus.setImageResource(R.drawable.ic_baseline_wifi_24)
            binding.Txtstatus.text = "You are connected to the internet"
        } else {
            binding.ImgStatus.setImageResource(R.drawable.ic_baseline_wifi_off_24)
            binding.Txtstatus.text = "You are connected to the internet"
        }

        // button event function

        binding.btnCheck.setOnClickListener {
            val activeNetwork : NetworkInfo? = connectionManager.activeNetworkInfo
            val isConnected :Boolean = activeNetwork?.isConnectedOrConnecting == true
            if (isConnected){
                binding.ImgStatus.setImageResource(R.drawable.ic_baseline_wifi_24)
                binding.Txtstatus.text = "You are connected to the internet"
            } else {
                binding.ImgStatus.setImageResource(R.drawable.ic_baseline_wifi_off_24)
                binding.Txtstatus.text = "You are connected to the internet"
            }
        }
        setTransparentStatusBar()
    }

    fun Activity.setTransparentStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = Color.TRANSPARENT
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {

        /*/*val mediaPlayer = MediaPlayer.create(this@MainActivity1, R.raw.sound)
        mediaPlayer.start()*/*/ // no need to call prepare(); create() does that for you
        val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibratorService.vibrate(150)

        AlertDialog.Builder(this@MainActivity)
            .setTitle("Exit App")
            .setMessage("Are you sure you want exit the app?")
            .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                super.onBackPressed()

            }
            .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

            }
            .show()

    }
}