import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.system.measureTimeMillis

fun main() {
    val file = File("hello2.txt")

// Yorum satırına alınmış yazma işlemi, dosyaya büyük miktarda veri yazacaktı
    // Dosyaya 10 milyon satır yazma işlemi için kod burada yer alıyordu
    //
    // FileOutputStream(file).use { outputStream ->
    //     repeat(10_000_000) {
    //         outputStream.write("$it\n".encodeToByteArray())
    //     }
    // }

    // İkinci kısımda dosya okuma işlemi yapılmaktadır


    // İlk okuma işlemi için byte array ile dosyayı okuma süresi ölçülüyor
    val stringBuilder = StringBuilder()  // Dosya içeriğini bir StringBuilder'a yazacağız
    val time1 = measureTimeMillis {  // Bu blokta geçen süre ölçülür
        FileInputStream(file).use {
            it.readBytes()  // Dosyadaki tüm baytları okuyoruz
        }
    }

    // İkinci okuma işlemi için bufferedReader ile dosyayı okuma süresi ölçülüyor
    val stringBuilder2 = StringBuilder()  // Alternatif okuma sonucunu buraya yazacağız
    val time2 = measureTimeMillis {  // Bu blokta geçen süre ölçülür
        FileInputStream(file).bufferedReader().use { reader ->  // BufferedReader ile daha verimli okuma
            var byte = reader.read()  // İlk byte'ı okuyoruz
            // Dosya sonuna kadar okunana kadar devam eder
            while(byte != -1) {
                stringBuilder.append(byte.toChar())  // Okunan byte'ı karaktere çevirip ekliyoruz
                byte = reader.read()  // Sonraki byte'ı okuyoruz
            }
        }
    }

    // İlk ve ikinci okuma işlemlerinin sürelerini yazdırıyoruz
    println("Time1: $time1 ms.")  // İlk okuma süresi
    println("Time2: $time2 ms.")  // İkinci okuma süresi

    // Aşağıdaki kısımda, okunan içeriği yazdırmak için bir çözüm olabilir, ancak şu an yorum satırında
    //
    // println(stringBuilder.toString())  // StringBuilder içeriğini yazdırıyoruz
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