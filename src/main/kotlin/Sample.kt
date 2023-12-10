import kotlinx.cli.Subcommand

class Sample() : Subcommand(name = "sample", actionDescription = "샘플입니다") {
  override fun execute() {
    println("샘플 명령어 작동")
    val words =
      checkNotNull(javaClass.getResource("words.json")) {
        "words.json 파일을 찾을 수 없습니다"
      }.readText()
    println(words)
  }
}
