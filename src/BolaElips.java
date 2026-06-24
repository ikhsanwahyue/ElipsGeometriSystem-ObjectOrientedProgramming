// INHERITENCE : BolaElips (3D) turunan dari Elips (2D).
// MULTHITREADING : "implements Runnable" agar bisa berjalan sebagai Thread mandiri untuk masing-masing bangun ruang.
public class BolaElips extends Elips implements Runnable {

    // ENCAPSULATION : Atribut state untuk membungkus hasil perhitungan volume dan luas.
    public double sumbuC, volumeBolaElips, luasPermukaanBolaElips, luasAlas;
    public final double p = 1.6075;

    // Constructor untuk menginisialisasi atribut state bola elips.
    public BolaElips(double semiMayorLuar, double semiMinorLuar, double sumbuC, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);  // INHERITENCE : Memanggil constructor parent class (Elips) untuk menginisialisasi semiMayorLuar, semiMinorLuar, dan isManual.

        // Validasi : Memastikan nilai sumbu C lebih besar dari 0, serta semi mayor dan semi minor lebih besar dari 0 dan semi mayor lebih besar atau sama dengan semi minor.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor bola elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor bola elips!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.sumbuC = sumbuC;
    }

    // Method Baru : method hitungVolumeBolaElips (tanpa parameter/menggunakan data internal).
    public double hitungVolumeBolaElips() {
        if (super.luas <= 0) { 
            throw new IllegalArgumentException("Nilai luas harus lebih besar dari 0!"); 
        }
        this.volumeBolaElips = (4.0 / 3.0) * super.luas * this.sumbuC; // Mengambil variabel 'luas' dari induk.
        return this.volumeBolaElips; // ENCAPSULATION : return hasil perhitungan volume bola elips.
    }

    // OVERLOADING : method hitungVolumeBolaElips (dengan parameter).
    public double hitungVolumeBolaElips(double semiMayorLuar, double semiMinorLuar, double sumbuC) {

        // Validasi : Memastikan nilai sumbu C lebih besar dari 0, serta semi mayor dan semi minor lebih besar dari 0 dan semi mayor lebih besar atau sama dengan semi minor.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor bola elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor bola elips!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.volumeBolaElips = (4.0 / 3.0) * luasAlas * sumbuC;
        return this.volumeBolaElips;
    }
    
    // Method Standard : method hitungLuasPermukaanBolaElips (tanpa parameter/menggunakan data internal).
    public double hitungLuasPermukaanBolaElips() {
        this.luasPermukaanBolaElips = 4 * Math.PI * Math.pow(
                (Math.pow(super.semiMayorLuar * super.semiMinorLuar, p)
                + Math.pow(super.semiMayorLuar * this.sumbuC, p)
                + Math.pow(super.semiMinorLuar * this.sumbuC, p)) / 3.0,
                1.0 / p
        );
        return this.luasPermukaanBolaElips;
    }

    // OVERLOADING : method hitungLuasPermukaanBolaElips (dengan parameter).
    public double hitungLuasPermukaanBolaElips(double semiMayorLuar, double semiMinorLuar, double sumbuC) {

        // Validasi : Memastikan nilai sumbu C lebih besar dari 0, serta semi mayor dan semi minor lebih besar dari 0 dan semi mayor lebih besar atau sama dengan semi minor.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor bola elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor bola elips!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.luasPermukaanBolaElips = 4 * Math.PI * Math.pow(
                (Math.pow(semiMayorLuar * semiMinorLuar, p)
                + Math.pow(semiMayorLuar * sumbuC, p)
                + Math.pow(semiMinorLuar * sumbuC, p)) / 3.0,
                1.0 / p
        );
        return this.luasPermukaanBolaElips;
    }

    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan volume dan luas permukaan bola elips secara otomatis saat thread dijalankan.
    @Override
    public void run() {
        super.run(); // Memastikan data (Elips) sudah dihitung dan siap di memori.
        if (this.isManual) {
            this.hitungLuasPermukaanBolaElips(semiMayorLuar, semiMinorLuar, sumbuC);
            this.hitungVolumeBolaElips(semiMayorLuar, semiMinorLuar, sumbuC);
        } else {
            this.hitungLuasPermukaanBolaElips();
            this.hitungVolumeBolaElips();
        }
    }
}