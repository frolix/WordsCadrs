package com.example.wordscardsproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wordscardsproject.client.ApiClient
import com.example.wordscardsproject.model.TranslateModel
import com.example.wordscardsproject.model.TranslateModelSend
import kotlinx.android.synthetic.main.activity_translate.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TranslateActivity : AppCompatActivity() {
    private lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        apiClient = ApiClient()

        translate_button.setOnClickListener {
            translateText(apiClient, TranslateModelSend(translate_text.text.toString(), "eu", "ua"))
        }

    }

    private fun translateText(apiClient: ApiClient, translateModelSend: TranslateModelSend) {
        CoroutineScope(Dispatchers.IO).launch {
            apiClient.getApiService()
                .searchGoogleTranslate(translateModelSend)
                .enqueue(object : Callback<TranslateModel> {
                    override fun onResponse(
                        call: Call<TranslateModel>,
                        response: Response<TranslateModel>
                    ) {
                        if (response.isSuccessful) {
                            var textAdd = ""
                            response.body()?.data?.translations?.forEach { textAdd += it.translatedText }
                            text_output_translate.text = textAdd
                        }
                    }

                    override fun onFailure(call: Call<TranslateModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "error translate", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
        }
    }

}