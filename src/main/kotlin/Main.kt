import kotlinx.cli.ArgParser

fun main(args: Array<String>) {
  KowordApplication().execute(args)
}

class KowordApplication() {
  fun execute(args: Array<String>) {
    val parser = ArgParser(programName = "Koword")
    parser.subcommands(
      Sample(),
    )
    parser.parse(args.ifEmpty { arrayOf("-h") })
  }
}
