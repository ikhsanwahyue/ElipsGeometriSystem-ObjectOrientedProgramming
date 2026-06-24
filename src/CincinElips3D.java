// INHERITENCE : CincinElips3D (3D) turunan dari BolaElips (3D).
// MULTHITREADING : "implements Runnable" agar bisa berjalan sebagai Thread mandiri untuk masing-masing bangun ruang.
public class CincinElips3D extends BolaElips implements Runnable {

    // ENCAPSULATION : Atribut state untuk membungkus hasil perhitungan volume dan luas permukaan cincin elips 3D.
    public double radiusDalam, volumeDalam, luasPermukaanDalam, volumeCincinElips3D, luasPermukaanCincinElips3D;
    public double luasElips, kelilingElips, volumeLuar, luasPermukaanLuar, luasLingkaranDalam, kelilingLingkaranDalam;

    // Constructor untuk menginisialisasi atribut state cincin elips 3D.
    public CincinElips3D(double semiMayorLuar, double semiMinorLuar, double sumbuC, double radiusDalam, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, sumbuC, isManual); // INHERITENCE : Memanggil constructor parent class (BolaElips) untuk menginisialisasi semiMayorLuar, semiMinorLuar, sumbuC, dan isManual.

        // Validasi : Memastikan nilai radius dalam lebih kecil dari semi mayor dan semi minor luar, serta lebih besar dari 0.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor cincin elips 3D harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor cincin elips 3D!");
        }
        if (radiusDalam <= 0) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih besar dari 0!");
        }
        if (radiusDalam >= semiMayorLuar || radiusDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih kecil dari semi mayor dan semi minor luar!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.radiusDalam = radiusDalam;
    }        

    // Method Baru : method hitungVolumeCincinElips3D (tanpa parameter/menggunakan data internal).
    public double hitungVolumeCincinElips3D() {
        this.luasElips = super.hitungLuas(super.semiMayorLuar, super.semiMinorLuar);
        this.luasLingkaranDalam = PI * this.radiusDalam * this.radiusDalam;

        this.volumeLuar = this.luasElips * super.sumbuC;
        this.volumeDalam = luasLingkaranDalam * super.sumbuC;
        this.volumeCincinElips3D = this.volumeLuar - this.volumeDalam;
        return this.volumeCincinElips3D;
    }

    // OVERLOADING : method hitungVolumeCincinElips3D (dengan parameter).
    public double hitungVolumeCincinElips3D(double semiMayorLuar, double semiMinorLuar, double sumbuC, double radiusDalam) {

        // Validasi : Memastikan nilai radius dalam lebih kecil dari semi mayor dan semi minor luar, serta lebih besar dari 0.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor cincin elips 3D harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor cincin elips 3D!");
        }
        if (radiusDalam <= 0) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih besar dari 0!");
        }
        if (radiusDalam >= semiMayorLuar || radiusDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih kecil dari semi mayor dan semi minor luar!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasLingkaranDalam = PI * radiusDalam * radiusDalam;

        this.volumeLuar = this.luasElips * sumbuC;
        this.volumeDalam = luasLingkaranDalam * sumbuC;
        this.volumeCincinElips3D = this.volumeLuar - this.volumeDalam;
        return this.volumeCincinElips3D;
    }

    // Method Baru : method hitungLuasPermukaanCincinElips3D (tanpa parameter/menggunakan data internal).
    public double hitungLuasPermukaanCincinElips3D() {
        this.luasElips = super.hitungLuas(super.semiMayorLuar, super.semiMinorLuar);
        this.kelilingElips = super.hitungKeliling(super.semiMayorLuar, super.semiMinorLuar);

        this.luasLingkaranDalam = PI * this.radiusDalam * this.radiusDalam;
        this.kelilingLingkaranDalam = 2 * PI * this.radiusDalam;

        this.luasPermukaanLuar = (2 * this.luasElips) + (this.kelilingElips * super.sumbuC);
        this.luasPermukaanDalam = kelilingLingkaranDalam * super.sumbuC;
        this.luasPermukaanCincinElips3D = (2 * (this.luasElips - luasLingkaranDalam))
                + (this.kelilingElips * super.sumbuC)
                + this.luasPermukaanDalam;
        return this.luasPermukaanCincinElips3D;
    }

    // OVERLOADING : method hitungLuasPermukaanCincinElips3D (dengan parameter).
    public double hitungLuasPermukaanCincinElips3D(double semiMayorLuar, double semiMinorLuar, double sumbuC, double radiusDalam) {

        // Validasi : Memastikan nilai radius dalam lebih kecil dari semi mayor dan semi minor luar, serta lebih besar dari 0.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor cincin elips 3D harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor cincin elips 3D!");
        }
        if (radiusDalam <= 0) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih besar dari 0!");
        }
        if (radiusDalam >= semiMayorLuar || radiusDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih kecil dari semi mayor dan semi minor luar!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);

        this.luasLingkaranDalam = PI * radiusDalam * radiusDalam;
        this.kelilingLingkaranDalam = 2 * PI * radiusDalam;

        this.luasPermukaanLuar = (2 * this.luasElips) + (this.kelilingElips * sumbuC);
        this.luasPermukaanDalam = this.kelilingLingkaranDalam * sumbuC;
        this.luasPermukaanCincinElips3D = (2 * (this.luasElips - this.luasLingkaranDalam))
                + (this.kelilingElips * sumbuC)
                + this.luasPermukaanDalam;
        return this.luasPermukaanCincinElips3D;
    }
    
    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan volume dan luas permukaan cincin elips 3D secara otomatis saat thread dijalankan.
    @Override
    public void run() {
        if (this.isManual) {
            this.hitungLuasPermukaanCincinElips3D(semiMayorLuar, semiMinorLuar, sumbuC, radiusDalam);
            this.hitungVolumeCincinElips3D(semiMayorLuar, semiMinorLuar, sumbuC, radiusDalam);
        } else {
            this.hitungLuasPermukaanCincinElips3D();
            this.hitungVolumeCincinElips3D();
        }
    }
}
