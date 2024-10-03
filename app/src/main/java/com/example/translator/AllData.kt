package com.example.translator

data class TranslatorData(
    val word: String,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>
)

data class Phonetic(
    val text: String?
)

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

data class Definition(
    val definition: String
)