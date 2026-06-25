// INHERITENCE : LimasElips extends Elips, sehingga LimasElips merupakan subclass dari Elips.
// MULTITHREADING : "implements Runnable" agar bisa berjalan sebagai Thread mandiri untuk mengh
public class LimasElips extends Elips implements Runnable {

    // ENCAPSULATION : Atribut state untuk membungkus hasil perhitungan volume dan luas permukaan limas elips.
    public double tinggiLimas, volumeLimasElips, luasPermukaanLimasElips, luasSelimutLimasElips;
    public double luasAlas, garisPelukisA, garisPelukisB, rataGariPelukis, rataGarisPelukis, kelilingAlas;
    
    // Constructor untuk menginisialisasi atribut state limas elips.
    public LimasElips(double semiMayorLuar, double semiMinorLuar, double tinggiLimas, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);

        // Validasi : Memastikan nilai semi mayor dan semi minor lebih besar dari 0, serta semi mayor lebih besar atau sama dengan semi minor, dan tinggi limas lebih besar dari 0.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas/kerucut elips harus lebih besar dari 0!");
        }
        this.tinggiLimas = tinggiLimas;
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------
    // OVERRIDING : Implementasi method hitungVolume() dan hitungLuas

    public double hitungVolumeLimasElips() {
        this.volumeLimasElips = (1.0 / 3.0) * super.luas * this.tinggiLimas;
        return this.volumeLimasElips;
    }

    // OVERRIDING : Implementasi method hitungLuasPermukaan() dari interface BendaGeometri.
    public double hitungVolumeLimasElips(double semiMayorLuar, double semiMinorLuar, double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas/kerucut elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.volumeLimasElips = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return this.volumeLimasElips;
    }

    // OVERRIDING : Implementasi method hitungLuasPermukaan() dari interface BendaGeometri.
    public double hitungLuasPermukaanLimasElips() {
        garisPelukisA = Math.sqrt(Math.pow(super.semiMayorLuar, 2) + Math.pow(this.tinggiLimas, 2));
        garisPelukisB = Math.sqrt(Math.pow(super.semiMinorLuar, 2) + Math.pow(this.tinggiLimas, 2));
        rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;
        this.luasSelimutLimasElips = 0.5 * super.keliling * rataGarisPelukis;
        this.luasPermukaanLimasElips = super.luas + this.luasSelimutLimasElips;
        return this.luasPermukaanLimasElips;
    }

    // OVERRIDING : Implementasi method hitungLuasPermukaan() dari interface BendaGeometri.
    public double hitungLuasPermukaanLimasElips(double semiMayorLuar, double semiMinorLuar, double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas/kerucut elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        kelilingAlas = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        garisPelukisA = Math.sqrt(Math.pow(semiMayorLuar, 2) + Math.pow(tinggiLimas, 2));
        garisPelukisB = Math.sqrt(Math.pow(semiMinorLuar, 2) + Math.pow(tinggiLimas, 2));
        rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;
        this.luasSelimutLimasElips = 0.5 * kelilingAlas * rataGarisPelukis;
        this.luasPermukaanLimasElips = luasAlas + this.luasSelimutLimasElips;
        return this.luasPermukaanLimasElips;
    }

    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling limas elips secara otomatis saat thread dijalankan.
    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanLimasElips(semiMayorLuar, semiMinorLuar, tinggiLimas);
            this.hitungVolumeLimasElips(semiMayorLuar, semiMinorLuar, tinggiLimas);
        } else {
            this.hitungLuasPermukaanLimasElips();
            this.hitungVolumeLimasElips();
        }
    }
}