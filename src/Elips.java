// INHERITENCE : Elips (2D) turunan dari BendaGeometri (interface).
// MULTHITREADING : "implements Runnable" agar bisa berjalan sebagai Thread mandiri untuk masing-masing bangun ruang.
public class Elips implements BendaGeometri, Runnable {

    // ENCAPSULATION : Atribut state untuk membungkus data semi mayor, semi minor, luas, keliling, dan isManual.
    public double semiMayorLuar, semiMinorLuar, luas, keliling;
    public boolean isManual;
    public final double PI = 3.14;

    // Constructor untuk menginisialisasi atribut state elips.
    public Elips(double semiMayorLuar, double semiMinorLuar, boolean isManual) {

        // Validasi : Memastikan nilai semi mayor dan semi minor lebih besar dari 0, serta semi mayor lebih besar atau sama dengan semi minor.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai Semi Mayor Luar dan Semi Minor Luar elips harus lebih besar dari 0!");
        }
        
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi Mayor Luar tidak boleh lebih kecil dari Semi Minor Luar!");
        }
        this.semiMayorLuar = semiMayorLuar;
        this.semiMinorLuar = semiMinorLuar;
        this.isManual = isManual;
    }

    // OVERRIDING : Implementasi method hitungLuas() dan hitungKeliling() dari interface BendaGeometri.
    @Override
    public double hitungLuas() {
        this.luas = PI * this.semiMayorLuar * this.semiMinorLuar;
        return this.luas;
    }

    // OVERLOADING : Implementasi method hitungLuas() dengan parameter dari interface BendaGeometri.
    public double hitungLuas(double semiMayorLuar, double semiMinorLuar) throws IllegalArgumentException {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Input Semi Mayor Luar dan Semi Minor Luar tidak boleh 0 atau negatif!");
        }
        
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi Mayor Luar tidak boleh lebih kecil dari Semi Minor Luar!");
        }
        
        this.luas = PI * semiMayorLuar * semiMinorLuar;
        return this.luas;
    }
    
    // OVERRIDING : Implementasi method hitungKeliling() dari interface BendaGeometri.
    @Override
    public double hitungKeliling() {
        
        // Pendekatan Ramanujan untuk keliling elips dan validasi input semi mayor dan semi minor.
        this.keliling = PI * (3 * (this.semiMayorLuar + this.semiMinorLuar)
                - Math.sqrt((3 * this.semiMayorLuar + this.semiMinorLuar) * (this.semiMayorLuar + 3 * this.semiMinorLuar)));
        return this.keliling;
    }

    // OVERLOADING : Implementasi method hitungKeliling() dengan parameter dari interface BendaGeometri.
    public double hitungKeliling(double semiMayorLuar, double semiMinorLuar) throws IllegalArgumentException {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Input Semi Mayor Luar dan Semi Minor Luar tidak boleh 0 atau negatif!");
        }
        
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi Mayor Luar tidak boleh lebih kecil dari Semi Minor Luar!");
        }
        
        this.keliling = PI * (3 * (semiMayorLuar + semiMinorLuar)
                - Math.sqrt((3 * semiMayorLuar + semiMinorLuar) * (semiMayorLuar + 3 * semiMinorLuar)));
        return this.keliling;
    }
    
    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling elips secara otomatis saat thread dijalankan.
    @Override
    public void run() {
        if (this.isManual) {
            this.hitungLuas(this.semiMayorLuar, this.semiMinorLuar);
            this.hitungKeliling(this.semiMayorLuar, this.semiMinorLuar);
        } else {
            this.hitungLuas();
            this.hitungKeliling();
        }
    }
}
