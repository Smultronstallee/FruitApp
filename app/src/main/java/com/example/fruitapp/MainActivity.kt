package com.example.fruitapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.fruitapp.data.local.TokenManager
import com.example.fruitapp.ui.address.ShippingAddressDetail
import com.example.fruitapp.ui.address.component.AddAddressDialog
import com.example.fruitapp.ui.auth.FacebookAuthManager
import com.example.fruitapp.ui.cart.CartScreen
import com.example.fruitapp.ui.navigation.AppNavGraph
import com.facebook.FacebookSdk
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import android.os.Build
import android.util.Base64
import kotlinx.coroutines.launch
import java.security.MessageDigest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(getApplicationContext());
        Log.d("GG_CLIENT_ID", BuildConfig.GG_CLIENT_ID)
        handleDeepLink(intent)
        setContent{
            val navController = rememberNavController()
            AppNavGraph(navController)
        }
        printKeyHash()
    }
    private fun handleDeepLink(intent: Intent){
        intent?.data?.let{
                uri->
            val token = uri.getQueryParameter("token")

            if(token!=null){
                lifecycleScope.launch {
                    tokenManager.saveToken(token)
                }
            }
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        FacebookAuthManager.getCallbackManager()
            .onActivityResult(requestCode, resultCode, data)
    }
    private fun printKeyHash() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val info = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNING_CERTIFICATES
                )

                info.signingInfo?.apkContentsSigners?.forEach { signature ->
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())

                    Log.d(
                        "FACEBOOK_HASH",
                        Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    )
                }

            } else {
                @Suppress("DEPRECATION")
                val info = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                )

                @Suppress("DEPRECATION")
                info.signatures?.forEach { signature ->
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())

                    Log.d(
                        "FACEBOOK_HASH",
                        Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    )
                }
            }

        } catch (e: Exception) {
            Log.e("FACEBOOK_HASH", "Error", e)
        }
    }
}