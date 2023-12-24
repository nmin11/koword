package command

import WordsProperty.random
import WordsProperty.wordBook
import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import model.WordBook

class Guess : Subcommand(name = "guess", actionDescription = "영단어 맞추기") {
  private val count by option(ArgType.Int, shortName = "c", description = "문제 수").default(20)
  private val incorrectWords = mutableListOf<WordBook.Word>()
  private val selectedIndexes = mutableSetOf<Int>()

  override fun execute() {
    val green = "\u001B[32m"
    val red = "\u001B[31m"
    val reset = "\u001B[0m"

    while (selectedIndexes.size < count) {
      val idx = random.nextInt(wordBook.count)
      if (idx !in selectedIndexes) {
        selectedIndexes.add(idx)
      }
    }

    for ((problemIdx, wordIdx) in selectedIndexes.withIndex()) {
      println("$green Problem ${problemIdx + 1} / $count$reset")
      guessWord(wordIdx)
    }

    if (incorrectWords.isEmpty()) {
      println("$green 🎉 전부 맞췄습니다!$reset")
    } else {
      println("$red ❌ 틀린 문제 목록$reset")
      for (word in incorrectWords) {
        println("● ${word.word} : ${word.meaning.joinToString(" / ")}")
      }
    }
  }

  private fun guessWord(idx: Int) {
    val word = wordBook.words[idx]
    println(word.meaning.joinToString(" / "))
    print(">> ")
    val answer = readln()
    if (word.word != answer) {
      incorrectWords.add(word)
    }
  }
}
