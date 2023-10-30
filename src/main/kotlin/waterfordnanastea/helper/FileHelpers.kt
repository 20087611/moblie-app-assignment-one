package waterfordnanastea.helper

import mu.KotlinLogging
import java.io.*

val logger = KotlinLogging.logger {}

fun write( fileName: String, data: String) {
    try {
        File(fileName).writeText(data)
    }catch (e:Exception){
        logger.error { "Cannot read file:" + e.toString() }
    }
}

fun read(fileName: String): String {
    val file = File(fileName)
    try{
        val line = mutableListOf<String>()
        file.forEachLine {line.add(it)}
        return line.joinToString(separator = "\n")
    }catch (e:FileNotFoundException){
        logger.error { "Cannot Find file:" +e.toString() }
    }catch (e:IOException){
        logger.error { "Cannot Find file:" +e.toString() }
    }
    return "{}"
}

fun exists(fileName: String): Boolean {
    val file = File(fileName)
    return file.exists()
}