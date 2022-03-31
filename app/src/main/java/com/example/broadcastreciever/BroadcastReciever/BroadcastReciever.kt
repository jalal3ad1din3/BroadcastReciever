package com.example.broadcastreciever.BroadcastReciever

import android.app.IntentService
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService

class NBroadcastReceiver(/*private val listener: MyListener*/) : BroadcastReceiver() {

    val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    val TAG = "SMSBroadcastReceiver"

    override fun onReceive(context: Context?, intent: Intent) {

        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

        if (state == TelephonyManager.EXTRA_STATE_RINGING) {
            Toast.makeText(context, "$state", Toast.LENGTH_SHORT).show()

            Log.d("@@@","@@@$state")

        }
        Log.d("@@@","@@@ $state")

    }

}
/*Log.i(TAG, "Intent recieved: " + intent.action)
if (intent.action === SMS_RECEIVED) {
    val bundle = intent.extras
    if (bundle != null) {
        val pdus = bundle["pdus"] as Array<*>?
        val messages: Array<SmsMessage?> = arrayOfNulls(pdus!!.size)
        for (i in pdus.indices) {
            messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                //  listener.onclick(messages)
        }
        if (messages.size > -1) {
            Toast.makeText(
                context, "${messages[0]?.messageBody}${messages[0]?.originatingAddress}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
}

interface MyListener {
fun onclick(messages: Array<SmsMessage?>)
}
}*/


//    override fun onReceive(p0: Context?, p1: Intent?) {
//
//        val state = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
//        val stat = p1?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
//        Log.d("@@@", "@@@ $state")
//        Toast.makeText(p0, "$state,$stat", Toast.LENGTH_SHORT).show()
//
////        if (BatteryManager. == state){
////        }
//    }

