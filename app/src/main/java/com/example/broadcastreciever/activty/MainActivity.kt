package com.example.broadcastreciever.activty

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.broadcastreciever.BroadcastReciever.NBroadcastReceiver
import com.example.broadcastreciever.R


/**

1 - detect charging state
2 - detect plugin state USB  +
3- sending brc to other apps

 */

class MainActivity : AppCompatActivity() {
    lateinit var receiver: NBroadcastReceiver
    val isPersistent: Boolean = true
    val isInternal: Boolean = false
    var readPermissionGranted = false
    var writePermissionGranted = false
    var permissionsToRequest = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        requestPermisions()
    }

    private fun initViews() {
        receiver = NBroadcastReceiver()
//        receiver = NBroadcastReceiver(object : NBroadcastReceiver.MyListener {
//            override fun onclick(messages: Array<SmsMessage?>) {
//                messages.toList()
//            }
//
//        })
//
  }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(TelephonyManager.EXTRA_STATE_RINGING)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private fun requestPermisions(){
        val hasReadPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED
        val hasWritePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS
        ) == PackageManager.PERMISSION_GRANTED
        val minSdk29 = Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q
        readPermissionGranted = hasReadPermission
        writePermissionGranted = hasWritePermission || minSdk29

        if (!readPermissionGranted){
            permissionsToRequest.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (!writePermissionGranted){
            permissionsToRequest.add(Manifest.permission.RECEIVE_SMS)
        }
        if (permissionsToRequest.isNotEmpty()){
            permissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permissions ->
        readPermissionGranted = permissions[Manifest.permission.READ_PHONE_STATE] ?: readPermissionGranted
        writePermissionGranted = permissions[Manifest.permission.RECEIVE_SMS] ?: writePermissionGranted

        if (readPermissionGranted) Toast.makeText(this, "READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show()
        if (writePermissionGranted) Toast.makeText(this, "WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show()
    }

}
