package command

import WordsProperty.random
import WordsProperty.wordBook
import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import model.ColoredText

class Wordle : Subcommand(name = "wordle", actionDescription = "영단어 추측 게임") {
  private val length by option(ArgType.Int, shortName = "l", description = "단어 길이")
  private val tries by option(ArgType.Int, shortName = "t", description = "시도 가능 횟수").default(6)
  private val selectedWord =
    if (length != null) {
      wordBook.words.filter { it.word.length == length }[random.nextInt(wordBook.count)].word
    } else {
      wordBook.words[random.nextInt(wordBook.count)].word
    }

  override fun execute() {
    for (i in 1..tries) {
      val numberOfAttempts =
        when (i) {
          1 -> "1st try"
          2 -> "2nd try"
          3 -> "3rd try"
          else -> "${i}th try"
        }
      println("${ColoredText.GREEN}$numberOfAttempts / total $tries tries${ColoredText.RESET}")
      println("단어 길이: ${selectedWord.length}")
      val result = wordle()
      if (result) {
        println("${ColoredText.GREEN} 🎉 정답입니다!${ColoredText.RESET}")
        break
      }
      if (i == tries) {
        println("❌ 정답은 $selectedWord 입니다.")
      }
    }
  }

  private fun wordle(): Boolean {
    print(">> ")
    val answer = readln()
    if (answer == selectedWord) {
      return true
    } else if (answer.length != selectedWord.length) {
      println("단어 길이가 다릅니다.")
      return wordle()
    }

    val answerChars = answer.toCharArray()
    val selectedWordChars = selectedWord.toCharArray()

    for (i in answerChars.indices) {
      if (answerChars[i] == selectedWordChars[i]) {
        print(ColoredText.BACKGROUND_GREEN)
        print(ColoredText.BLACK)
        print(answerChars[i])
        print(ColoredText.RESET)
      } else if (answerChars[i] in selectedWordChars) {
        print(ColoredText.BACKGROUND_YELLOW)
        print(ColoredText.BLACK)
        print(answerChars[i])
        print(ColoredText.RESET)
      } else {
        print(ColoredText.BACKGROUND_GRAY)
        print(ColoredText.BLACK)
        print(answerChars[i])
        print(ColoredText.RESET)
      }
    }
    println()

    return false
  }
}
