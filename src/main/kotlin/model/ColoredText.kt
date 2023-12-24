package model

enum class ColoredText {
  GREEN,
  RED,
  BLACK,
  BACKGROUND_GREEN,
  BACKGROUND_YELLOW,
  BACKGROUND_GRAY,
  RESET,
  ;

  override fun toString(): String {
    return when (this) {
      GREEN -> "\u001B[32m"
      RED -> "\u001B[31m"
      BLACK -> "\u001B[30m"
      BACKGROUND_GREEN -> "\u001B[42m"
      BACKGROUND_YELLOW -> "\u001B[43m"
      BACKGROUND_GRAY -> "\u001B[47m"
      RESET -> "\u001B[0m"
    }
  }
}
