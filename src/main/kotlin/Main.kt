import kotlinx.cli.ArgParser

fun main(args: Array<String>) {
  KowordApplication().execute(args)
}

class KowordApplication() {
  fun execute(args: Array<String>) {
    val argParser = ArgParser(programName = "Koword")
    argParser.subcommands(
      Sample(),
    )
    argParser.parse(args.ifEmpty { arrayOf("-h") })
  }
}
