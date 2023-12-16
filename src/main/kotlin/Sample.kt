import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.cli.Subcommand
import model.WordBook

val objectMapper: ObjectMapper =
  ObjectMapper()
    .registerKotlinModule()
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .findAndRegisterModules()

class Sample() : Subcommand(name = "sample", actionDescription = "샘플입니다") {
  override fun execute() {
    println("샘플 명령어 작동")
    val json =
      checkNotNull(javaClass.getResource("META-INF/words.json")).readText()
    val words = readValue<WordBook>(json)
    words.words.forEach {
      println(it)
    }
  }
}

private inline fun <reified T> readValue(json: String): T {
  return objectMapper.readValue(json)
}
