package command

import WordsProperty.random
import WordsProperty.wordBook
import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import model.ColoredText
import model.WordBook

class Mean : Subcommand(name = "mean", actionDescription = "영단어 뜻 맞추기") {
  private val count by option(ArgType.Int, shortName = "c", description = "문제 수").default(20)
  private val incorrectWords = mutableListOf<WordBook.Word>()
  private val selectedIndexes = mutableSetOf<Int>()

  override fun execute() {
    while (selectedIndexes.size < count) {
      val idx = random.nextInt(wordBook.count)
      if (idx !in selectedIndexes) {
        selectedIndexes.add(idx)
      }
    }

    for ((problemIdx, wordIdx) in selectedIndexes.withIndex()) {
      println("${ColoredText.GREEN} Problem ${problemIdx + 1} / $count${ColoredText.RESET}")
      meanWord(wordIdx)
    }

    if (incorrectWords.isEmpty()) {
      println("${ColoredText.GREEN} 🎉 전부 맞췄습니다!${ColoredText.RESET}")
    } else {
      println("${ColoredText.RED} ❌ 틀린 문제 목록${ColoredText.RESET}")
      for (word in incorrectWords) {
        println("● ${word.word} : ${word.meaning.joinToString(" / ")}")
      }
    }
  }

  private fun meanWord(idx: Int) {
    val word = wordBook.words[idx]
    println(word.word)
    print(">> ")
    val answer = readln()
    if (!word.meaning.contains(answer)) {
      incorrectWords.add(word)
    }
  }
}
