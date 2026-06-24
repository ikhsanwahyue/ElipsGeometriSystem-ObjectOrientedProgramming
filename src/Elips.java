public class Elips implements BendaGeometri, Runnable {

    // Atribut dibuat public mengikuti pola program acuan.
    public double semiMayorLuar, semiMinorLuar, luas, keliling;
    public boolean isManual;
    public final double PI = 3.14;

    public Elips(double semiMayorLuar, double semiMinorLuar, boolean isManual) {
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

    @Override
    public double hitungLuas() {
        this.luas = PI * this.semiMayorLuar * this.semiMinorLuar;
        return this.luas;
    }

    @Override
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

    @Override
    public double hitungKeliling() {
        
        // Pendekatan Ramanujan untuk keliling elips.
        this.keliling = PI * (3 * (this.semiMayorLuar + this.semiMinorLuar)
                - Math.sqrt((3 * this.semiMayorLuar + this.semiMinorLuar) * (this.semiMayorLuar + 3 * this.semiMinorLuar)));
        return this.keliling;
    }

    @Override
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
