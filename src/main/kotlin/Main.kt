import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import command.Guess
import command.Mean
import kotlinx.cli.ArgParser
import model.WordBook
import java.security.SecureRandom

private val objectMapper: ObjectMapper =
  ObjectMapper()
    .registerKotlinModule()
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .findAndRegisterModules()
private val json = checkNotNull(WordsProperty::class.java.getResource("META-INF/words.json")).readText()

object WordsProperty {
  val wordBook = objectMapper.readValue<WordBook>(json)
  val random: SecureRandom = SecureRandom.getInstanceStrong()
}

fun main(args: Array<String>) {
  KowordApplication().execute(args)
}

class KowordApplication() {
  fun execute(args: Array<String>) {
    val parser = ArgParser(programName = "Koword")
    parser.subcommands(
      Guess(),
      Mean(),
    )
    parser.parse(args.ifEmpty { arrayOf("-h") })
  }
}
