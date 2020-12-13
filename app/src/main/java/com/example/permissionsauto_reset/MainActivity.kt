package com.example.permissionsauto_reset

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.example.permissionsauto_reset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.isAutoRevokeWhitelisted.text =
            applicationContext.packageManager.isAutoRevokeWhitelisted.toString()
        binding.button.setOnClickListener {
            val i = Intent(Intent.ACTION_AUTO_REVOKE_PERMISSIONS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(i)

            // NG java.lang.SecurityException: Permission denial: writing to settings requires:android.permission.WRITE_SECURE_SETTINGS
//            Settings.Global.putString(
//                contentResolver,
//                "auto_revoke_parameters",
//                "enabledForPreRApps=false,unusedThresholdMs=60000,checkFrequencyMs=60000"
//            )
            // OK
            Log.d(
                "test", Settings.Global.getString(
                    contentResolver,
                    "auto_revoke_parameters"
                )
            )

        }
        setContentView(binding.root)
    }
}