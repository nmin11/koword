import kotlinx.cli.Subcommand

class Sample() : Subcommand(name = "sample", actionDescription = "샘플입니다") {
  override fun execute() {
    println("샘플 명령어 작동")
  }
}