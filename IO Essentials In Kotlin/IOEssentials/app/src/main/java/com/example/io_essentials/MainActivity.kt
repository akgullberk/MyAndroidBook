import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {

    // "hello2.txt" adında bir dosya oluşturuluyor (varsa üzerine yazılır)
    val file = File("hello2.txt")

    // FileOutputStream kullanarak dosyaya veri yazıyoruz
    // `use` bloğu, otomatik olarak çıkışı kapatır (kaynakları serbest bırakır)
    FileOutputStream(file).use { outputStream ->

        // 1 milyon kez tekrarlayarak her sayıyı dosyaya yazıyoruz
        repeat(1_000_000) {

            // Her bir sayıyı yeni bir satırda yazmak için `encodeToByteArray` kullanıyoruz
            // "$it\n" ifadesi, sayıyı ve sonrasında bir satır başını içerir
            outputStream.write("$it\n".encodeToByteArray())
        }
    }

// FileInputStream kullanarak dosyayı okuma işlemi yapılmış
//    val stringBuilder = StringBuilder()
//    FileInputStream(file).use {
//        var byte = it.read() // Dosyadaki ilk baytı okuyoruz
    // Dosyanın sonuna kadar okuma işlemi devam eder
//        while(byte != -1) {
    // Okunan baytı karaktere çevirip StringBuilder'a ekliyoruz
//            stringBuilder.append(byte.toChar())
//            byte = it.read() // Sonraki baytı okuyoruz
//        }
//    }
//    // Dosyanın tamamı okunduktan sonra içeriği yazdırıyoruz
//    println(stringBuilder.toString())
}

// Bu fonksiyon bir klasördeki dosyaları ve alt klasörleri özyinelemeli (rekürsif) olarak listeler
fun printFilesRecursively(folder: File, indentationLevel: Int = 0) {
    // Klasördeki her bir dosya veya alt klasör için işlemi gerçekleştiriyoruz
    folder.listFiles()?.forEach { child ->
        // Hiyerarşiyi (girintiyi) göstermek için buildString kullanıyoruz
        val line = buildString {
            // indentationLevel kadar boşluk ekliyoruz
            repeat(indentationLevel) {
                append("  ")
            }
            // Dosya veya klasör adını yazıyoruz
            append("- ${child.name}")
        }

        // Dosya veya klasör adını ekrana yazdırıyoruz
        println(line)

        // Eğer bu bir klasörse, içeriğini yine aynı fonksiyonla özyinelemeli olarak yazdırıyoruz
        if(child.isDirectory) {
            // Alt klasör için indentationLevel bir arttırarak çağırıyoruz
            printFilesRecursively(child, indentationLevel + 1)
        }
    }
}