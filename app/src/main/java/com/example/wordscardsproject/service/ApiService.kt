package com.example.wordscardsproject.service

import com.example.wordscardsproject.model.TranslateModel
import com.example.wordscardsproject.model.TranslateModelSend
import com.example.wordscardsproject.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService {


    @Headers(
        "content-type: application/x-www-form-urlencoded",
        "accept-encoding: application/gzip",
        "x-rapidapi-key: d7abb4c6b9mshd3d8e7f10e0261dp13bf15jsncbe41ca2a70e",
        "x-rapidapi-host: google-translate1.p.rapidapi.com"
    )
    @POST(Constants.TRANSLATE_API)
    fun searchGoogleTranslate(
        @Body sendTranslateForm: TranslateModelSend
    ): Call<TranslateModel>



//    @GET(Constants.SEARCH_NOMENCLATURE_1C)
//    fun searchNomenclature1C(
//        @Query("code")code:String
//    ): Call<searchResult>
//
//    @GET(Constants.LIST_WAREHOUSE_1C)
//    fun listOfWarehouse(): Call<WarehouseModel>
//
//    @GET(Constants.LIST_DOCUMENTS_1C)
//    fun listOfDocuments(
//        @Query("codeWarehouse")codeWarehouse:String,
//        @Query("dateStart")dateStart:String,
//        @Query("dateEnd")dateEnd:String,
//    ):Call<DocumentsModel>
}