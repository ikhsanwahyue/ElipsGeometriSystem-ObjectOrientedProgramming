// INHERITENCE : LimasElipsTerpancung merupakan turunan dari LimasElips.
// MULTHITREADING : implements Runnable agar dapat dijalankan dalam thread.
public class LimasElipsTerpancung extends LimasElips implements Runnable {

    public double faktorAtas;           
    public double luasAtas;             
    public double kelilingAtas;         
    public double volumeTerpancung;     
    public double luasPermukaanTerpancung; 
    public double luasSelimutTerpancung;   

    public double luasAlas;             
    public double garisPelukisA;        
    public double garisPelukisB;        
    public double rataGarisPelukis;     
    public double selisihA;             
    public double selisihB;             
    public double kelilingAlas;         

    // Constructor untuk menginisialisasi objek LimasElipsTerpancung.
    public LimasElipsTerpancung(double semiMayorLuar, double semiMinorLuar, 
                                double tinggiLimas, double faktorAtas, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, tinggiLimas, isManual);

        // Validasi : Memastikan parameter yang diberikan valid.
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas elips terpancung harus lebih dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }

        this.faktorAtas = faktorAtas;
    }

    // Menghitung volume limas terpancung menggunakan nilai-nilai yang tersimpan.
    public double hitungVolumeTerpancung() {
        // Luas atas = luas alas * faktor^2 (karena skala linier)
        this.luasAtas = super.luas * Math.pow(this.faktorAtas, 2);
        // Volume frustum
        this.volumeTerpancung = (this.tinggiLimas / 3.0) * 
                                (super.luas + this.luasAtas + Math.sqrt(super.luas * this.luasAtas));
        return this.volumeTerpancung;
    }

    // OVERLOADING : method hitungVolumeTerpancung dengan parameter.
    public double hitungVolumeTerpancung(double semiMayorLuar, double semiMinorLuar, 
                                         double tinggiLimas, double faktorAtas) {
        // Validasi parameter
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas elips terpancung harus lebih dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }

        // Hitung luas alas menggunakan method dari superclass
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasAtas = luasAlas * Math.pow(faktorAtas, 2);
        this.volumeTerpancung = (tinggiLimas / 3.0) * 
                                (luasAlas + this.luasAtas + Math.sqrt(luasAlas * this.luasAtas));
        return this.volumeTerpancung;
    }

    // Menghitung luas permukaan total limas terpancung (alas + atas + selimut).
    public double hitungLuasPermukaanTerpancung() {
        // Hitung luas dan keliling atas berdasarkan faktor skala
        this.luasAtas = super.luas * Math.pow(this.faktorAtas, 2);
        this.kelilingAtas = super.keliling * this.faktorAtas;

        // Selisih sumbu untuk menghitung garis pelukis
        selisihA = super.semiMayorLuar - (super.semiMayorLuar * this.faktorAtas);
        selisihB = super.semiMinorLuar - (super.semiMinorLuar * this.faktorAtas);
        // Garis pelukis pada arah sumbu mayor dan minor (miring dari alas ke atas)
        this.garisPelukisA = Math.sqrt(Math.pow(selisihA, 2) + Math.pow(this.tinggiLimas, 2));
        this.garisPelukisB = Math.sqrt(Math.pow(selisihB, 2) + Math.pow(this.tinggiLimas, 2));
        this.rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;

        // Luas selimut = 0.5 * (keliling alas + keliling atas) * rata-rata garis pelukis
        this.luasSelimutTerpancung = 0.5 * (super.keliling + this.kelilingAtas) * rataGarisPelukis;
        // Luas permukaan total = luas alas + luas atas + luas selimut
        this.luasPermukaanTerpancung = super.luas + this.luasAtas + this.luasSelimutTerpancung;
        return this.luasPermukaanTerpancung;
    }

    // OVERLOADING : method hitungLuasPermukaanTerpancung dengan parameter.
    public double hitungLuasPermukaanTerpancung(double semiMayorLuar, double semiMinorLuar, 
                                                double tinggiLimas, double faktorAtas) {
        // Validasi parameter
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas elips terpancung harus lebih dari 0!");
        }
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }

        // Hitung luas dan keliling alas menggunakan method superclass
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.kelilingAlas = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        // Skala untuk bidang atas
        this.luasAtas = luasAlas * Math.pow(faktorAtas, 2);
        this.kelilingAtas = kelilingAlas * faktorAtas;

        // Selisih sumbu untuk garis pelukis
        selisihA = semiMayorLuar - (semiMayorLuar * faktorAtas);
        selisihB = semiMinorLuar - (semiMinorLuar * faktorAtas);
        this.garisPelukisA = Math.sqrt(Math.pow(selisihA, 2) + Math.pow(tinggiLimas, 2));
        this.garisPelukisB = Math.sqrt(Math.pow(selisihB, 2) + Math.pow(tinggiLimas, 2));
        this.rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;

        // Luas selimut dan total
        this.luasSelimutTerpancung = 0.5 * (kelilingAlas + this.kelilingAtas) * rataGarisPelukis;
        this.luasPermukaanTerpancung = luasAlas + this.luasAtas + this.luasSelimutTerpancung;
        return this.luasPermukaanTerpancung;
    }

    // OVERRIDING : Implementasi method run() dari Runnable.
    @Override
    public void run() {
        super.run();

        if (this.isManual) {
            // Hitung dengan parameter yang disimpan di objek (nilai dari konstruktor)
            this.hitungLuasPermukaanTerpancung(semiMayorLuar, semiMinorLuar, tinggiLimas, faktorAtas);
            this.hitungVolumeTerpancung(semiMayorLuar, semiMinorLuar, tinggiLimas, faktorAtas);
        } else {
            // Hitung dengan nilai yang sudah di-set sebelumnya (dari konstruktor atau setter)
            this.hitungLuasPermukaanTerpancung();
            this.hitungVolumeTerpancung();
        }
    }
}