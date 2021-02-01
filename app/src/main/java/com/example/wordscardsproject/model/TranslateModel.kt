package com.example.wordscardsproject.model

data class TranslateModel(
    var `data`: Data
) {
    data class Data(
        var translations: List<Translation>
    ) {
        data class Translation(
            var translatedText: String
        )
    }
}