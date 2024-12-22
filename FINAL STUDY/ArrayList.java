/*
 * GÖREVLER (TASKLER):

ArrayList.java Dosyası (Temel Alanlar ve Kurucu):

Bir ArrayList sınıfı oluştur (dosya adı: ArrayList.java).
İçinde, verileri tutmak için bir Object[] ya da T[] dizi alanı (field) tanımla.
Dizinin kaç eleman barındırdığını tutacak bir size alanı tanımla.
Varsayılan kapasite (örneğin 10) ile bir constructor oluştur. Bu constructor içinde size = 0 olacak ve dizi new Object[10] gibi bir başlangıç boyutu alacak.
add(element) Metodu:

add(element) metodunu yaz.
Eğer dizi dolduysa kapasiteyi artır (örn: kapasiteyi iki katına çıkararak yeni bir dizi oluştur ve eski elemanları kopyala).
Sonra yeni elemanı diziye en sona ekle, size değerini güncelle.
add(index, element) Metodu:

Verilen index’i kontrol et (index geçerli değilse hata fırlatabilirsin ya da konsola uyarı yazabilirsin).
Eğer dizi dolduysa kapasiteyi artır.
Belirtilen index’ten itibaren elemanları sağa kaydır (arkadan başlayarak).
Yeni elemanı bu boşalan yere koy.
size değerini güncelle.
remove(index) Metodu:

Index geçerli mi kontrol et.
İlgili elemanı diziden çıkar (yani o konumdaki elemanı geçerli sayma).
O elemandan sonrakileri sola doğru kaydır.
size değerini bir azalt.
get(index) ve set(index, element) Metodları:

get(index): Dizideki ilgili index elemanını döndür.
set(index, element): Dizideki ilgili index elemanının değerini güncelle.
Test Cases Yapısı (Bunu en sona bırak):

Yazdığın metodları test edecek basit bir main metodu yaz.
Örnek:
csharp
Kodu kopyala
ArrayList list = new ArrayList();
list.add("A");
list.add("B");
list.add(1, "C"); // A, C, B sıralı olmalı
list.remove(0); // C, B kalmalı
System.out.println(list.get(0)); // C basmalı
Bu basit test ile kodunu kontrol et.
Şimdilik bu altı görevi sırayla yapmanı istiyorum. Hepsini bitirdiğinde kodlarını bana aktar, birlikte kontrol edelim.
 */

class ArrayList {
    /* --------- SINIFIN ANA DEĞİŞKENLERİ (FIELDS) ---------
     * Bu sınıfta iki temel değişken kullanıyoruz:
     * 1. elements: Object tipinde bir dizi
     * - Object, Java'da tüm sınıfların atasıdır
     * - Object kullanarak her türden veriyi (String, Integer, Double vs.) tutabiliriz
     * - Dizi, bellekte ardışık olarak yerleştirilmiş aynı tipteki verileri tutan bir veri yapısıdır
     * 
     * 2. size: Dizideki mevcut eleman sayısı
     * - Dizinin gerçek boyutu (capacity) ile karıştırılmamalı
     * - Örneğin dizi 10 elemanlık olabilir ama içinde 3 eleman varsa size=3'tür
     */
    private Object[] elements;
    private int size;
    
    /* --------- GÖREV 1: CONSTRUCTOR (KURUCU METOD) ---------
     * Constructor, bir sınıftan nesne oluşturulduğunda otomatik çalışan özel metoddur
     * Örneğin: ArrayList list = new ArrayList(); yazıldığında bu metod çağrılır
     * 
     * Bu constructor şunları yapar:
     * 1. 10 elemanlık boş bir dizi oluşturur (başlangıç kapasitesi)
     * 2. size'ı 0'a ayarlar çünkü henüz hiç eleman eklenmemiştir
     */
    public ArrayList() {
        elements = new Object[10];
        size = 0;
    }

    /* --------- GÖREV 2: DİZİNİN SONUNA ELEMAN EKLEME (add) ---------
     * Bu metod dizinin sonuna yeni bir eleman ekler
     * @param element: Eklenecek eleman (herhangi bir tipte olabilir çünkü Object tipinde)
     * 
     * Metodun çalışma adımları:
     * 1. Önce dizinin dolu olup olmadığını kontrol eder
     * 2. Doluysa, kapasiteyi 2 katına çıkarır
     * 3. Yeni elemanı sona ekler
     * 4. size değerini günceller
     */
    public void add(Object element) {
        // Dizinin doluluk kontrolü
        // Eğer dizi doluysa (size == dizinin uzunluğu)
        if (size == elements.length) {
            // Yeni bir dizi oluştur (eskisinin 2 katı büyüklüğünde)
            Object[] newElements = new Object[elements.length * 2];
            // Eski elemanları yeni diziye kopyala
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            // Yeni diziyi elements referansına ata
            elements = newElements;
        }
        
        // Yeni elemanı dizinin sonuna ekle
        elements[size] = element;
        // Eleman sayısını bir artır
        size++;
    }

    /* --------- GÖREV 3: BELİRLİ BİR KONUMA ELEMAN EKLEME ---------
     * Bu metod dizinin istenen konumuna yeni bir eleman ekler
     * @param index: Elemanın ekleneceği konum (0'dan başlar)
     * @param element: Eklenecek eleman
     * 
     * Metodun çalışma mantığı:
     * 1. Verilen index geçerli mi diye kontrol eder
     * 2. Geçerli değilse hata mesajı verir ve metodu sonlandırır
     * 3. Dizi doluysa kapasiteyi artırır
     * 4. İstenen konumdan itibaren tüm elemanları sağa kaydırır
     * 5. Yeni elemanı istenen konuma yerleştirir
     */
    public void add(int index, Object element) {
        // Geçersiz index kontrolü
        // Index 0'dan küçük veya mevcut eleman sayısından büyük olamaz
        if (index < 0 || index > size) {
            System.out.println("Hata: Geçersiz index değeri! " +
                             "Index 0 ile " + size + " arasında olmalıdır.");
            return;
        }

        // Kapasite kontrolü
        // Dizi dolduysa yeni ve daha büyük bir dizi oluştur
        if (size == elements.length) {
            // Yeni dizi oluştur (2 kat büyük)
            Object[] newElements = new Object[elements.length * 2];
            // Eski elemanları kopyala
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            // Yeni diziyi referans göster
            elements = newElements;
        }

        // Elemanları sağa kaydırma işlemi
        // Sondan başlayarak, eklenecek yere kadar olan elemanları bir sağa kaydır
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        // Yeni elemanı belirlenen konuma yerleştir
        elements[index] = element;
        // Toplam eleman sayısını bir artır
        size++;
    }

    /* --------- GÖREV 4: BELİRLİ BİR KONUMDAN ELEMAN SİLME (remove) ---------
     * Bu metod verilen index'teki elemanı diziden çıkarır
     * @param index: Silinecek elemanın konumu
     * @return: Silinen eleman (ileride lazım olabilir diye)
     * 
     * Metodun detaylı çalışması:
     * 1. Index kontrolü yapar (geçerli bir index mi?)
     * 2. Silinecek elemanı geçici bir değişkende saklar
     * 3. O konumdan sonraki tüm elemanları sola kaydırır
     * 4. size değerini azaltır
     * 5. Silinen elemanı geri döndürür
     */
    public Object remove(int index) {
        // Geçersiz index kontrolü
        if (index < 0 || index >= size) {
            System.out.println("HATA: Silme işlemi için geçersiz index! " +
                             "Index 0 ile " + (size-1) + " arasında olmalıdır.");
            return null;
        }

        // Silinecek elemanı geçici olarak sakla
        Object removedElement = elements[index];

        // Elemanları sola kaydır
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        // Son elemanı null yap ve size'ı azalt
        elements[size - 1] = null;
        size--;

        return removedElement;
    }

    /* --------- GÖREV 5: GETTER VE SETTER METODLARI ---------
     * get: Dizinin belirli bir konumundaki elemanı döndürür
     * set: Dizinin belirli bir konumundaki elemanı değiştirir
     */
    
    /**
     * get metodu: İstenen konumdaki elemanı getirir
     * @param index: İstenen elemanın konumu
     * @return: O konumdaki eleman
     * 
     * Metodun işleyişi:
     * 1. Index kontrolü yapar
     * 2. Geçerliyse o konumdaki elemanı döndürür
     */
    public Object get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("HATA: Geçersiz index! " +
                             "Index 0 ile " + (size-1) + " arasında olmalıdır.");
            return null;
        }
        return elements[index];
    }

    /**
     * set metodu: Belirli bir konumdaki elemanı değiştirir
     * @param index: Değiştirilecek elemanın konumu
     * @param element: Yeni eleman
     * @return: Eski eleman (değiştirilen)
     * 
     * Metodun işleyişi:
     * 1. Index kontrolü yapar
     * 2. Eski elemanı geçici değişkende saklar
     * 3. Yeni elemanı yerleştirir
     * 4. Eski elemanı döndürür
     */
    public Object set(int index, Object element) {
        if (index < 0 || index >= size) {
            System.out.println("HATA: Değiştirme işlemi için geçersiz index! " +
                             "Index 0 ile " + (size-1) + " arasında olmalıdır.");
            return null;
        }
        Object oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    /* --------- GÖREV 6: TEST METODU ---------
     * Bu metod, yazdığımız ArrayList sınıfının tüm fonksiyonlarını test eder
     * 
     * TEST SENARYOLARI:
     * 1. Boş bir ArrayList oluşturma
     * 2. Sona eleman ekleme (add)
     * 3. Araya eleman ekleme (add with index)
     * 4. Eleman silme (remove)
     * 5. Eleman alma (get)
     * 6. Eleman güncelleme (set)
     * 
     * ADIM ADIM NE YAPILIYOR?
     * 1. Önce yeni bir ArrayList oluşturuyoruz (constructor çalışıyor)
     * 2. "A" elemanını ekliyoruz (ilk eleman, index=0'a gelecek)
     * 3. "B" elemanını ekliyoruz (ikinci eleman, index=1'e gelecek)
     * 4. "C" elemanını 1. indexe ekliyoruz (A,C,B şeklinde olacak)
     * 5. 0. indexteki elemanı siliyoruz (C,B kalacak)
     * 6. Kalan elemanları ekrana yazdırıyoruz
     * 
     * Her adımda dizinin durumunu ekrana yazdırarak ne olduğunu görebiliriz
     */
    public static void main(String[] args) {
        // --------- TEST BAŞLANGICI ---------
        System.out.println("========= ARRAYLIST TEST BAŞLIYOR =========");
        
        // 1. ADIM: Yeni ArrayList oluştur
        System.out.println("\n----- 1. ADIM: ArrayList Oluşturuluyor -----");
        ArrayList list = new ArrayList();
        System.out.println("Boş ArrayList oluşturuldu. Şu an size = " + list.size);
        
        // 2. ADIM: İlk eleman ekleme
        System.out.println("\n----- 2. ADIM: İlk Eleman ('A') Ekleniyor -----");
        list.add("A");
        System.out.println("'A' eklendi. Şu anki size = " + list.size);
        System.out.println("0. indexteki eleman: " + list.get(0));
        
        // 3. ADIM: İkinci eleman ekleme
        System.out.println("\n----- 3. ADIM: İkinci Eleman ('B') Ekleniyor -----");
        list.add("B");
        System.out.println("'B' eklendi. Şu anki size = " + list.size);
        System.out.println("Tüm elemanlar: " + list.get(0) + ", " + list.get(1));
        
        // 4. ADIM: Araya eleman ekleme
        System.out.println("\n----- 4. ADIM: Araya Eleman ('C') Ekleniyor -----");
        list.add(1, "C");  // A,C,B olacak şekilde
        System.out.println("'C' 1. indexe eklendi. Şu anki size = " + list.size);
        System.out.println("Tüm elemanlar: " + list.get(0) + ", " + list.get(1) + ", " + list.get(2));
        
        // 5. ADIM: Eleman silme
        System.out.println("\n----- 5. ADIM: İlk Eleman Siliniyor -----");
        Object silinen = list.remove(0);  // A silinecek, C,B kalacak
        System.out.println("Silinen eleman: " + silinen);
        System.out.println("Silme sonrası size = " + list.size);
        System.out.println("Kalan elemanlar: " + list.get(0) + ", " + list.get(1));
        
        // 6. ADIM: Eleman güncelleme
        System.out.println("\n----- 6. ADIM: Eleman Güncelleme -----");
        Object eskiDeger = list.set(0, "X");  // C yerine X koyuyoruz
        System.out.println("0. indexteki eski değer: " + eskiDeger);
        System.out.println("Güncelleme sonrası elemanlar: " + list.get(0) + ", " + list.get(1));
        
        // TEST SONUCU
        System.out.println("\n========= TEST TAMAMLANDI =========");
        System.out.println("Final durum:");
        System.out.println("- Size: " + list.size);
        System.out.print("- Elemanlar: ");
        for(int i = 0; i < list.size; i++) {
            System.out.print(list.get(i));
            if(i < list.size - 1) System.out.print(", ");
        }
        System.out.println("\n");
    }

    /* --------- YARDIMCI METODLAR ---------
     * toString metodu: ArrayList'in içeriğini String olarak döndürür
     * Bu metod, System.out.println(list) şeklinde çağrıldığında otomatik çalışır
     * 
     * NASIL ÇALIŞIR?
     * 1. Önce boş bir String oluşturur
     * 2. Bu String'e "[" ekler
     * 3. Dizideki her elemanı sırayla ekler
     * 4. Elemanlar arasına ", " koyar
     * 5. En sona "]" ekler
     * 
     * ÖRNEK ÇIKTI: [A, B, C]
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < size; i++) {
            result.append(elements[i]);
            if(i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}

