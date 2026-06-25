// INHERITENCE : PrismaElips merupakan turunan dari Elips (yang merupakan bangun 2D) untuk membentuk prisma dengan alas elips.
// MULTHITREADING : implements Runnable agar dapat dijalankan dalam thread mandiri.
public class PrismaElips extends Elips implements Runnable {

    public double tinggiPrisma, volumePrismaElips, luasPermukaanPrismaElips;
    public double luasAlas, kelilingAlas;
    
    // Constructor untuk menginisialisasi objek PrismaElips dengan parameter.
    public PrismaElips(double semiMayorLuar, double semiMinorLuar, double tinggiPrisma, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);
        
        // Validasi : Memastikan tinggi prisma dan sumbu-sumbu bernilai positif dan valid.
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    // Menghitung volume prisma elips menggunakan nilai luas alas yang tersimpan.
    public double hitungVolumePrismaElips() {
        this.volumePrismaElips = super.luas * this.tinggiPrisma;
        return this.volumePrismaElips;
    }

    // OVERLOADING : method hitungVolumePrismaElips dengan parameter baru.
    public double hitungVolumePrismaElips(double semiMayorLuar, double semiMinorLuar, double tinggiPrisma) {
        // Validasi parameter
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.volumePrismaElips = luasAlas * tinggiPrisma;
        return this.volumePrismaElips;
    }

    // Menghitung luas permukaan prisma elips (2*luas alas + keliling alas * tinggi).
    public double hitungLuasPermukaanPrismaElips() {
        this.luasPermukaanPrismaElips = (2 * super.luas) + (super.keliling * this.tinggiPrisma);
        return this.luasPermukaanPrismaElips;
    }

    // OVERLOADING : method hitungLuasPermukaanPrismaElips dengan parameter.
    public double hitungLuasPermukaanPrismaElips(double semiMayorLuar, double semiMinorLuar, double tinggiPrisma) {
        // Validasi parameter
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.kelilingAlas = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.luasPermukaanPrismaElips = (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
        return this.luasPermukaanPrismaElips;
    }

    // OVERRIDING : Implementasi method run() dari Runnable untuk menjalankan perhitungan sesuai mode isManual.
    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanPrismaElips(semiMayorLuar, semiMinorLuar, tinggiPrisma);
            this.hitungVolumePrismaElips(semiMayorLuar, semiMinorLuar, tinggiPrisma);
        } else {
            this.hitungLuasPermukaanPrismaElips();
            this.hitungVolumePrismaElips();
        }
    }
}