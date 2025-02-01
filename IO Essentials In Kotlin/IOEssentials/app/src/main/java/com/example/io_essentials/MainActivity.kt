import java.io.File // Gerekli dosya işlemleri için java.io.File sınıfı kullanılır


fun main() { // Ana fonksiyon, programın başlangıç noktasıdır
    val folder = File(".") // Geçerli dizini ("." ile) temsil eden bir File nesnesi oluşturuluyor

    printFilesRecursively(folder) // printFilesRecursively fonksiyonunu çağırarak, klasörün içeriğini yazdırıyoruz
}

// printFilesRecursively fonksiyonu, bir klasördeki tüm dosyaları ve alt klasörleri
// rekürsif olarak yazdırmak için kullanılır
fun printFilesRecursively(folder: File, indentationLevel: Int = 0) {

    // listFiles() fonksiyonu, klasördeki tüm dosya ve alt klasörlerin bir listesini döndürür
    folder.listFiles()?.forEach { child ->

        // buildString ile, hiyerarşiyi yansıtacak şekilde her dosya için bir satır oluşturuluyor
        val line = buildString {

            // Mevcut indentationLevel kadar boşluk ekleyerek hiyerarşiyi oluşturuyoruz
            repeat(indentationLevel) {
                append("  ") // İki boşluk, her seviye için bir girinti
            }

            // Dosya adını yazdırıyoruz
            append("- ${child.name}")
        }

        // Hiyerarşik şekilde yazdırılan dosya adını ekrana yazdırıyoruz
        println(line)

        // Eğer bu bir klasörse, içeriğini yeniden yazdırmak için fonksiyonu çağırıyoruz
        if(child.isDirectory) {

            // Rekürsif olarak alt klasörü de listelemek için indentationLevel'ı bir artırıyoruz
            printFilesRecursively(child, indentationLevel + 1)
        }
    }
}