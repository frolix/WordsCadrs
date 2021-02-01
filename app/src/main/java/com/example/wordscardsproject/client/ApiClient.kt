package com.example.wordscardsproject.client

import android.util.Log
import com.example.wordscardsproject.service.ApiService
import com.example.wordscardsproject.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class ApiClient {
    private lateinit var apiService: ApiService

    fun getApiService(): ApiService {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.GOOGLE_TRANSLATE_HOME)

//                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }


    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                    Log.d("MainActivity", "checkClientTrusted1: ")
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                    Log.d("MainActivity", "checkClientTrusted2: ")

                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    Log.d("MainActivity", "checkClientTrusted3: ")

                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(hostnameVerifier = HostnameVerifier { _, _ -> true })
            return builder


        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}