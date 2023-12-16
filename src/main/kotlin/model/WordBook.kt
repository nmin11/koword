package model

data class WordBook(
  val words: List<Word>,
  val count: Int = words.size,
) {
  data class Word(
    val word: String,
    val meaning: List<String>,
    val example: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
  )
}
